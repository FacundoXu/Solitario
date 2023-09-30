import org.junit.Test;
import Card.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class KlondikeTest {

    static Card mapCard(String CardS){
        char[] chars = CardS.toCharArray();
        Suit s = switch (chars[0]) {
            case 'H' -> Suit.HEARTS;
            case 'D' -> Suit.DIAMONDS;
            case 'C' -> Suit.CLUBS;
            case 'S' -> Suit.SPADES;
            default -> throw new IllegalStateException("Unexpected value: " + chars[0]);
        };
        int r = switch (chars[1]){
            case 'T' -> 10;
            case 'J' -> 11;
            case 'Q' -> 12;
            case 'K' -> 13;
            default -> Integer.parseInt(String.valueOf(chars[1]));
        };
        Color c =(s == Suit.HEARTS || s == Suit.DIAMONDS) ? Color.RED : Color.BLACK;
        return new Card(s,r,c);
    }
    @Test
    public void GameTest(){
        //Arrange
        Card[] tableu1 = {mapCard("H2")};
        Card[] tableu2 = {mapCard("DK"),
                mapCard("HQ")};
        Card[] tableu3 = {mapCard("S3"),
                mapCard("S1"),
                mapCard("S4"),
        };
        Card[] tableu4 = {mapCard("S5"),
                mapCard("H4"),
                mapCard("D5"),
                mapCard("DQ")};
        Card[] tableu5 = {mapCard("C6"),
                mapCard("D3"),
                mapCard("D4"),
                mapCard("CK"),
                mapCard("S8")
        };
        Card[] tableu6 = {mapCard("DT"),
                mapCard("SQ"),
                mapCard("D1"),
                mapCard("H1"),
                mapCard("H7"),
                mapCard("ST")};
        Card[] tableu7 = {mapCard("H5"),
                        mapCard("D2"),
                        mapCard("SJ"),
                        mapCard("CJ"),
                        mapCard("C4"),
                        mapCard("HJ"),
                        mapCard("DJ")};
        Card[] stock = {mapCard("C3"),
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
                mapCard("H6")};

        List<Card> deck = new ArrayList<Card>();
        deck.addAll(Arrays.asList(tableu1));
        deck.addAll(Arrays.asList(tableu2));
        deck.addAll(Arrays.asList(tableu3));
        deck.addAll(Arrays.asList(tableu4));
        deck.addAll(Arrays.asList(tableu5));
        deck.addAll(Arrays.asList(tableu6));
        deck.addAll(Arrays.asList(tableu7));
        deck.addAll(Arrays.asList(stock));
        Card[] deckArray = deck.toArray(new Card[0]);
        Klondike k = new Klondike(deckArray);

        assertTrue(k.moveTableuToTableu(0,5, 6));
        assertTrue(k.moveTableuToTableu(0,5, 4));
        assertTrue(k.moveTableuToFoundation(5,0));
        assertTrue(k.moveTableuToFoundation(5,1));
        assertTrue(k.moveTableuToFoundation(0,0));
        assertEquals(mapCard("H6"),k.stockNextCard());
        assertEquals(mapCard("HT"),k.stockNextCard());
        assertEquals(mapCard("H9"),k.stockNextCard());
        assertTrue(k.moveStockToTableu(6));
        assertTrue(k.moveTableuToTableu(1,4, 6));
        assertTrue(k.moveTableuToTableu(0,4, 0));
        assertTrue(k.moveTableuToTableu(0,1, 0));
        assertTrue(k.moveTableuToTableu(0,5, 1));
        assertFalse(k.moveTableuToTableu(3,6, 1));
        assertTrue(k.moveTableuToTableu(4,6, 1));
        k.stockNextCard();
        k.stockNextCard();
        k.stockNextCard();
        k.stockNextCard();
        assertTrue(k.moveStockToTableu(5));
        k.stockNextCard();
        k.stockNextCard();
        assertTrue(k.moveStockToTableu(6));
        k.stockNextCard();
        assertTrue(k.moveStockToTableu(5));
        k.stockNextCard();
        assertTrue(k.moveStockToFoundation(0));
        k.stockNextCard();
        k.stockNextCard();
        k.stockNextCard();
        k.stockNextCard();
        k.stockNextCard();
        assertTrue(k.moveStockToFoundation(2));
        k.stockNextCard();
        k.stockNextCard();
        assertTrue(k.moveStockToTableu(5));
        assertTrue(k.moveStockToTableu(5));
        k.stockNextCard();
        assertTrue(k.moveStockToTableu(5));
        assertTrue(k.moveTableuToTableu(0, 4, 5));
        assertTrue(k.moveTableuToTableu(0, 4, 2));
        assertTrue(k.moveTableuToTableu(0, 4, 1));
        k.stockNextCard();
        k.stockNextCard();
        k.stockNextCard();
        assertTrue(k.moveStockToFoundation(2));
        k.stockNextCard();
        k.stockNextCard();
        assertTrue(k.moveStockToFoundation(2));
        k.stockNextCard();
        k.stockNextCard();
        k.stockNextCard();
        k.stockNextCard();
        k.stockNextCard();
        k.stockNextCard();
        assertTrue(k.moveStockToTableu(6));
        k.stockNextCard();
        k.stockNextCard();
        assertTrue(k.moveStockToTableu(6));
        k.stockNextCard();
        assertTrue(k.moveStockToTableu(4));
        assertTrue(k.moveTableuToTableu(0, 3, 4));
        assertTrue(k.moveTableuToTableu(1, 2, 3));
        assertTrue(k.moveTableuToTableu(2, 3, 1));
        assertTrue(k.moveTableuToFoundation(2, 3));
        assertTrue(k.moveTableuToFoundation(3, 0));
        assertTrue(k.moveTableuToTableu(0, 2, 5));
        k.stockNextCard();
        assertTrue(k.moveStockToTableu(2));
        k.stockNextCard();
        k.stockNextCard();
        k.stockNextCard();
        k.stockNextCard();
        assertTrue(k.moveStockToFoundation(3));
        assertTrue(k.moveTableuToFoundation(5,3));
        k.stockNextCard();
        k.stockNextCard();
        k.stockNextCard();
        k.stockNextCard();
        assertTrue(k.moveStockToTableu(2));
        assertTrue(k.moveTableuToTableu(3, 6,2));
        assertTrue(k.moveTableuToFoundation(6, 2));
        assertTrue(k.moveTableuToTableu(0, 6,4));
        assertTrue(k.moveTableuToTableu(0, 6,0));
        assertTrue(k.moveTableuToFoundation(6, 1));
        assertTrue(k.moveTableuToFoundation(6, 0));
        assertTrue(k.moveTableuToFoundation(1, 1));
        assertTrue(k.moveTableuToFoundation(5, 1));
        assertTrue(k.moveTableuToFoundation(5, 2));
        assertTrue(k.moveTableuToFoundation(1, 3));
        assertTrue(k.moveTableuToFoundation(1, 1));
        assertTrue(k.moveTableuToFoundation(5, 1));
        assertTrue(k.moveTableuToTableu(3, 5,4));
        assertTrue(k.moveStockToTableu(0));
        assertTrue(k.moveStockToTableu(4));
        assertTrue(k.moveTableuToTableu(0, 3,4));
        assertTrue(k.moveTableuToTableu(8, 4,3));
        k.stockNextCard();
        assertTrue(k.moveStockToTableu(2));
        k.stockNextCard();
        assertTrue(k.moveStockToTableu(0));
        k.stockNextCard();
        assertTrue(k.moveStockToTableu(0));
        k.stockNextCard();
        assertTrue(k.moveStockToTableu(0));
        k.stockNextCard();
        assertTrue(k.moveStockToTableu(2));
        assertFalse(k.verifyVictory());
        assertTrue(k.moveTableuToFoundation(3,3));
        assertTrue(k.moveTableuToFoundation(3,0));
        assertTrue(k.moveTableuToFoundation(2,3));
        assertTrue(k.moveTableuToFoundation(1,2));
        assertTrue(k.moveTableuToFoundation(0,3));
        assertTrue(k.moveTableuToFoundation(1,0));
        assertTrue(k.moveTableuToFoundation(2,1));
        assertTrue(k.moveTableuToFoundation(3,2));
        assertTrue(k.moveTableuToFoundation(3,1));
        assertTrue(k.moveTableuToFoundation(2,2));
        assertTrue(k.moveTableuToFoundation(1,3));
        assertFalse(k.moveTableuToFoundation(0,1));
        assertTrue(k.moveTableuToFoundation(0,0));
        assertTrue(k.moveTableuToFoundation(0,3));
        assertTrue(k.moveTableuToFoundation(1,0));
        assertTrue(k.moveTableuToFoundation(2,1));
        assertTrue(k.moveTableuToFoundation(3,2));
        assertTrue(k.moveTableuToFoundation(3,1));
        assertTrue(k.moveTableuToFoundation(2,2));
        assertTrue(k.moveTableuToFoundation(1,3));
        assertTrue(k.moveTableuToFoundation(0,0));
        assertTrue(k.moveTableuToFoundation(0,3));
        assertTrue(k.moveTableuToFoundation(1,1));
        assertTrue(k.moveTableuToFoundation(2,0));
        assertTrue(k.moveTableuToFoundation(3,2));
        assertTrue(k.moveTableuToFoundation(0,0));
        assertTrue(k.moveTableuToFoundation(1,3));
        assertTrue(k.moveTableuToFoundation(2,2));
        assertTrue(k.moveTableuToFoundation(3,1));
        assertTrue(k.moveTableuToFoundation(3,3));
        assertTrue(k.moveTableuToFoundation(2,0));
        assertTrue(k.moveTableuToFoundation(1,1));
        assertTrue(k.moveTableuToFoundation(0,2));
        assertTrue(k.verifyVictory());
    }

}