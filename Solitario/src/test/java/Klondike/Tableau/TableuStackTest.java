package Klondike.Tableau;

import Card.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class TableuStackTest {

    @Test
    public void oneCard() {

        // Arrange
        Card[] initialArray = {new Card(Suit.CLUBS, 8, Color.BLACK)};
        TableauStack tableauStack = new TableauStack(initialArray);
        Card c1 = new Card(Suit.DIAMONDS, 7, Color.RED);
        Card c2 = new Card(Suit.DIAMONDS, 6, Color.RED);
        Card c3 = new Card(Suit.CLUBS, 6, Color.BLACK);
        Card c4 = new Card(Suit.DIAMONDS, 10, Color.RED);

        // Act
        boolean push1 = tableauStack.push(c1);
        Card peek1 = tableauStack.peek();
        boolean push2 = tableauStack.push(c2);
        boolean push3 = tableauStack.push(c3);
        boolean push4 = tableauStack.push(c4);

        // Assert
        assertTrue(push1);
        assertFalse(push2);
        assertTrue(push3);
        assertFalse(push4);
    }

    @Test
    public void multipleCards() {

        // Arrange
        Card[] initialArray = {new Card(Suit.CLUBS, 10, Color.BLACK)};
        TableauStack s = new TableauStack(initialArray);
        List<Card> interleavedCards = new LinkedList<>();
        Suit[] suits = {Suit.CLUBS, Suit.DIAMONDS, Suit.SPADES, Suit.HEARTS};
        Color[] colors = {Color.BLACK, Color.RED};

        for (int i = 9; i > 0; i--) {
            interleavedCards.add(new Card(suits[i % 4], i, colors[i % 2]));
        }

        Card[] sampleArray = {
                new Card(Suit.HEARTS, 7, Color.RED),
                new Card(Suit.CLUBS, 6, Color.BLACK),
                new Card(Suit.DIAMONDS, 5, Color.RED),
        };

        // Act and assert
        for (Card card : interleavedCards) {
            assertTrue(s.push(card));
        }

        assertEquals(interleavedCards.subList(2, interleavedCards.size()), s.popArray(6));
        assertTrue(s.pushArray(Arrays.asList(sampleArray)));
    }

    @Test
    public void EmptyStackAndReturns() {

        // Arrange
        Card[] initialArray = {new Card(Suit.CLUBS, 10, Color.BLACK)};
        TableauStack tableauStack = new TableauStack(initialArray);
        List<Card> interleavedCards = new LinkedList<>();
        Suit[] suits = {Suit.CLUBS, Suit.DIAMONDS, Suit.SPADES, Suit.HEARTS};
        Color[] colors = {Color.BLACK, Color.RED};

        for (int i = 13; i > 5; i--) {
            interleavedCards.add(new Card(suits[i % 4], i, colors[i % 2]));
        }

        // Act and assert
        Card card = tableauStack.pop();
        tableauStack.returnCard(card);
        assertFalse(tableauStack.isEmpty());
        tableauStack.pop();
        assertTrue(tableauStack.pushArray(interleavedCards));
        assertEquals(interleavedCards, tableauStack.popArray(7));
        tableauStack.returnArray(interleavedCards);
        assertFalse(tableauStack.isEmpty());
    }
}
