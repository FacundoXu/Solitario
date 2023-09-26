import org.junit.Test;
import java.util.LinkedList;
import java.util.List;
import static org.junit.Assert.*;

public class StackTest {

    @Test
    public void testSize1() {
        // Arrange
        OldPile p = new OldPile(new LinkedList<>());
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
        OldPile p = new OldPile(l);
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

        OldPile p = new OldPile(l);
        int expected = 5;
        // Act
        int result = p.Size();
        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testEquals1() {
    //Arrange
    OldPile p1 = new OldPile(new LinkedList<>());
    OldPile p2 = new OldPile(new LinkedList<>());
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
        OldPile p1 = new OldPile(l);
        OldPile p2 = new OldPile(new LinkedList<>());
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

        OldPile p1 = new OldPile(l1);
        OldPile p2 = new OldPile(l2);
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

        OldPile p1 = new OldPile(l1);
        OldPile p2 = new OldPile(l2);
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