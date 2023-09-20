import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck {
    List<Card> deck;

    public Deck(){
        deck = new ArrayList<Card>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                Color color = (suit == Suit.HEARTS || suit == Suit.DIAMONDS) ? Color.RED : Color.BLACK;
                deck.add(new Card(suit, rank, color));
            }
        }
    }
    public void Shuffle(int seed){
        Collections.shuffle(deck, new Random(seed));
    }
}