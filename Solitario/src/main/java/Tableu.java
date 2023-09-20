public class Tableu {
    private boolean empty;
    private Pile FaceDown;
    private Pile FaceUp;

    public Pile Discard(Card HighestCard){
        return FaceUp.DiscardPile(HighestCard);
    }
}

