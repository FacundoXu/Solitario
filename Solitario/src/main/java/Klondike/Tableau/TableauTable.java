package Klondike.Tableau;

import Card.Card;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class TableauTable implements Serializable {

    // Constants
    public final static int MAX_NUM_TABLEAUS = 7;

    // Attributes
    private final TableauStack[] tableaus = new TableauStack[MAX_NUM_TABLEAUS];

    // Constructor
    public TableauTable(Card[] cards) {

        // Stores the ranges of the given array that should constitute the tableau stacks
        int[] ranges = {0, 1, 3, 6, 10, 15, 21, 28};

        for (int i = 0; i < MAX_NUM_TABLEAUS; i++) {
            Card[] s = Arrays.copyOfRange(cards, ranges[i], ranges[i + 1]);
            tableaus[i] = new TableauStack(s);
        }
    }

    // Methods
    public Card pickUp(int StackIdx) {
        return tableaus[StackIdx].pop();
    }

    public Card[] peek() {
        Card[] topCards = new Card[MAX_NUM_TABLEAUS];

        for (int i = 0; i < MAX_NUM_TABLEAUS; i++) {
            topCards[i] = this.peek(i);
        }
        return topCards;
    }

    public Card peek(int StackIdx) {
        return tableaus[StackIdx].peek();
    }

    public boolean insert(int tableau, Card c) {
        return tableaus[tableau].push(c);
    }

    public void returnCard(int tableau, Card c) {
        tableaus[tableau].returnCard(c);
    }

    public boolean move(int originCardIdx, int originStack, int destStack) {
        List<Card> CardArray = tableaus[originStack].popArray(originCardIdx);

        if (!tableaus[destStack].pushArray(CardArray)) {
            tableaus[originStack].returnArray(CardArray);
            return false;
        }
        return true;
    }
}