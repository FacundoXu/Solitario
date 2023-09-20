import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class PileTest {

    @Test
    public void testSize1() {
        // Arrange
        Pile p = new Pile(new LinkedList<>());
        int expected = 0;
        // Act
        int result = p.Size();
        // Assert
        assertEquals(expected, result);
    }
    @Test
    public void testSize2() {
        // Arrange
        List<Card> l = new LinkedList<Card>();
        l.add(new Card(Suit.SPADES, Rank.JACK, Color.BLACK));
        Pile p = new Pile(l);
        int expected = 1;
        // Act
        int result = p.Size();
        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testSize3() {
        // Arrange
        List<Card> l = new LinkedList<Card>();
        l.add(new Card(Suit.SPADES, Rank.JACK, Color.BLACK));
        l.add(new Card(Suit.SPADES, Rank.FIVE, Color.BLACK));
        l.add(new Card(Suit.DIAMONDS, Rank.JACK, Color.RED));
        l.add(new Card(Suit.HEARTS, Rank.ACE, Color.RED));
        l.add(new Card(Suit.DIAMONDS, Rank.FOUR, Color.RED));

        Pile p = new Pile(l);
        int expected = 5;
        // Act
        int result = p.Size();
        // Assert
        assertEquals(expected, result);
    }


    @Test
    public void testEquals1() {
    //Arrange
    Pile p1 = new Pile(new LinkedList<>());
    Pile p2 = new Pile(new LinkedList<>());
    boolean expected = true;
    //Act
    boolean result = p1.equals(p2);
    //Assert
    assertEquals(expected, result);
    }
    @Test
    public void testEquals2() {
        //Arrange
        List<Card> l  = new LinkedList<Card>();
        l.add(new Card(Suit.CLUBS, Rank.KING, Color.BLACK));
        l.add(new Card(Suit.CLUBS, Rank.QUEEN, Color.BLACK));
        Pile p1 = new Pile(l);
        Pile p2 = new Pile(new LinkedList<>());
        boolean expected = false;
        //Act
        boolean result = p1.equals(p2);
        //Assert
        assertEquals(expected, result);
    }
    @Test
    public void testEquals3() {
        //Arrange
        List<Card> l1  = new LinkedList<Card>();
        l1.add(new Card(Suit.CLUBS, Rank.KING, Color.BLACK));
        l1.add(new Card(Suit.CLUBS, Rank.QUEEN, Color.BLACK));
        List<Card> l2  = new LinkedList<Card>();
        l2.add(new Card(Suit.CLUBS, Rank.KING, Color.BLACK));
        l2.add(new Card(Suit.CLUBS, Rank.QUEEN, Color.BLACK));

        Pile p1 = new Pile(l1);
        Pile p2 = new Pile(l2);
        boolean expected = true;
        //Act
        boolean result = p1.equals(p2);
        //Assert
        assertEquals(expected, result);
    }

    @Test
    public void testEquals4() {
        //Arrange
        List<Card> l1  = new LinkedList<Card>();
        l1.add(new Card(Suit.CLUBS, Rank.KING, Color.BLACK));
        l1.add(new Card(Suit.CLUBS, Rank.QUEEN, Color.BLACK));
        List<Card> l2  = new LinkedList<Card>();
        l2.add(new Card(Suit.CLUBS, Rank.QUEEN, Color.BLACK));
        l2.add(new Card(Suit.CLUBS, Rank.KING, Color.BLACK));

        Pile p1 = new Pile(l1);
        Pile p2 = new Pile(l2);
        boolean expected = false;
        //Act
        boolean result = p1.equals(p2);
        //Assert
        assertEquals(expected, result);
    }

    @Test
    public void discardPile() {
    }

    @Test
    public void showCards() {
    }

}