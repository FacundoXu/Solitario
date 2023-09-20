import java.util.ArrayList;
import java.util.List;

public class Pile {
    private List<Card> cards;

    public Pile(List<Card> cards) {
    }

    public Pile DiscardPile(Card highestCard) {
        int idxCard = cards.indexOf(highestCard);
        List<Card> LeftDeck = new ArrayList<>(cards.subList(0, idxCard + 1));
        Pile leftPile = new Pile(LeftDeck);
        this.cards = new ArrayList<>(cards.subList(idxCard + 1, cards.size()));
        return leftPile;
    }
/*    public boolean InsertPile(Pile src){
        List<Card> newCards = new ArrayList<>();
        newCards.addAll(src);
        newCards.addAll(src);
    }
 */
}