import java.util.LinkedList;
import java.util.List;

public class FoundationStack implements Stack<Card>{
    private int size;
    private List<Card> cards;
    private Suit suit;

    public FoundationStack(){
        size = 0;
        cards = new LinkedList<Card>();
        suit = null;
    }

    public boolean push(Card card) {

        if (size > 0 &&
                (!suit.equals(card.suit()) ||
                cards.get(size - 1).rank() != card.rank() - 1)){
            return false;

        } else {
            if (size == 0) suit = card.suit();
            cards.add(card);
            size ++;
            return true;
        }
    }

    private void privatePush(Card card) {
        size ++;
        cards.add(card);
    }
    
    @Override
    public Card pop() {
        if (this.isEmpty()) return null;
        size --;
        if (size == 0) suit = null;
        return cards.remove(size - 1);
    }

    @Override
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
