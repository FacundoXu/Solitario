package Klondike.Foundation;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import Card.*;
import Stack.Stack;

public class FoundationStack implements Stack<Card>, Serializable {

    // Constants
    public static final int ACE = 1;
    public static final int KING = 13;
    public static final int EMPTY = 0;

    // Attributes
    private int size;
    private final List<Card> cards;
    private Suit suit;

    // Constructor
    public FoundationStack() {
        size = 0;
        cards = new LinkedList<>();
        suit = null;
    }

    // Methods
    @Override
    public boolean push(Card card) {
        if (this.isEmpty()) {
            if (card.rank() != ACE)
                return false;
            suit = card.suit();

        } else if (!suit.equals(card.suit()) || this.peek().rank() != card.rank() - 1)
            return false;

        cards.add(card);
        size++;
        return true;
    }

    @Override
    public Card pop() {
        if (this.isEmpty())
            return null;

        size--;

        if (size == EMPTY)
            suit = null;
        return cards.remove(size);
    }

    @Override
    public Card peek() {
        if (this.isEmpty())
            return null;
        return cards.get(size - 1);
    }

    @Override
    public boolean isEmpty() {
        return size == EMPTY;
    }

    public boolean verify() {
        return !this.isEmpty() && this.peek().rank() == KING;
    }
}