package Klondike.Tableau;

import Card.*;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class TableauTableTest {

    Card[] deck = Deck.createDeck(false);

    @Test
    public void moveCards() {

        // Arrange
        // Top Cards: H1 H3 H6 H10 D2 D8 C2
        Card[] TopCards = {deck[0], deck[2], deck[5], deck[9], deck[14], deck[20], deck[27]};
        TableauTable tableauTable = new TableauTable(Arrays.copyOfRange(deck, 0, 28));
        Card C9 = new Card(Suit.CLUBS, 9, Color.BLACK);
        Card C7 = new Card(Suit.CLUBS, 7, Color.BLACK);
        Card H6 = new Card(Suit.HEARTS, 6, Color.RED);
        Card S6 = new Card(Suit.SPADES, 6, Color.BLACK);
        Card SK = new Card(Suit.SPADES, 13, Color.BLACK);

        // Act and assert
        assertArrayEquals(TopCards, tableauTable.peek());
        assertTrue(tableauTable.move(0, 0, 6));
        assertTrue(tableauTable.insert(3, C9));
        assertFalse(tableauTable.insert(1, SK));
        assertTrue(tableauTable.insert(0, SK));
        assertTrue(tableauTable.insert(5, C7));
        assertTrue(tableauTable.move(1, 5, 3));
        assertFalse(tableauTable.insert(3, S6));
        assertTrue(tableauTable.insert(3, H6));
        assertEquals(H6, tableauTable.pickUp(3));
        tableauTable.returnCard(3, H6);
        assertEquals(H6, tableauTable.peek(3));
    }
}