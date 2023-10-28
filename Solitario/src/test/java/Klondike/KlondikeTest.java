package Klondike;

import Card.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class KlondikeTest {

    static Card mapCard(String CardS) {
        char[] chars = CardS.toCharArray();

        Suit s = switch (chars[0]) {
            case 'H' -> Suit.HEARTS;
            case 'D' -> Suit.DIAMONDS;
            case 'C' -> Suit.CLUBS;
            case 'S' -> Suit.SPADES;
            default -> throw new IllegalStateException("Unexpected value: " + chars[0]);
        };

        int r = switch (chars[1]) {
            case 'T' -> 10;
            case 'J' -> 11;
            case 'Q' -> 12;
            case 'K' -> 13;
            default -> Integer.parseInt(String.valueOf(chars[1]));
        };

        Color c = (s == Suit.HEARTS || s == Suit.DIAMONDS) ? Color.RED : Color.BLACK;
        return new Card(s, r, c);
    }

    @Test
    public void GameTest() {

        // Arrange
        Card[] tableau1 = {
                mapCard("H2")
        };

        Card[] tableau2 = {
                mapCard("DK"),
                mapCard("HQ")
        };

        Card[] tableau3 = {
                mapCard("S3"),
                mapCard("S1"),
                mapCard("S4"),
        };

        Card[] tableau4 = {
                mapCard("S5"),
                mapCard("H4"),
                mapCard("D5"),
                mapCard("DQ")};

        Card[] tableau5 = {
                mapCard("C6"),
                mapCard("D3"),
                mapCard("D4"),
                mapCard("CK"),
                mapCard("S8")
        };

        Card[] tableau6 = {
                mapCard("DT"),
                mapCard("SQ"),
                mapCard("D1"),
                mapCard("H1"),
                mapCard("H7"),
                mapCard("ST")
        };

        Card[] tableau7 = {
                mapCard("H5"),
                mapCard("D2"),
                mapCard("SJ"),
                mapCard("CJ"),
                mapCard("C4"),
                mapCard("HJ"),
                mapCard("DJ")
        };

        Card[] stock = {
                mapCard("C3"),
                mapCard("S2"),
                mapCard("C2"),
                mapCard("S6"),
                mapCard("S7"),
                mapCard("C5"),
                mapCard("C7"),
                mapCard("D6"),
                mapCard("C1"),
                mapCard("H8"),
                mapCard("HK"),
                mapCard("SK"),
                mapCard("C8"),
                mapCard("H3"),
                mapCard("D8"),
                mapCard("CT"),
                mapCard("S9"),
                mapCard("C9"),
                mapCard("D9"),
                mapCard("D7"),
                mapCard("CQ"),
                mapCard("H9"),
                mapCard("HT"),
                mapCard("H6")
        };

        List<Card> deck = new ArrayList<>();
        deck.addAll(Arrays.asList(tableau1));
        deck.addAll(Arrays.asList(tableau2));
        deck.addAll(Arrays.asList(tableau3));
        deck.addAll(Arrays.asList(tableau4));
        deck.addAll(Arrays.asList(tableau5));
        deck.addAll(Arrays.asList(tableau6));
        deck.addAll(Arrays.asList(tableau7));
        deck.addAll(Arrays.asList(stock));
        Card[] deckArray = deck.toArray(new Card[0]);
        Klondike klondike = new Klondike(deckArray);

        // Act and assert
        assertTrue(klondike.moveTableauToTableau(0, 5, 6));
        assertTrue(klondike.moveTableauToTableau(0, 5, 4));
        assertTrue(klondike.moveTableauToFoundation(5, 0));
        assertTrue(klondike.moveTableauToFoundation(5, 1));
        assertTrue(klondike.moveTableauToFoundation(0, 0));
        assertEquals(mapCard("H6"), klondike.stockNextCard());
        assertEquals(mapCard("HT"), klondike.stockNextCard());
        assertEquals(mapCard("H9"), klondike.stockNextCard());
        assertTrue(klondike.moveStockToTableau(6));
        assertTrue(klondike.moveTableauToTableau(1, 4, 6));
        assertTrue(klondike.moveTableauToTableau(0, 4, 0));
        assertTrue(klondike.moveTableauToTableau(0, 1, 0));
        assertTrue(klondike.moveTableauToTableau(0, 5, 1));
        assertFalse(klondike.moveTableauToTableau(3, 6, 1));
        assertTrue(klondike.moveTableauToTableau(4, 6, 1));
        klondike.stockNextCard();
        klondike.stockNextCard();

        // Persistence
        klondike.saveGame();
        klondike.stockNextCard();
        klondike.loadGame();

        klondike.stockNextCard();
        klondike.stockNextCard();
        assertTrue(klondike.moveStockToTableau(5));
        klondike.stockNextCard();
        klondike.stockNextCard();
        assertTrue(klondike.moveStockToTableau(6));
        klondike.stockNextCard();
        assertTrue(klondike.moveStockToTableau(5));
        klondike.stockNextCard();
        assertTrue(klondike.moveStockToFoundation(0));
        klondike.stockNextCard();
        klondike.stockNextCard();
        klondike.stockNextCard();
        klondike.stockNextCard();
        klondike.stockNextCard();
        assertTrue(klondike.moveStockToFoundation(2));
        klondike.stockNextCard();
        klondike.stockNextCard();
        assertTrue(klondike.moveStockToTableau(5));
        assertTrue(klondike.moveStockToTableau(5));
        klondike.stockNextCard();
        assertTrue(klondike.moveStockToTableau(5));
        assertTrue(klondike.moveTableauToTableau(0, 4, 5));
        assertTrue(klondike.moveTableauToTableau(0, 4, 2));
        assertTrue(klondike.moveTableauToTableau(0, 4, 1));
        klondike.stockNextCard();
        klondike.stockNextCard();
        klondike.stockNextCard();
        assertTrue(klondike.moveStockToFoundation(2));
        klondike.stockNextCard();
        klondike.stockNextCard();
        assertTrue(klondike.moveStockToFoundation(2));
        klondike.stockNextCard();
        klondike.stockNextCard();
        klondike.stockNextCard();
        klondike.stockNextCard();
        klondike.stockNextCard();
        klondike.stockNextCard();
        assertTrue(klondike.moveStockToTableau(6));
        klondike.stockNextCard();
        klondike.stockNextCard();
        assertTrue(klondike.moveStockToTableau(6));
        klondike.stockNextCard();
        assertTrue(klondike.moveStockToTableau(4));
        assertTrue(klondike.moveTableauToTableau(0, 3, 4));
        assertTrue(klondike.moveTableauToTableau(1, 2, 3));
        assertTrue(klondike.moveTableauToTableau(2, 3, 1));
        assertTrue(klondike.moveTableauToFoundation(2, 3));
        assertTrue(klondike.moveTableauToFoundation(3, 0));
        assertTrue(klondike.moveTableauToTableau(0, 2, 5));
        klondike.stockNextCard();
        assertTrue(klondike.moveStockToTableau(2));
        klondike.stockNextCard();
        klondike.stockNextCard();
        klondike.stockNextCard();
        klondike.stockNextCard();
        assertTrue(klondike.moveStockToFoundation(3));
        assertTrue(klondike.moveTableauToFoundation(5, 3));
        klondike.stockNextCard();
        klondike.stockNextCard();
        klondike.stockNextCard();
        klondike.stockNextCard();
        assertTrue(klondike.moveStockToTableau(2));
        assertTrue(klondike.moveTableauToTableau(3, 6, 2));
        assertTrue(klondike.moveTableauToFoundation(6, 2));
        assertTrue(klondike.moveTableauToTableau(0, 6, 4));
        assertTrue(klondike.moveTableauToTableau(0, 6, 0));
        assertTrue(klondike.moveTableauToFoundation(6, 1));
        assertTrue(klondike.moveTableauToFoundation(6, 0));
        assertTrue(klondike.moveTableauToFoundation(1, 1));
        assertTrue(klondike.moveTableauToFoundation(5, 1));
        assertTrue(klondike.moveTableauToFoundation(5, 2));
        assertTrue(klondike.moveTableauToFoundation(1, 3));
        assertTrue(klondike.moveTableauToFoundation(1, 1));
        assertTrue(klondike.moveTableauToFoundation(5, 1));
        assertTrue(klondike.moveTableauToTableau(3, 5, 4));
        assertTrue(klondike.moveStockToTableau(0));
        assertTrue(klondike.moveStockToTableau(4));
        assertTrue(klondike.moveTableauToTableau(0, 3, 4));
        assertTrue(klondike.moveTableauToTableau(8, 4, 3));
        klondike.stockNextCard();
        assertTrue(klondike.moveStockToTableau(2));
        klondike.stockNextCard();
        assertTrue(klondike.moveStockToTableau(0));
        klondike.stockNextCard();
        assertTrue(klondike.moveStockToTableau(0));
        klondike.stockNextCard();
        assertTrue(klondike.moveStockToTableau(0));
        klondike.stockNextCard();
        assertTrue(klondike.moveStockToTableau(2));
        assertFalse(klondike.verifyVictory());
        assertTrue(klondike.moveTableauToFoundation(3, 3));
        assertTrue(klondike.moveTableauToFoundation(3, 0));
        assertTrue(klondike.moveTableauToFoundation(2, 3));
        assertTrue(klondike.moveTableauToFoundation(1, 2));
        assertTrue(klondike.moveTableauToFoundation(0, 3));
        assertTrue(klondike.moveTableauToFoundation(1, 0));
        assertTrue(klondike.moveTableauToFoundation(2, 1));
        assertTrue(klondike.moveTableauToFoundation(3, 2));
        assertTrue(klondike.moveTableauToFoundation(3, 1));
        assertTrue(klondike.moveTableauToFoundation(2, 2));
        assertTrue(klondike.moveTableauToFoundation(1, 3));
        assertFalse(klondike.moveTableauToFoundation(0, 1));
        assertTrue(klondike.moveTableauToFoundation(0, 0));
        assertTrue(klondike.moveTableauToFoundation(0, 3));
        assertTrue(klondike.moveTableauToFoundation(1, 0));
        assertTrue(klondike.moveTableauToFoundation(2, 1));
        assertTrue(klondike.moveTableauToFoundation(3, 2));
        assertTrue(klondike.moveTableauToFoundation(3, 1));
        assertTrue(klondike.moveTableauToFoundation(2, 2));
        assertTrue(klondike.moveTableauToFoundation(1, 3));
        assertTrue(klondike.moveTableauToFoundation(0, 0));
        assertTrue(klondike.moveTableauToFoundation(0, 3));
        assertTrue(klondike.moveTableauToFoundation(1, 1));
        assertTrue(klondike.moveTableauToFoundation(2, 0));
        assertTrue(klondike.moveTableauToFoundation(3, 2));
        assertTrue(klondike.moveTableauToFoundation(0, 0));
        assertTrue(klondike.moveTableauToFoundation(1, 3));
        assertTrue(klondike.moveTableauToFoundation(2, 2));
        assertTrue(klondike.moveTableauToFoundation(3, 1));
        assertTrue(klondike.moveTableauToFoundation(3, 3));
        assertTrue(klondike.moveTableauToFoundation(2, 0));
        assertTrue(klondike.moveTableauToFoundation(1, 1));
        assertTrue(klondike.moveTableauToFoundation(0, 2));
        assertTrue(klondike.verifyVictory());
    }
}