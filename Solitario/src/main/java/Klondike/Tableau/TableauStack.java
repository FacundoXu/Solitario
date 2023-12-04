package Klondike.Tableau;

import Card.Card;
import Stack.Stack;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TableauStack implements Stack<Card>, Serializable {

    // Constants
    private static final int KING = 13;
    private static final int EMPTY = 0;

    // Attributes
    private int size;
    private int faceUpIdx;
    private final List<Card> cards;

    // Constructor
    public TableauStack(Card[] cardArray) {
        cards = new LinkedList<>(Arrays.asList(cardArray));
        size = cards.size();
        faceUpIdx = cards.size() - 1;
    }

    // Methods
    @Override
    public boolean push(Card card) {
        if (this.isEmpty()) {
            if (card.rank() == KING) {
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
    public Card[] peekVisible(){
        LinkedList<Card> resCards = new LinkedList<>();
        if (size == 0) return null;
        for (int i = faceUpIdx; i < size; i++){
            resCards.addLast(cards.get(i));
        }
        return resCards.toArray(new Card[0]);
    }

    @Override
    public boolean isEmpty() {
        return size == EMPTY;
    }

    public void returnCard(Card c) {
        cards.add(c);
        size++;

        if (faceUpIdx > 0)
            faceUpIdx++;
    }

    public void returnArray(List<Card> l) {
        if(l != null) {
            faceUpIdx += l.size();
            cards.addAll(l);
            size = cards.size();
        }
    }

    public List<Card> popArray(int cardIdx) {
        int realIdx = size - cardIdx - 1;

        if (realIdx < faceUpIdx)
            return null;

        if (realIdx == faceUpIdx) faceUpIdx--;

//        && faceUpIdx > 0)

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
            if (bottomCard.rank() == KING) {
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

    public int size(){
        return size;
    }
}