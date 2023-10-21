package Spider.Stock;

import Card.Card;
import Card.Suit;
import Card.Color;
import org.junit.Test;

import static org.junit.Assert.*;

public class StockTest {

    @Test
    public void drawCards() {

        // Arrange
        Card c1 = new Card(Suit.CLUBS, 5, Color.BLACK);
        Card c2 = new Card(Suit.HEARTS, 6, Color.RED);
        Card c3 = new Card(Suit.SPADES, 4, Color.BLACK);
        Card c4 = new Card(Suit.DIAMONDS, 3, Color.RED);
        Card[] initialArray = {c1, c2, c3, c4};
        Stock stock = new Stock(initialArray);

        // Act
        Card draw1 = stock.drawCard();
        Card draw2 = stock.drawCard();
        Card draw3 = stock.drawCard();
        Card draw4 = stock.drawCard();

        // Assert
        assertEquals(c4, draw1);
        assertEquals(c3, draw2);
        assertEquals(c2, draw3);
        assertEquals(c1, draw4);
    }
}