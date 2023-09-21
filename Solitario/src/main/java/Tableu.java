public class Tableu {
    private boolean empty;
    private OldPile FaceDown;
    private OldPile FaceUp;

    public OldPile Discard(Card HighestCard){
        return FaceUp.DiscardPile(HighestCard);
    }
}

