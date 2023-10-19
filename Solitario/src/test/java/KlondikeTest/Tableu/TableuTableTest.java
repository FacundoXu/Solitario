package KlondikeTest.Tableu;

import Card.Card;
import Card.Color;
import Card.Deck;
import Card.Suit;
import Klondike.Tableu.TableuTable;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class TableuTableTest {

    Card[] deck = Deck.createDeck(false);

    @Test
    public void moveCards() {
        // Arrange
        // Top Cards: H1 H3 H6 H10 D2 D8 C2
        Card[] TopCards = {deck[0], deck[2], deck[5], deck[9], deck[14], deck[20], deck[27]};
        TableuTable tt = new TableuTable(Arrays.copyOfRange(deck, 0, 28));
        Card C9 = new Card(Suit.CLUBS, 9, Color.BLACK);
        Card C7 = new Card(Suit.CLUBS, 7, Color.BLACK);
        Card H6 = new Card(Suit.HEARTS, 6, Color.RED);
        Card S6 = new Card(Suit.SPADES, 6, Color.BLACK);
        Card SK = new Card(Suit.SPADES, 13, Color.BLACK);

        // Act and assert
        assertArrayEquals(TopCards, tt.peek());
        assertTrue(tt.move(0, 0, 6));
        assertTrue(tt.insert(3, C9));
        assertFalse(tt.insert(1, SK));
        assertTrue(tt.insert(0, SK));
        assertTrue(tt.insert(5, C7));
        assertTrue(tt.move(1, 5, 3));
        assertFalse(tt.insert(3, S6));
        assertTrue(tt.insert(3, H6));
        assertEquals(H6, tt.pickUp(3));
        tt.returnCard(3, H6);
        assertEquals(H6, tt.peek(3));
    }
}