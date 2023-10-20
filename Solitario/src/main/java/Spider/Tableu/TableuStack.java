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
        this.check();

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
        this.check();

        if (this.isEmpty())
            return null;
        return visibleCards.remove(visibleCards.size() - 1);
    }

    public Card peek() {
        if (visibleCards.isEmpty())
            return null;
        return visibleCards.get(visibleCards.size() - 1);
    }

    @Override
    public boolean isEmpty() {
        return visibleCards.isEmpty();
    }

    public void check() {
        if (visibleCards.isEmpty()) {
            if (!hiddenCards.isEmpty()) {
                visibleCards.add(hiddenCards.remove(hiddenCards.size() - 1));
            }
        }
    }
}