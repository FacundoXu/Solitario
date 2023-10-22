package Spider.Tableau;

import Card.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class TableauStackTest {

    @Test
    public void oneCardMethods() {

        // Arrange
        Card c0 = new Card(Suit.CLUBS, 8, Color.BLACK);
        Card[] initialArray = {c0};
        TableauStack tableauStack = new TableauStack(initialArray);
        Card c1 = new Card(Suit.DIAMONDS, 7, Color.RED);
        Card c2 = new Card(Suit.HEARTS, 6, Color.RED);
        Card c3 = new Card(Suit.CLUBS, 6, Color.BLACK);
        Card c4 = new Card(Suit.SPADES, 5, Color.BLACK);

        // Act
        boolean empty0 = tableauStack.isEmpty();

        boolean push1 = tableauStack.push(c1);
        Card peek1 = tableauStack.peek();
        boolean empty1 = tableauStack.isEmpty();

        boolean push2 = tableauStack.push(c2);
        Card peek2 = tableauStack.peek();
        boolean empty2 = tableauStack.isEmpty();

        boolean push3 = tableauStack.push(c3);
        Card peek3 = tableauStack.peek();
        boolean empty3 = tableauStack.isEmpty();

        boolean push4 = tableauStack.push(c4);
        Card peek4 = tableauStack.peek();
        boolean empty4 = tableauStack.isEmpty();

        Card pop1 = tableauStack.pop();
        Card popAndPeek1 = tableauStack.peek();

        Card pop2 = tableauStack.pop();
        Card popAndPeek2 = tableauStack.peek();

        Card pop3 = tableauStack.pop();
        Card pop4 = tableauStack.pop();
        boolean empty5 = tableauStack.isEmpty();

        Card pop5 = tableauStack.pop();

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
        TableauStack tableuStack = new TableauStack(initialArray);

        Card c1 = new Card(Suit.CLUBS, 9, Color.BLACK);
        Card c2 = new Card(Suit.CLUBS, 8, Color.BLACK);

        Card sample1 = new Card(Suit.HEARTS, 7, Color.RED);
        Card sample2 = new Card(Suit.HEARTS, 6, Color.RED);
        Card sample3 = new Card(Suit.HEARTS, 5, Color.RED);
        Card sample4 = new Card(Suit.SPADES, 4, Color.BLACK);
        Card sample5 = new Card(Suit.HEARTS, 4, Color.RED);
        Card[] sampleArray = {(sample1), (sample2), (sample3),};

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
    public void creatingTableau() {
        // Arrange
        Card c1 = new Card(Suit.CLUBS, 10, Color.BLACK);
        Card c2 = new Card(Suit.CLUBS, 5, Color.BLACK);
        Card c3 = new Card(Suit.CLUBS, 35, Color.BLACK);
        Card[] initialArray = {c1, c2, c3};
        TableauStack tableauStack = new TableauStack(initialArray);

        // Act
        boolean empty1 = tableauStack.isEmpty();
        Card peek1 = tableauStack.peek();
        Card pop1 = tableauStack.pop();
        Card peek2 = tableauStack.peek();
        Card pop2 = tableauStack.pop();
        Card peek3 = tableauStack.peek();
        Card pop3 = tableauStack.pop();
        Card peek4 = tableauStack.peek();
        Card pop4 = tableauStack.pop();

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

    @Test
    public void verifyWinnerCards() {
        // Arrange
        Card c1 = new Card(Suit.DIAMONDS, 13, Color.RED);
        Card c2 = new Card(Suit.DIAMONDS, 12, Color.RED);
        Card c3 = new Card(Suit.DIAMONDS, 11, Color.RED);
        Card c4 = new Card(Suit.DIAMONDS, 10, Color.RED);
        Card c5 = new Card(Suit.DIAMONDS, 9, Color.RED);
        Card c6 = new Card(Suit.DIAMONDS, 8, Color.RED);
        Card c7 = new Card(Suit.DIAMONDS, 7, Color.RED);
        Card c8 = new Card(Suit.DIAMONDS, 6, Color.RED);
        Card c9 = new Card(Suit.DIAMONDS, 5, Color.RED);
        Card c10 = new Card(Suit.DIAMONDS, 4, Color.RED);
        Card c11 = new Card(Suit.DIAMONDS, 3, Color.RED);
        Card c12 = new Card(Suit.DIAMONDS, 2, Color.RED);
        Card c13 = new Card(Suit.DIAMONDS, 1, Color.RED);
        Card[] initialArray = {c1};
        Card[] pushArray = {c2, c3, c4, c5, c6, c7, c8, c9};
        TableauStack tableuStack = new TableauStack(initialArray);

        // Act
        Card peek1 = tableuStack.peek();
        boolean pushArray1 = tableuStack.pushArray(pushArray);
        Card peek2 = tableuStack.peek();
        Card[] winner1 = tableuStack.getWinnerCards();
        tableuStack.push(c10);
        tableuStack.push(c11);
        tableuStack.push(c12);
        tableuStack.push(c13);
        Card[] winner2 = tableuStack.getWinnerCards();
        Card[] winnerSample = {c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13};
        boolean empty1 = tableuStack.isEmpty();

        // Assert
        assertEquals(c1, peek1);
        assertTrue(pushArray1);
        assertEquals(c9, peek2);
        assertNull(winner1);
        assertEquals(winnerSample, winner2);
        assertTrue(empty1);
    }
}