import java.util.LinkedList;
import java.util.List;

public class Pile {
    private final List<Card> cards;

    public Pile(List<Card> cards) {
        this.cards = cards;
    }

    //No sé si conviene recibir la carta o el índice cómo arg
    public Pile DiscardPile(Card highestCard) {
        int idxCard = cards.indexOf(highestCard);
        List<Card> leftDeck = new LinkedList<>(cards.subList(0, idxCard + 1));
        Pile leftPile = new Pile(leftDeck);
        this.cards.subList(0, idxCard + 1).clear();
        return leftPile;
    }

    public void ShowCards(){
        for (Card card : cards){
            System.out.println(card);
        }
    }

    public int Size(){
        return cards.size();
    }
    public boolean equals(Pile pile) {
        if (pile.Size() != cards.size()){
            return false;
        }
        int idx = 0;
        for (Card c : pile.cards){
            if (!c.equals(cards.get(idx))){
                return false;
            }
            idx += 1;
        }
        return true;
    }

    /*    public boolean InsertPile(Pile src){
        List<Card> newCards = new ArrayList<>();
        newCards.addAll(src);
        newCards.addAll(src);
    }
 */
}