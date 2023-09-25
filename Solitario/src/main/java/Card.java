enum Suit {
    HEARTS, DIAMONDS, CLUBS, SPADES
}

enum Color {
    RED, BLACK
}

public record Card(Suit suit, int rank, Color color) {
    
}