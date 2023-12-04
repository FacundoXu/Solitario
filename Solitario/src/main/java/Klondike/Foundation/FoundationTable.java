package Klondike.Foundation;

import Card.Card;

import java.io.Serializable;

public class FoundationTable implements Serializable {

    // Constants
    private static final int MAX_NUM_FOUNDATIONS = 4;

    // Attributes
    private final FoundationStack[] foundations = new FoundationStack[MAX_NUM_FOUNDATIONS];

    // Constructor
    public FoundationTable() {
        for (int i = 0; i < MAX_NUM_FOUNDATIONS; i++) {
            foundations[i] = new FoundationStack();
        }
    }

    // Methods
    public boolean insert(int i, Card c) {
        return foundations[i].push(c);
    }

    public Card get(int i) {
        return foundations[i].pop();
    }

    public boolean verify() {
        for (FoundationStack foundation : foundations) {
            if (!foundation.verify())
                return false;
        }
        return true;
    }

    public Card peek(int foundationIdx) {
        return foundations[foundationIdx].peek();
    }
}