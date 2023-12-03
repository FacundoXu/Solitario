package Card;

import Spider.Spider;

import java.util.*;

public class Deck {

    static public Card[] createDeck() {
        return createDeck(true);
    }

    static public Card[] createDeck(boolean shuffle) {

        ArrayList<Card> deck = new ArrayList<>();

        for (Suit suit : Suit.values()) {
            for (int i = 0; i < 13; i++) {
                Color color = (suit == Suit.HEARTS || suit == Suit.DIAMONDS) ? Color.RED : Color.BLACK;
                deck.add(new Card(suit, i + 1, color));
            }
        }

        if (shuffle)
            Collections.shuffle(deck, new Random(6));
        return deck.toArray(new Card[0]);
    }

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

    static public Card[] createSpiderVictoryDeck() {
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

        return deck.toArray(new Card[0]);
    }
}