package Spider.Tableu;

import Card.Card;
import Card.Color;
import Card.Suit;
import org.junit.Test;

import static org.junit.Assert.*;

public class TableuStackTest {

    @Test
    public void oneCardMethods() {

        // Arrange
        Card c0 = new Card(Suit.CLUBS, 8, Color.BLACK);
        Card[] initialArray = {c0};
        Spider.Tableu.TableuStack tableuStack = new TableuStack(initialArray);
        Card c1 = new Card(Suit.DIAMONDS, 7, Color.RED);
        Card c2 = new Card(Suit.HEARTS, 6, Color.RED);
        Card c3 = new Card(Suit.CLUBS, 6, Color.BLACK);
        Card c4 = new Card(Suit.SPADES, 5, Color.BLACK);

        // Act
        boolean empty0 = tableuStack.isEmpty();

        boolean push1 = tableuStack.push(c1);
        Card peek1 = tableuStack.peek();
        boolean empty1 = tableuStack.isEmpty();

        boolean push2 = tableuStack.push(c2);
        Card peek2 = tableuStack.peek();
        boolean empty2 = tableuStack.isEmpty();

        boolean push3 = tableuStack.push(c3);
        Card peek3 = tableuStack.peek();
        boolean empty3 = tableuStack.isEmpty();

        boolean push4 = tableuStack.push(c4);
        Card peek4 = tableuStack.peek();
        boolean empty4 = tableuStack.isEmpty();

        Card pop1 = tableuStack.pop();
        Card popAndPeek1 = tableuStack.peek();

        Card pop2 = tableuStack.pop();
        Card popAndPeek2 = tableuStack.peek();

        Card pop3 = tableuStack.pop();
        Card pop4 = tableuStack.pop();
        boolean empty5 = tableuStack.isEmpty();

        Card pop5 = tableuStack.pop();

        // Assert
        assertFalse(empty0);

        assertTrue(push1);
        assertEquals(c1, peek1);
        assertFalse(empty1);

        assertTrue(push2);
        assertEquals(c2, peek2);
        assertFalse(empty2);

        assertFalse(push3);
        assertEquals(c2, peek3);
        assertFalse(empty3);

        assertTrue(push4);
        assertEquals(c4, peek4);
        assertFalse(empty4);

        assertEquals(c4, pop1);
        assertEquals(c2, popAndPeek1);

        assertEquals(c2, pop2);
        assertEquals(c1, popAndPeek2);

        assertEquals(c1, pop3);
        assertEquals(c0, pop4);
        assertTrue(empty5);

        assertNull(pop5);
    }

    @Test
    public void multipleCardsMethods() {

        // Arrange
        Card c0 = new Card(Suit.CLUBS, 10, Color.BLACK);
        Card[] initialArray = {c0};
        Spider.Tableu.TableuStack tableuStack = new TableuStack(initialArray);

        Card c1 = new Card(Suit.CLUBS, 9, Color.BLACK);
        Card c2 = new Card(Suit.CLUBS, 8, Color.BLACK);

        Card sample1 = new Card(Suit.HEARTS, 7, Color.RED);
        Card sample2 = new Card(Suit.HEARTS, 6, Color.RED);
        Card sample3 = new Card(Suit.HEARTS, 5, Color.RED);
        Card sample4 = new Card(Suit.SPADES, 4, Color.BLACK);
        Card sample5 = new Card(Suit.HEARTS, 4, Color.RED);
        Card[] sampleArray = {
                (sample1),
                (sample2),
                (sample3),
        };

        // Act
        boolean pushArray1 = tableuStack.pushArray(sampleArray);
        Card peek1 = tableuStack.peek();
        tableuStack.push(c1);
        tableuStack.push(c2);
        Card peek2 = tableuStack.peek();

        boolean pushArray2 = tableuStack.pushArray(sampleArray);
        Card peek3 = tableuStack.peek();

        Card pop1 = tableuStack.pop();
        Card pop2 = tableuStack.pop();
        Card pop3 = tableuStack.pop();
        Card peek4 = tableuStack.peek();

        tableuStack.push(sample1);
        tableuStack.push(sample2);
        tableuStack.push(sample3);
        Card peek5 = tableuStack.peek();

        Card[] pop4 = tableuStack.popArray(3);
        Card peek6 = tableuStack.peek();
        boolean push1 = tableuStack.pushArray(pop4);
        Card peek7 = tableuStack.peek();

        tableuStack.push(sample4);
        Card[] pop5 = tableuStack.popArray(3);
        Card peek8 = tableuStack.peek();

        tableuStack.pop();
        tableuStack.push(sample5);
        Card[] pop6 = tableuStack.popArray(3);
        Card peek9 = tableuStack.peek();

        // Assert
        assertFalse(pushArray1);
        assertEquals(c0, peek1);
        assertEquals(c2, peek2);

        assertTrue(pushArray2);
        assertEquals(sample3, peek3);

        assertEquals(sample3, pop1);
        assertEquals(sample2, pop2);
        assertEquals(sample1, pop3);
        assertEquals(c2, peek4);

        assertEquals(sample3, peek5);

        assertEquals(sample1, pop4[0]);
        assertEquals(sample2, pop4[1]);
        assertEquals(sample3, pop4[2]);

        assertEquals(c2, peek6);
        assertTrue(push1);
        assertEquals(sample3, peek7);

        assertNull(pop5);
        assertEquals(sample4, peek8);

        assertEquals(sample1, pop6[0]);
        assertEquals(sample2, pop6[1]);
        assertEquals(sample3, pop6[2]);
        assertEquals(sample5, pop6[3]);
        assertEquals(c2, peek9);
    }

    @Test
    public void creatingTableu() {
        // Arrange
        Card c1 = new Card(Suit.CLUBS, 10, Color.BLACK);
        Card c2 = new Card(Suit.CLUBS, 5, Color.BLACK);
        Card c3 = new Card(Suit.CLUBS, 35, Color.BLACK);
        Card[] initialArray = {c1, c2, c3};
        Spider.Tableu.TableuStack tableuStack = new TableuStack(initialArray);

        // Act
        boolean empty1 = tableuStack.isEmpty();
        Card peek1 = tableuStack.peek();
        Card pop1 = tableuStack.pop();
        Card peek2 = tableuStack.peek();
        Card pop2 = tableuStack.pop();
        Card peek3 = tableuStack.peek();
        Card pop3 = tableuStack.pop();
        Card peek4 = tableuStack.peek();
        Card pop4 = tableuStack.pop();

        // Assert
        assertFalse(empty1);
        assertEquals(c3, peek1);
        assertEquals(c3, pop1);
        assertEquals(c2, peek2);
        assertEquals(c2, pop2);
        assertEquals(c1, peek3);
        assertEquals(c1, pop3);
        assertNull(peek4);
        assertNull(pop4);
    }
}