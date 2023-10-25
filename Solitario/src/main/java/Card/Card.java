package Card;

import java.io.Serializable;

public record Card(Suit suit, int rank, Color color) implements Serializable {

}