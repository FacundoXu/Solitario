package Spider.Foundation;

import Card.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class FoundationTableTest {

    @Test
    public void verifyWin() {
        // Arrange
        Card[] diamondCards1 = {
                new Card(Suit.DIAMONDS, 13, Color.RED),
                new Card(Suit.DIAMONDS, 12, Color.RED),
                new Card(Suit.DIAMONDS, 11, Color.RED),
                new Card(Suit.DIAMONDS, 10, Color.RED),
                new Card(Suit.DIAMONDS, 9, Color.RED),
                new Card(Suit.DIAMONDS, 8, Color.RED),
                new Card(Suit.DIAMONDS, 7, Color.RED),
                new Card(Suit.DIAMONDS, 6, Color.RED),
                new Card(Suit.DIAMONDS, 5, Color.RED),
                new Card(Suit.DIAMONDS, 4, Color.RED),
                new Card(Suit.DIAMONDS, 3, Color.RED),
                new Card(Suit.DIAMONDS, 2, Color.RED),
                new Card(Suit.DIAMONDS, 1, Color.RED)
        };

        Card[] diamondCards2 = {
                new Card(Suit.DIAMONDS, 13, Color.RED),
                new Card(Suit.DIAMONDS, 12, Color.RED),
                new Card(Suit.DIAMONDS, 11, Color.RED),
                new Card(Suit.DIAMONDS, 10, Color.RED),
                new Card(Suit.DIAMONDS, 9, Color.RED),
                new Card(Suit.DIAMONDS, 8, Color.RED),
                new Card(Suit.DIAMONDS, 7, Color.RED),
                new Card(Suit.DIAMONDS, 6, Color.RED),
                new Card(Suit.DIAMONDS, 5, Color.RED),
                new Card(Suit.DIAMONDS, 4, Color.RED),
                new Card(Suit.DIAMONDS, 3, Color.RED),
                new Card(Suit.DIAMONDS, 2, Color.RED),
                new Card(Suit.DIAMONDS, 1, Color.RED)
        };

        Card[] clubCards1 = {
                new Card(Suit.CLUBS, 13, Color.BLACK),
                new Card(Suit.CLUBS, 12, Color.BLACK),
                new Card(Suit.CLUBS, 11, Color.BLACK),
                new Card(Suit.CLUBS, 10, Color.BLACK),
                new Card(Suit.CLUBS, 9, Color.BLACK),
                new Card(Suit.CLUBS, 8, Color.BLACK),
                new Card(Suit.CLUBS, 7, Color.BLACK),
                new Card(Suit.CLUBS, 6, Color.BLACK),
                new Card(Suit.CLUBS, 5, Color.BLACK),
                new Card(Suit.CLUBS, 4, Color.BLACK),
                new Card(Suit.CLUBS, 3, Color.BLACK),
                new Card(Suit.CLUBS, 2, Color.BLACK),
                new Card(Suit.CLUBS, 1, Color.BLACK)
        };

        Card[] clubCards2 = {
                new Card(Suit.CLUBS, 13, Color.BLACK),
                new Card(Suit.CLUBS, 12, Color.BLACK),
                new Card(Suit.CLUBS, 11, Color.BLACK),
                new Card(Suit.CLUBS, 10, Color.BLACK),
                new Card(Suit.CLUBS, 9, Color.BLACK),
                new Card(Suit.CLUBS, 8, Color.BLACK),
                new Card(Suit.CLUBS, 7, Color.BLACK),
                new Card(Suit.CLUBS, 6, Color.BLACK),
                new Card(Suit.CLUBS, 5, Color.BLACK),
                new Card(Suit.CLUBS, 4, Color.BLACK),
                new Card(Suit.CLUBS, 3, Color.BLACK),
                new Card(Suit.CLUBS, 2, Color.BLACK),
                new Card(Suit.CLUBS, 1, Color.BLACK)
        };

        Card[] heartCards1 = {
                new Card(Suit.HEARTS, 13, Color.RED),
                new Card(Suit.HEARTS, 12, Color.RED),
                new Card(Suit.HEARTS, 11, Color.RED),
                new Card(Suit.HEARTS, 10, Color.RED),
                new Card(Suit.HEARTS, 9, Color.RED),
                new Card(Suit.HEARTS, 8, Color.RED),
                new Card(Suit.HEARTS, 7, Color.RED),
                new Card(Suit.HEARTS, 6, Color.RED),
                new Card(Suit.HEARTS, 5, Color.RED),
                new Card(Suit.HEARTS, 4, Color.RED),
                new Card(Suit.HEARTS, 3, Color.RED),
                new Card(Suit.HEARTS, 2, Color.RED),
                new Card(Suit.HEARTS, 1, Color.RED)
        };

        Card[] heartCards2 = {
                new Card(Suit.HEARTS, 13, Color.RED),
                new Card(Suit.HEARTS, 12, Color.RED),
                new Card(Suit.HEARTS, 11, Color.RED),
                new Card(Suit.HEARTS, 10, Color.RED),
                new Card(Suit.HEARTS, 9, Color.RED),
                new Card(Suit.HEARTS, 8, Color.RED),
                new Card(Suit.HEARTS, 7, Color.RED),
                new Card(Suit.HEARTS, 6, Color.RED),
                new Card(Suit.HEARTS, 5, Color.RED),
                new Card(Suit.HEARTS, 4, Color.RED),
                new Card(Suit.HEARTS, 3, Color.RED),
                new Card(Suit.HEARTS, 2, Color.RED),
                new Card(Suit.HEARTS, 1, Color.RED)
        };

        Card[] spadeCards1 = {
                new Card(Suit.SPADES, 13, Color.BLACK),
                new Card(Suit.SPADES, 12, Color.BLACK),
                new Card(Suit.SPADES, 11, Color.BLACK),
                new Card(Suit.SPADES, 10, Color.BLACK),
                new Card(Suit.SPADES, 9, Color.BLACK),
                new Card(Suit.SPADES, 8, Color.BLACK),
                new Card(Suit.SPADES, 7, Color.BLACK),
                new Card(Suit.SPADES, 6, Color.BLACK),
                new Card(Suit.SPADES, 5, Color.BLACK),
                new Card(Suit.SPADES, 4, Color.BLACK),
                new Card(Suit.SPADES, 3, Color.BLACK),
                new Card(Suit.SPADES, 2, Color.BLACK),
                new Card(Suit.SPADES, 1, Color.BLACK)
        };

        Card[] spadeCards2 = {
                new Card(Suit.SPADES, 13, Color.BLACK),
                new Card(Suit.SPADES, 12, Color.BLACK),
                new Card(Suit.SPADES, 11, Color.BLACK),
                new Card(Suit.SPADES, 10, Color.BLACK),
                new Card(Suit.SPADES, 9, Color.BLACK),
                new Card(Suit.SPADES, 8, Color.BLACK),
                new Card(Suit.SPADES, 7, Color.BLACK),
                new Card(Suit.SPADES, 6, Color.BLACK),
                new Card(Suit.SPADES, 5, Color.BLACK),
                new Card(Suit.SPADES, 4, Color.BLACK),
                new Card(Suit.SPADES, 3, Color.BLACK),
                new Card(Suit.SPADES, 2, Color.BLACK),
                new Card(Suit.SPADES, 1, Color.BLACK)
        };

        Card[][] cards = {
                diamondCards1,
                diamondCards2,
                clubCards1,
                clubCards2,
                heartCards1,
                heartCards2,
                spadeCards1,
                spadeCards2
        };

        FoundationTable foundationTable1 = new FoundationTable();
        FoundationTable foundationTable2 = new FoundationTable();
        FoundationTable foundationTable3 = new FoundationTable();

        Card[] extraSpadeCards = {
                new Card(Suit.SPADES, 13, Color.BLACK),
                new Card(Suit.SPADES, 12, Color.BLACK),
                new Card(Suit.SPADES, 11, Color.BLACK),
                new Card(Suit.SPADES, 10, Color.BLACK),
                new Card(Suit.SPADES, 9, Color.BLACK),
                new Card(Suit.SPADES, 8, Color.BLACK),
                new Card(Suit.SPADES, 7, Color.BLACK),
                new Card(Suit.SPADES, 6, Color.BLACK),
                new Card(Suit.SPADES, 5, Color.BLACK),
                new Card(Suit.SPADES, 4, Color.BLACK),
                new Card(Suit.SPADES, 3, Color.BLACK),
                new Card(Suit.SPADES, 2, Color.BLACK),
                new Card(Suit.SPADES, 1, Color.BLACK)
        };

        // Act and assert
        for (Card[] array : cards) {
            assertTrue(foundationTable1.assignCards(array));
        }

        for (int i = 0; i < 5; i++) {
            assertTrue(foundationTable2.assignCards(cards[i]));
        }

        assertTrue(foundationTable1.gameWon());
        assertFalse(foundationTable2.gameWon());
        assertFalse(foundationTable3.gameWon());
        assertFalse(foundationTable1.assignCards(extraSpadeCards));
        assertFalse(foundationTable2.assignCards(null));
    }
}