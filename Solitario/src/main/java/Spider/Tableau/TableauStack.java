package Spider.Tableau;

import Card.Card;
import Stack.Stack;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TableauStack implements Stack<Card>, Serializable {

    // Constants
    private static final int NOT_FOUND = -1;

    // Attributes
    private final List<Card> visibleCards;
    private final List<Card> hiddenCards;
    private int size;

    // Constructor
    public TableauStack(Card[] cardsArray) {
        hiddenCards = new LinkedList<>(Arrays.asList(cardsArray));
        visibleCards = new LinkedList<>();
        Card lastHiddenCard = hiddenCards.remove(hiddenCards.size() - 1);
        visibleCards.add(lastHiddenCard);
    }

    // Methods
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

    public void returnCard(Card card) {
        if (hiddenCards.size() >= 4 && visibleCards.size() == 1) {
            visibleCards.add(card);

        } else {
            hiddenCards.add(visibleCards.remove(0));
            visibleCards.add(card);
        }
    }

    public void pushStockCard(Card card) {
        visibleCards.add(card);
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

    public void returnArray(Card[] cards) {
        visibleCards.addAll(Arrays.asList(cards));
    }

    public Card[] popArray(int i) {
        if (this.isEmpty())
            return null;

        Card[] popCards = Arrays.copyOfRange(visibleCards.toArray(new Card[0]), i, visibleCards.size());

        if (this.checkCanPop(popCards)) {
            visibleCards.subList(i, visibleCards.size()).clear();
            this.checkHiddenCards();
            return popCards;
        }
        return null;
    }

    private void checkHiddenCards() {
        if (this.isEmpty()) {
            if (!hiddenCards.isEmpty()) {
                visibleCards.add(hiddenCards.remove(hiddenCards.size() - 1));
            }
        }
    }

    private boolean checkCanPop(Card[] cardsArray) {
        for (int i = 1; i < cardsArray.length; i++) {
            if (cardsArray[i - 1].color() != cardsArray[i].color() || cardsArray[i - 1].suit() != cardsArray[i].suit())
                return false;
        }
        return true;
    }

    public Card[] getWinnerCards() {
        int king = this.getKing();

        if (king == NOT_FOUND)
            return null;

        Card[] cards = this.popArray(king);

        if (cards != null && (cards[cards.length - 1].rank() == 1))
            return cards;

        return null;
    }

    private int getKing() {
        for (int i = 0; i < visibleCards.size(); i++) {
            if (visibleCards.get(i).rank() == 13)
                return i;
        }
        return NOT_FOUND;
    }

    public int size() {
        return hiddenCards.size() + visibleCards.size();
    }

    public int getHiddenCardsSize() {
        return hiddenCards.size();
    }

    public int getVisibleCardsSize() {
        return visibleCards.size();
    }

    public List<Card> getVisibleCards() {
        return visibleCards;
    }
}