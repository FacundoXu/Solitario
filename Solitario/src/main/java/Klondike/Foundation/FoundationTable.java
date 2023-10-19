package Klondike.Foundation;

import Card.Card;

public class FoundationTable {

    private final FoundationStack[] stacks = new FoundationStack[4];

    public FoundationTable() {
        for (int i = 0; i < 4; i++) {
            stacks[i] = new FoundationStack();
        }
    }

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
