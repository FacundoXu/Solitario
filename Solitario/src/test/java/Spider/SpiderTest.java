package Spider;

import Card.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class SpiderTest {

    static Card mapCard(String cardStr) {
        char suitChar = cardStr.charAt(0);
        String rankStr = cardStr.substring(1);

        Suit suit = switch (suitChar) {
            case 'H' -> Suit.HEARTS;
            case 'D' -> Suit.DIAMONDS;
            case 'C' -> Suit.CLUBS;
            case 'S' -> Suit.SPADES;
            default -> throw new IllegalArgumentException("Invalid suit: " + suitChar);
        };

        int rank;

        switch (rankStr) {
            case "A" -> rank = 1;
            case "K" -> rank = 13;
            case "Q" -> rank = 12;
            case "J" -> rank = 11;

            default -> {
                try {
                    rank = Integer.parseInt(rankStr);

                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid rank: " + rankStr);
                }
            }
        }

        Color color = (suit == Suit.HEARTS || suit == Suit.DIAMONDS) ? Color.RED : Color.BLACK;
        return new Card(suit, rank, color);
    }

    @Test
    public void gameMovesTest() {

        // Arrange
        Card[] tableau1 = {
                mapCard("S3"),
                mapCard("H1"),
                mapCard("D4"),
                mapCard("C10"),
                mapCard("S1"),
                mapCard("H2")
        };

        Card[] tableau2 = {
                mapCard("S5"),
                mapCard("H11"),
                mapCard("C5"),
                mapCard("S8"),
                mapCard("D13"),
                mapCard("H6")
        };

        Card[] tableau3 = {
                mapCard("SJ"),
                mapCard("S4"),
                mapCard("D9"),
                mapCard("H3"),
                mapCard("C7"),
                mapCard("S10")
        };

        Card[] tableau4 = {
                mapCard("S4"),
                mapCard("D11"),
                mapCard("C8"),
                mapCard("S2"),
                mapCard("H13"),
                mapCard("D7")
        };

        Card[] tableau5 = {
                mapCard("S2"),
                mapCard("D10"),
                mapCard("H7"),
                mapCard("C2"),
                mapCard("S9")
        };

        Card[] tableau6 = {
                mapCard("S8"),
                mapCard("D6"),
                mapCard("S1"),
                mapCard("H8"),
                mapCard("C12")
        };

        Card[] tableau7 = {
                mapCard("S7"),
                mapCard("S6"),
                mapCard("C9"),
                mapCard("H12"),
                mapCard("D4")
        };

        Card[] tableau8 = {
                mapCard("S6"),
                mapCard("H10"),
                mapCard("D3"),
                mapCard("C11"),
                mapCard("S7")
        };

        Card[] tableau9 = {
                mapCard("S10"),
                mapCard("C6"),
                mapCard("D1"),
                mapCard("H3"),
                mapCard("H3")
        };

        Card[] tableau10 = {
                mapCard("S9"),
                mapCard("S1"),
                mapCard("D13"),
                mapCard("S5"),
                mapCard("C9")
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
                mapCard("C10"),
                mapCard("S9"),
                mapCard("C9"),
                mapCard("D9"),
                mapCard("D7"),
                mapCard("CQ"),
                mapCard("H9"),
                mapCard("H10"),
                mapCard("H6")
        };

        List<Card> deck = new ArrayList<>();
        deck.addAll(Arrays.asList(stock));
        deck.addAll(Arrays.asList(tableau10));
        deck.addAll(Arrays.asList(tableau9));
        deck.addAll(Arrays.asList(tableau8));
        deck.addAll(Arrays.asList(tableau7));
        deck.addAll(Arrays.asList(tableau6));
        deck.addAll(Arrays.asList(tableau5));
        deck.addAll(Arrays.asList(tableau4));
        deck.addAll(Arrays.asList(tableau3));
        deck.addAll(Arrays.asList(tableau2));
        deck.addAll(Arrays.asList(tableau1));
        Card[] deckArray = deck.toArray(new Card[0]);
        Spider spider = new Spider(deckArray);

        // Act
        boolean moveTopCard1 = spider.moveTopCardToTableau(8, 9);
        boolean moveTopCard2 = spider.moveTopCardToTableau(9, 8);
        boolean moveTopCard3 = spider.moveTopCardToTableau(8, 9);
        boolean moveTopCard4 = spider.moveTopCardToTableau(5, 8);
        boolean moveTopCard5 = spider.moveTopCardToTableau(6, 8);
        boolean moveTopCard6 = spider.moveTopCardToTableau(7, 8);
        boolean moveTopCard7 = spider.moveTopCardToTableau(1, 8);
        boolean moveTopCard8 = spider.moveTopCardToTableau(3, 8);
        boolean moveTopCard9 = spider.moveTopCardToTableau(2, 8);
        boolean moveTopCard10 = spider.moveTopCardToTableau(0, 8);
        boolean moveTopCard11 = spider.moveTopCardToTableau(4, 8);
        boolean moveTopCard12 = spider.moveTopCardToTableau(9, 8);
        boolean moveTableauToTableau1 = spider.moveTableauToTableau(8, 0, 2);
        boolean gameWon = spider.verifyVictory();

        // Assert
        assertFalse(moveTopCard1);
        assertTrue(moveTopCard2);
        assertFalse(moveTopCard3);
        assertTrue(moveTopCard4);
        assertTrue(moveTopCard5);
        assertTrue(moveTopCard6);
        assertTrue(moveTopCard7);
        assertTrue(moveTopCard8);
        assertFalse(moveTopCard9);
        assertTrue(moveTopCard10);
        assertTrue(moveTopCard11);
        assertTrue(moveTopCard12);
        assertTrue(moveTableauToTableau1);
        assertFalse(gameWon);
    }

    @Test
    public void gameVictoryTest() {

        // Arrange
        Card[] tableau1 = {
                mapCard("H13"),
                mapCard("H1"),
                mapCard("D4"),
                mapCard("C10"),
                mapCard("S1"),
                mapCard("H2")
        };

        Card[] tableau2 = {
                mapCard("H13"),
                mapCard("H11"),
                mapCard("C5"),
                mapCard("S8"),
                mapCard("D13"),
                mapCard("H6")
        };

        Card[] tableau3 = {
                mapCard("S13"),
                mapCard("S4"),
                mapCard("D9"),
                mapCard("H3"),
                mapCard("C7"),
                mapCard("S10")
        };

        Card[] tableau4 = {
                mapCard("S13"),
                mapCard("D11"),
                mapCard("C8"),
                mapCard("S2"),
                mapCard("H13"),
                mapCard("D7")
        };

        Card[] tableau5 = {
                mapCard("D13"),
                mapCard("D10"),
                mapCard("H7"),
                mapCard("C2"),
                mapCard("S9")
        };

        Card[] tableau6 = {
                mapCard("D13"),
                mapCard("D6"),
                mapCard("S1"),
                mapCard("H8"),
                mapCard("C12")
        };

        Card[] tableau7 = {
                mapCard("C13"),
                mapCard("S6"),
                mapCard("C9"),
                mapCard("H12"),
                mapCard("D4")
        };

        Card[] tableau8 = {
                mapCard("C13"),
                mapCard("H10"),
                mapCard("D3"),
                mapCard("C11"),
                mapCard("S7")
        };

        Card[] tableau9 = {
                mapCard("S10"),
                mapCard("C6"),
                mapCard("D1"),
                mapCard("H3"),
                mapCard("H3")
        };

        Card[] tableau10 = {
                mapCard("S9"),
                mapCard("S1"),
                mapCard("D13"),
                mapCard("S5"),
                mapCard("C9")
        };

        Card[] stock = {
                mapCard("C1"),
                mapCard("C1"),
                mapCard("C1"),
                mapCard("C1"),
                mapCard("D1"),
                mapCard("D1"),
                mapCard("S1"),
                mapCard("S1"),
                mapCard("H1"),
                mapCard("H1"),


                mapCard("C2"),
                mapCard("C2"),
                mapCard("C2"),
                mapCard("C2"),
                mapCard("D2"),
                mapCard("D2"),
                mapCard("S2"),
                mapCard("S2"),
                mapCard("H2"),
                mapCard("H2"),


                mapCard("C3"),
                mapCard("C3"),
                mapCard("C3"),
                mapCard("C3"),
                mapCard("D3"),
                mapCard("D3"),
                mapCard("S3"),
                mapCard("S3"),
                mapCard("H3"),
                mapCard("H3"),


                mapCard("C4"),
                mapCard("C4"),
                mapCard("C4"),
                mapCard("C4"),
                mapCard("D4"),
                mapCard("D4"),
                mapCard("S4"),
                mapCard("S4"),
                mapCard("H4"),
                mapCard("H4"),


                mapCard("C5"),
                mapCard("C5"),
                mapCard("C5"),
                mapCard("C5"),
                mapCard("D5"),
                mapCard("D5"),
                mapCard("S5"),
                mapCard("S5"),
                mapCard("H5"),
                mapCard("H5"),


                mapCard("C6"),
                mapCard("C6"),
                mapCard("C6"),
                mapCard("C6"),
                mapCard("D6"),
                mapCard("D6"),
                mapCard("S6"),
                mapCard("S6"),
                mapCard("H6"),
                mapCard("H6"),


                mapCard("C7"),
                mapCard("C7"),
                mapCard("C7"),
                mapCard("C7"),
                mapCard("D7"),
                mapCard("D7"),
                mapCard("S7"),
                mapCard("S7"),
                mapCard("H7"),
                mapCard("H7"),


                mapCard("C8"),
                mapCard("C8"),
                mapCard("C8"),
                mapCard("C8"),
                mapCard("D8"),
                mapCard("D8"),
                mapCard("S8"),
                mapCard("S8"),
                mapCard("H8"),
                mapCard("H8"),


                mapCard("C9"),
                mapCard("C9"),
                mapCard("C9"),
                mapCard("C9"),
                mapCard("D9"),
                mapCard("D9"),
                mapCard("S9"),
                mapCard("S9"),
                mapCard("H9"),
                mapCard("H9"),


                mapCard("C10"),
                mapCard("C10"),
                mapCard("C10"),
                mapCard("C10"),
                mapCard("D10"),
                mapCard("D10"),
                mapCard("S10"),
                mapCard("S10"),
                mapCard("H10"),
                mapCard("H10"),


                mapCard("C11"),
                mapCard("C11"),
                mapCard("C11"),
                mapCard("C11"),
                mapCard("D11"),
                mapCard("D11"),
                mapCard("S11"),
                mapCard("S11"),
                mapCard("H11"),
                mapCard("H11"),


                mapCard("C12"),
                mapCard("C12"),
                mapCard("C12"),
                mapCard("C12"),
                mapCard("D12"),
                mapCard("D12"),
                mapCard("S12"),
                mapCard("S12"),
                mapCard("H12"),
                mapCard("H12"),
        };

        List<Card> deck = new ArrayList<>();
        deck.addAll(Arrays.asList(stock));
        deck.addAll(Arrays.asList(tableau10));
        deck.addAll(Arrays.asList(tableau9));
        deck.addAll(Arrays.asList(tableau8));
        deck.addAll(Arrays.asList(tableau7));
        deck.addAll(Arrays.asList(tableau6));
        deck.addAll(Arrays.asList(tableau5));
        deck.addAll(Arrays.asList(tableau4));
        deck.addAll(Arrays.asList(tableau3));
        deck.addAll(Arrays.asList(tableau2));
        deck.addAll(Arrays.asList(tableau1));
        Card[] deckArray = deck.toArray(new Card[0]);
        Spider spider1 = new Spider(deckArray);
        Spider spider2 = new Spider();

        // Act
        spider1.drawStockCards();
        spider1.drawStockCards();
        spider1.drawStockCards();
        spider1.drawStockCards();
        spider1.drawStockCards();
        spider1.addWonColumnsToFoundations();
        boolean gameWon1 = spider1.verifyVictory();
        spider1.drawStockCards();
        spider1.drawStockCards();
        spider1.drawStockCards();
        spider1.drawStockCards();
        spider1.drawStockCards();
        spider1.drawStockCards();
        spider1.drawStockCards();
        boolean gameWon2 = spider1.verifyVictory();
        spider1.addWonColumnsToFoundations();
        boolean gameWon3 = spider1.verifyVictory();
        boolean gameWon4 = spider2.verifyVictory();

        // Assert
        assertFalse(gameWon1);
        assertFalse(gameWon2);
        assertTrue(gameWon3);
        assertFalse(gameWon4);
    }
}