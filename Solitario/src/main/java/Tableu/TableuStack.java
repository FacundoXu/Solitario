package Tableu;

import Card.Card;
import Stack.Stack;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TableuStack implements Stack<Card> {
    private int size;
    private final List<Card> cards;

    public TableuStack() {
        cards = new LinkedList<>();
        size = 0;
    }

    public TableuStack(Card[] cardArray) {
        cards = new LinkedList<>(Arrays.asList(cardArray));
        size = cards.size();
    }

    @Override
    public boolean push(Card card) {
        if (this.isEmpty()) {
            cards.add(card);
            size++;
            return true;
        }
        Card topCard = this.peek();
        if (topCard.color() != card.color() && topCard.rank() == card.rank() + 1){
            cards.add(card);
            size++;
            return true;
        }
        return false;
    }

    @Override
    public Card pop() {
        if (this.isEmpty()) return null;
        size--;
        return cards.remove(size);
    }
    public Card peek() {
        if (this.isEmpty()) return null;
        return cards.get(size - 1);
    }
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    @Override
    public int size() {
        return size;
    }
}
