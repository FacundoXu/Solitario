package Stock;

import Card.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class StockTableTest {

    @Test
    public void oneCard(){
        //Arrange
        Card c = new Card(Suit.CLUBS, 12, Color.BLACK);
        Card[] cardArray= {c};
        StockTable t = new StockTable(cardArray);
        //Act
        boolean empty1 = t.isEmpty();
        Card getCard1 = t.getCard();
        Card peek1 = t.peek();
        Card nextCard1 = t.nextCard();
        Card peek2 = t.peek();
        Card nextCard2 = t.nextCard();
        Card nextCard3 = t.nextCard();
        Card nextCard4 = t.nextCard();
        Card nextCard5 = t.nextCard();
        Card nextCard6 = t.nextCard();
        Card nextCard7 = t.nextCard();
        Card getCard2 = t.getCard();
        boolean empty2 = t.isEmpty();
        //Assert
        assertEquals(false, empty1);
        assertEquals(true, empty2);
        assertEquals(null, getCard1);
        assertEquals(c, getCard2);
        assertEquals(null, peek1);
        assertEquals(c, peek2);
        assertEquals(c, nextCard1);
        assertEquals(null, nextCard2);
        assertEquals(c, nextCard3);
        assertEquals(null, nextCard4);
        assertEquals(c, nextCard5);
        assertEquals(null, nextCard6);
        assertEquals(null, nextCard7);
    }
}