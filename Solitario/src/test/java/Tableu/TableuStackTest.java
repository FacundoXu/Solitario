package Tableu;

import Card.*;
import Tableu.TableuStack;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import static org.junit.Assert.*;

public class TableuStackTest {
    @Test
    public void oneCard() {
        // Arrange
        Card[] initialArray = {new Card(Suit.CLUBS, 8, Color.BLACK)};
        TableuStack s = new TableuStack(initialArray);
        Card c1 = new Card(Suit.DIAMONDS, 7, Color.RED);
        Card c2 = new Card(Suit.DIAMONDS, 6, Color.RED);
        Card c3 = new Card(Suit.CLUBS, 6, Color.BLACK);
        Card c4 = new Card(Suit.DIAMONDS, 10, Color.RED);

        // Act
        boolean push1 = s.push(c1);
        Card peek1 = s.peek();
        boolean push2 = s.push(c2);
        boolean push3 = s.push(c3);
        boolean push4 = s.push(c4);

        // Assert
        assertEquals(true, push1);
        assertEquals(false, push2);
        assertEquals(true, push3);
        assertEquals(false, push4);
    }

    @Test
    public void multipleCards() {
        // Arrange
        Card[] initialArray = {new Card(Suit.CLUBS, 10, Color.BLACK)};
        TableuStack s = new TableuStack(initialArray);
        List<Card> interleavedCards = new LinkedList<>();
        Suit[] suits = {Suit.CLUBS, Suit.DIAMONDS, Suit.SPADES, Suit.HEARTS};
        Color[] colors = {Color.BLACK, Color.RED};

        for (int i = 9; i > 0; i--){
            interleavedCards.add(new Card(suits[i%4], i, colors[i%2]));
        }

        Card[] sampleArray = {
                new Card(Suit.HEARTS, 7, Color.RED),
                new Card(Suit.CLUBS, 6, Color.BLACK),
                new Card(Suit.DIAMONDS, 5, Color.RED),
        };

        // Act and Assert
        for (Card c: interleavedCards) {
            assertTrue(s.push(c));
        }
        assertEquals(interleavedCards.subList(2,interleavedCards.size()), s.popArray(6));
        assertTrue(s.pushArray(Arrays.asList(sampleArray)));
    }

    @Test
    public void EmptyStackAndReturns(){
        // Arrange
        Card[] initialArray = {new Card(Suit.CLUBS, 10, Color.BLACK)};
        TableuStack s = new TableuStack(initialArray);
        List<Card> interleavedCards = new LinkedList<>();
        Suit[] suits = {Suit.CLUBS, Suit.DIAMONDS, Suit.SPADES, Suit.HEARTS};
        Color[] colors = {Color.BLACK, Color.RED};

        for (int i = 13; i > 5; i--){
            interleavedCards.add(new Card(suits[i%4], i, colors[i%2]));
        }

        // Act and Assert
        Card c = s.pop();
        s.returnCard(c);
        assertFalse(s.isEmpty());
        s.pop();
        assertTrue(s.pushArray(interleavedCards));
        assertEquals(interleavedCards, s.popArray(7));
        s.returnArray(interleavedCards);
        assertFalse(s.isEmpty());
    }
}
