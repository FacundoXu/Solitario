import java.util.LinkedList;
import java.util.List;

public class OldPile {
    private final List<Card> cards;

    public OldPile(List<Card> cards) {
        this.cards = cards;
    }

    //No sé si conviene recibir la carta o el índice cómo arg
    public OldPile DiscardPile(Card highestCard) {
        int idxCard = cards.indexOf(highestCard);
        List<Card> leftDeck = new LinkedList<>(cards.subList(0, idxCard + 1));
        OldPile leftPile = new OldPile(leftDeck);
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
    
    public boolean equals(OldPile pile) {
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

    public void InsertPile(OldPile src){
        List<Card> newList = new LinkedList<>();
        newList.addAll(src.cards);
        newList.addAll(src.cards);

    }
}