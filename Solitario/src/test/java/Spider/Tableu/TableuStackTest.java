package Spider.Tableu;

import Card.Card;
import Card.Color;
import Card.Suit;
import org.junit.Test;

import static org.junit.Assert.*;

public class TableuStackTest {

    @Test
    public void oneCard() {

        // Arrange
        Card c0 = new Card(Suit.CLUBS, 8, Color.BLACK);
        Card[] initialArray = {c0};
        Spider.Tableu.TableuStack tableuStack = new TableuStack(initialArray);
        Card c1 = new Card(Suit.DIAMONDS, 7, Color.RED);
        Card c2 = new Card(Suit.DIAMONDS, 6, Color.RED);
        Card c3 = new Card(Suit.CLUBS, 6, Color.BLACK);
        Card c4 = new Card(Suit.DIAMONDS, 5, Color.RED);

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

        assertNull(poooop);
    }
}