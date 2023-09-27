import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class TableuStackTest {

    @Test
    public void setUp() {
        // arrange
        TableuStack s = new TableuStack();
        Card c1 = new Card(Suit.DIAMONDS, 1, Color.RED);
        boolean exp_empty1 = true;
        Card exp_peek1 = null;
        boolean exp_empty2 = false;
        Card exp_peek2 = c1;
        Card exp_pop1 = c1;
        boolean exp_empty3 = true;
        Card exp_peek3 = null;
        Card exp_pop2 = null;
        boolean exp_push1 = true;
        // act
        boolean empty1 = s.isEmpty();
        Card peek1 = s.peek();
        boolean push1 = s.push(c1);
        boolean empty2 = s.isEmpty();
        Card peek2 = s.peek();
        Card pop1 = s.pop();
        boolean empty3 = s.isEmpty();
        Card peek3 = s.peek();
        Card pop2 = s.pop();
        // assert
        assertEquals(exp_empty1, empty1);
        assertEquals(exp_empty2, empty2);
        assertEquals(exp_empty3, empty3);
        assertEquals(exp_pop1, pop1);
        assertEquals(exp_pop2, pop2);
        assertEquals(exp_peek1, peek1);
        assertEquals(exp_peek2, peek2);
        assertEquals(exp_peek3, peek3);
        assertEquals(exp_push1, push1);

    }

    @Test
    public void oneCard() {
        //Arrange
        TableuStack s = new TableuStack();
        Card c1 = new Card(Suit.DIAMONDS, 7, Color.RED);
        Card c2 = new Card(Suit.DIAMONDS, 6, Color.RED);
        Card c3 = new Card(Suit.CLUBS, 6, Color.BLACK);
        Card c4 = new Card(Suit.DIAMONDS, 10, Color.RED);
        // act
        boolean push1 = s.push(c1);
        Card peek1 = s.peek();
        boolean push2 = s.push(c2);
        boolean push3 = s.push(c3);
        boolean push4 = s.push(c4);
        // assert
        assertEquals(true, push1);
        assertEquals(false, push2);
        assertEquals(true, push3);
        assertEquals(false, push4);


    }

    @Test
    public void multipleCards() {
        // Arrange
        TableuStack s = new TableuStack();
        List<Card> interleavedCards = new LinkedList<>();
        Suit[] suits = {Suit.CLUBS, Suit.DIAMONDS, Suit.SPADES, Suit.HEARTS};
        Color[] colors = {Color.BLACK, Color.RED};
        for (int i = 13; i > 0; i--){
            interleavedCards.add(new Card(suits[i%4], i, colors[i%2]));
        }
        // Act and assert
        for (Card c: interleavedCards) {
            assertTrue(s.push(c));
        }
    }
}
