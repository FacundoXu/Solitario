package Klondike.Tableu;

import Card.Card;
import Stack.Stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TableuStack implements Stack<Card> {

    private int size;
    private int faceUpIdx;
    private final List<Card> cards;

    public TableuStack(Card[] cardArray) {
        cards = new LinkedList<>(Arrays.asList(cardArray));
        size = cards.size();
        faceUpIdx = cards.size() - 1;
    }

    @Override
    public boolean push(Card card) {
        if (this.isEmpty()) {
            if (card.rank() == 13) {
                cards.add(card);
                size++;
                return true;
            }
            return false;
        }

        Card topCard = this.peek();

        if (topCard.color() != card.color() && topCard.rank() == card.rank() + 1) {
            cards.add(card);
            size++;
            return true;
        }
        return false;
    }

    @Override
    public Card pop() {
        if (this.isEmpty())
            return null;

        size--;

        if (faceUpIdx == size)
            faceUpIdx--;

        return cards.remove(size);
    }

    public Card peek() {
        if (this.isEmpty())
            return null;
        return cards.get(size - 1);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void returnCard(Card c) {
        cards.add(c);
        size++;

        if (faceUpIdx > 0)
            faceUpIdx++;
    }

    public void returnArray(List<Card> l) {
        cards.addAll(l);
        size = cards.size();
    }

    public List<Card> popArray(int cardIdx) {
        int realIdx = size - cardIdx - 1;

        if (realIdx < faceUpIdx)
            return null;

        if (realIdx == faceUpIdx)
            faceUpIdx--;

        List<Card> left = new ArrayList<>(cards.subList(0, realIdx));
        List<Card> right = new ArrayList<>(cards.subList(realIdx, cards.size()));
        cards.clear();
        cards.addAll(left);
        size = cards.size();

        return right;
    }

    public boolean pushArray(List<Card> l) {
        Card bottomCard = l.get(0);

        if (this.isEmpty()) {
            if (bottomCard.rank() == 13) {
                cards.addAll(l);
                size = cards.size();
                return true;
            }
            return false;
        }

        Card topCard = this.peek();

        if (topCard.color() != bottomCard.color() && topCard.rank() == bottomCard.rank() + 1) {
            cards.addAll(l);
            size = cards.size();
            return true;
        }
        return false;
    }
}