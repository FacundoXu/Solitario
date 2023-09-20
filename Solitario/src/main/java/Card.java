enum Suit {
    HEARTS, DIAMONDS, CLUBS, SPADES
}

enum Rank {
    ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
}

enum Color {
    RED, BLACK
}

public record Card(Suit suit, Rank rank, Color color) {
}