package Spider.Tableu;

import Card.Card;
import Stack.Stack;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TableuStack implements Stack<Card> {

    private final List<Card> visibleCards;
    private final List<Card> hiddenCards;

    public TableuStack(Card[] cardsArray) {
        hiddenCards = new LinkedList<>(Arrays.asList(cardsArray));
        visibleCards = new LinkedList<>();
        Card lastHiddenCard = hiddenCards.remove(hiddenCards.size() - 1);
        visibleCards.add(lastHiddenCard);
    }

    @Override
    public boolean push(Card card) {
        if (this.isEmpty()) {
            visibleCards.add(card);
            return true;
        }

        if (card.rank() == (this.peek().rank() - 1)) {
            visibleCards.add(card);
            return true;
        }
        return false;
    }

    @Override
    public Card pop() {
        if (this.isEmpty())
            return null;

        Card card = visibleCards.remove(visibleCards.size() - 1);
        this.checkHiddenCards();
        return card;
    }

    @Override
    public Card peek() {
        if (this.isEmpty())
            return null;
        return visibleCards.get(visibleCards.size() - 1);
    }

    @Override
    public boolean isEmpty() {
        return visibleCards.isEmpty();
    }

    public boolean pushArray(Card[] cardsArray) {

        if (this.isEmpty()) {
            visibleCards.addAll(Arrays.asList(cardsArray));
            return true;
        }

        if (cardsArray[0].rank() == (this.peek().rank() - 1)) {
            visibleCards.addAll(Arrays.asList(cardsArray));
            return true;
        }
        return false;
    }

    public Card[] popArray(int i) {
        if (this.isEmpty())
            return null;

        Card[] popCards = Arrays.copyOfRange(visibleCards.toArray(new Card[0]), i, visibleCards.size());

        if (this.checkIsPopable(popCards)) {
            visibleCards.subList(i, visibleCards.size()).clear();
            this.checkHiddenCards();
            return popCards;
        }
        return null;
    }

    private boolean checkIsPopable(Card[] cardsArray) {
        for (int i = 1; i < cardsArray.length; i++) {
            if (cardsArray[i - 1].color() != cardsArray[i].color() || cardsArray[i - 1].suit() != cardsArray[i].suit())
                return false;
        }
        return true;
    }

    private void checkHiddenCards() {
        if (this.isEmpty()) {
            if (!hiddenCards.isEmpty()) {
                visibleCards.add(hiddenCards.remove(hiddenCards.size() - 1));
            }
        }
    }

    private int getKing() {
        for (int i = 0; i < visibleCards.size(); i++) {
            if (visibleCards.get(i).rank() == 13)
                return i;
        }
        return -1;
    }

    public Card[] getWinnerCards() {
        int king = this.getKing();

        if (king == -1)
            return null;

        Card[] cards = this.popArray(king);

        if (cards != null && (cards[cards.length - 1].rank() == 1)) {
            return cards;
        }
        this.pushArray(cards);
        return null;
    }
}