package Klondike.Tableu;

import Card.Card;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class TableuTable implements Serializable {

    private final TableuStack[] stacks = new TableuStack[7];

    public TableuTable(Card[] cards) {

        // Stores the ranges of the given array that should constitute the tableu stacks
        int[] ranges = {0, 1, 3, 6, 10, 15, 21, 28};

        for (int i = 0; i < 7; i++) {
            Card[] s = Arrays.copyOfRange(cards, ranges[i], ranges[i + 1]);
            stacks[i] = new TableuStack(s);
        }
    }

    public Card pickUp(int StackIdx) {
        return stacks[StackIdx].pop();
    }

    public Card[] peek() {
        Card[] topCards = new Card[7];

        for (int i = 0; i < 7; i++) {
            topCards[i] = this.peek(i);
        }
        return topCards;
    }

    public Card peek(int StackIdx) {
        return stacks[StackIdx].peek();
    }

    public boolean insert(int tableu, Card c) {
        return stacks[tableu].push(c);
    }

    public void returnCard(int tableu, Card c) {
        stacks[tableu].returnCard(c);
    }

    public boolean move(int originCardIdx, int originStack, int destStack) {
        List<Card> CardArray = stacks[originStack].popArray(originCardIdx);

        if (!stacks[destStack].pushArray(CardArray)) {
            stacks[originStack].returnArray(CardArray);
            return false;
        }
        return true;
    }
}