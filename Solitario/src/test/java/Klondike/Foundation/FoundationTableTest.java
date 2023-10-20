package Klondike.Foundation;

import Card.Card;
import Card.Color;
import Card.Deck;
import Card.Suit;
import org.junit.Test;

import static org.junit.Assert.*;

public class FoundationTableTest {

    @Test
    public void invalidOperations() {
        FoundationTable ft = new FoundationTable();
        Card D1 = new Card(Suit.DIAMONDS, 1, Color.RED);
        Card D2 = new Card(Suit.DIAMONDS, 2, Color.RED);
        Card D3 = new Card(Suit.DIAMONDS, 3, Color.RED);
        Card H1 = new Card(Suit.HEARTS, 1, Color.RED);
        Card H2 = new Card(Suit.HEARTS, 2, Color.RED);
        Card H3 = new Card(Suit.HEARTS, 3, Color.RED);

        // Act and assert
        assertNull(ft.get(0));
        assertFalse(ft.verify());
        assertFalse(ft.insert(0, D2));
        assertTrue(ft.insert(1, D1));
        assertFalse(ft.insert(0, D2));
        assertFalse(ft.insert(1, H2));
        assertTrue(ft.insert(1, D2));
        assertFalse(ft.insert(0, H2));
        assertTrue(ft.insert(0, H1));
        assertTrue(ft.insert(0, H2));
        assertTrue(ft.insert(0, H3));
        assertTrue(ft.insert(1, D3));
        assertFalse(ft.verify());
        assertEquals(H3, ft.get(0));
        assertEquals(H2, ft.get(0));
        assertEquals(H1, ft.get(0));
        assertTrue(ft.insert(2, H1));
        assertEquals(D3, ft.get(1));
        assertEquals(D2, ft.get(1));
        assertEquals(D1, ft.get(1));
    }

    @Test
    public void fillAllFoundations() {
        // Arrange
        Card[] d = Deck.createDeck(false);
        FoundationTable ft = new FoundationTable();

        // Act
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 12; j++) {
                ft.insert(i, d[i * 13 + j]);
            }
        }

        // Assert
        assertFalse(ft.verify());
        assertTrue(ft.insert(0, d[12]));
        assertFalse(ft.verify());
        assertTrue(ft.insert(1, d[25]));
        assertFalse(ft.verify());
        assertTrue(ft.insert(2, d[38]));
        assertFalse(ft.verify());
        assertTrue(ft.insert(3, d[51]));
        assertTrue(ft.verify());
    }
}