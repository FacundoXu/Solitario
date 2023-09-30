package Stack;
import Card.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CardStack implements Stack<Card>{
    private final List<Card> cards;
    private int size = 0;

    public CardStack(Card[] cardsArray){
        cards = new ArrayList<Card>(Arrays.asList(cardsArray));
        size = cards.size();
    }
    public CardStack(){
        cards = new ArrayList<Card>();
    }

    @Override
    public boolean push(Card card) {
        cards.add(card);
        size++;
        return true;
    }

    @Override
    public Card pop() {
        if (this.isEmpty()) return null;
        size--;
        return cards.remove(size);
    }

    @Override
    public Card peek() {
        if (this.isEmpty()) return null;
        return cards.get(size -1);
    }

    @Override
    public boolean isEmpty() {return size == 0;}

    @Override
    public int size() {
        return size;
    }

    public void reverse(){ Collections.reverse(cards);}

}