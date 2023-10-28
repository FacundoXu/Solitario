package Klondike.Foundation;

import Card.Card;

import java.io.Serializable;

public class FoundationTable implements Serializable {

    // Constants
    public static final int MAX_NUM_FOUNDATIONS = 4;

    // Attributes
    private final FoundationStack[] stacks = new FoundationStack[MAX_NUM_FOUNDATIONS];

    // Constructor
    public FoundationTable() {
        for (int i = 0; i < MAX_NUM_FOUNDATIONS; i++) {
            stacks[i] = new FoundationStack();
        }
    }

    // Methods
    public boolean insert(int i, Card c) {
        return stacks[i].push(c);
    }

    public Card get(int i) {
        return stacks[i].pop();
    }

    public boolean verify() {
        for (FoundationStack s : stacks) {
            if (!s.verify())
                return false;
        }
        return true;
    }
}
