package Foundation;

import Card.Card;

import java.util.LinkedList;
import java.util.List;
import Card.*;
import Stack.Stack;

public class FoundationStack implements Stack<Card> {

    private int size;

    private final List<Card> cards;

    private Suit suit;

    public FoundationStack() {
        size = 0;
        cards = new LinkedList<>();
        suit = null;
    }
    @Override
    public boolean push(Card card) {

        if (this.isEmpty()) {
            if (card.rank()  != 1) return false;
            suit = card.suit();
        } else if (!suit.equals(card.suit()) ||
            this.peek().rank() != card.rank() - 1) return false;
        cards.add(card);
        size++;
        return true;
    }
    @Override
    public Card pop() {
        if (this.isEmpty()) return null;
        size--;
        if (size == 0) suit = null;
        return cards.remove(size);
    }
    @Override
    public Card peek() {
        if (this.isEmpty()) return null;
        return cards.get(size - 1);
    }
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public boolean verify() { return !this.isEmpty() && this.peek().rank() == 13; }
}