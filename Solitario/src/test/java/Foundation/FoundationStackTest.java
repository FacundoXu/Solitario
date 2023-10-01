package Foundation;

import Card.*;
import org.junit.Test;
import java.util.LinkedList;
import java.util.List;
import static org.junit.Assert.*;

public class FoundationStackTest {
    @Test
    public void oneCard() {
        // Arrange
        FoundationStack s = new FoundationStack();
        Card c1 = new Card(Suit.DIAMONDS, 1, Color.RED);
        boolean exp_empty1 = true;
        boolean exp_empty2 = false;
        boolean exp_empty3 = true;
        boolean exp_push1 = true;

        // Act
        boolean empty1 = s.isEmpty();
        Card peek1 = s.peek();
        boolean push1 = s.push(c1);
        boolean empty2 = s.isEmpty();
        Card peek2 = s.peek();
        Card pop1 = s.pop();
        boolean empty3 = s.isEmpty();
        Card peek3 = s.peek();
        Card pop2 = s.pop();

        // Assert
        assertEquals(exp_empty1, empty1);
        assertEquals(exp_empty2, empty2);
        assertEquals(exp_empty3, empty3);
        assertEquals(c1, pop1);
        assertNull(pop2);
        assertNull(peek1);
        assertEquals(c1, peek2);
        assertNull(peek3);
        assertEquals(exp_push1, push1);
    }

    @Test
    public void invalidCard() {
        // Arrange
        FoundationStack s = new FoundationStack();
        Card c1 = new Card(Suit.DIAMONDS, 2, Color.RED);
        boolean exp_empty1 = true;
        boolean exp_push1 = false;
        boolean exp_empty2 = true;

        // Act
        boolean empty1 = s.isEmpty();
        Card peek1 = s.peek();
        boolean push1 = s.push(c1);
        boolean empty2 = s.isEmpty();
        Card peek2 = s.peek();
        Card pop1 = s.pop();

        // Assert
        assertEquals(exp_empty1, empty1);
        assertEquals(exp_empty2, empty2);
        assertNull(pop1);
        assertNull(peek1);
        assertNull(peek2);
        assertEquals(exp_push1, push1);
    }

    @Test
    public void multipleCards() {
    // Arrange
        FoundationStack s = new FoundationStack();
        Card oneSpades = new Card(Suit.SPADES, 1, Color.BLACK);
        Card twoSpades = new Card(Suit.SPADES, 2, Color.BLACK);
        Card threeSpades = new Card(Suit.SPADES, 3, Color.BLACK);
        Card fourSpades = new Card(Suit.SPADES, 4, Color.BLACK);
        Card threeClubs = new Card(Suit.CLUBS, 3, Color.BLACK);

    // Act
        boolean push1 = s.push(oneSpades);
        boolean push2 = s.push(twoSpades);
        boolean push3 = s.push(threeClubs);
        Card pop1 = s.pop();
        boolean push4 = s.push(twoSpades);
        boolean push5 = s.push(threeSpades);
        boolean push6 = s.push(fourSpades);
        Card pop2 = s.pop();
        Card pop3 = s.pop();
        Card pop4 = s.pop();
        Card pop5 = s.pop();
        Card pop6 = s.pop();

    // Assert
        assertTrue(push1);
        assertTrue(push2);
        assertFalse(push3);
        assertTrue(push4);
        assertTrue(push5);
        assertTrue(push6);
        assertEquals(twoSpades, pop1);
        assertEquals(fourSpades, pop2);
        assertEquals(threeSpades, pop3);
        assertEquals(twoSpades, pop4);
        assertEquals(oneSpades, pop5);
        assertNull(pop6);
    }

    @Test
    public void changeSuits() {
        // Arrange
        FoundationStack s = new FoundationStack();
        Card oneHearts = new Card(Suit.HEARTS, 1, Color.RED);
        Card twoHearts = new Card(Suit.HEARTS, 2, Color.RED);
        Card threeHearts = new Card(Suit.HEARTS, 3, Color.RED);
        Card oneClubs= new Card(Suit.CLUBS, 1, Color.BLACK);
        Card twoClubs= new Card(Suit.CLUBS, 2, Color.BLACK);
        Card threeClubs = new Card(Suit.CLUBS, 3, Color.BLACK);

        // Act
        s.push(oneHearts);
        s.push(twoHearts);
        s.push(threeHearts);
        s.pop();
        s.pop();
        s.pop();
        boolean push1 = s.push(oneClubs);
        boolean push2 = s.push(twoHearts);
        boolean push3 = s.push(twoClubs);
        boolean push4 = s.push(threeClubs);
        Card pop1 = s.pop();
        Card pop2 = s.pop();
        Card pop3 = s.pop();

        // Assert
        assertTrue(push1);
        assertFalse(push2);
        assertTrue(push3);
        assertTrue(push4);
        assertEquals(threeClubs, pop1);
        assertEquals(twoClubs, pop2);
        assertEquals(oneClubs, pop3);
    }

    @Test
    public void verify(){
        // Arrange
        FoundationStack s = new FoundationStack();
        List<Card> fullDiamonds = new LinkedList<>();
        List<Card> fullSpades = new LinkedList<>();

        for (int i = 1; i < 14; i++){
            fullDiamonds.add(new Card(Suit.DIAMONDS, i, Color.RED));
            fullSpades.add(new Card(Suit.SPADES, i, Color.BLACK));
        }

        // Act
        boolean verify1 = s.verify();
        for (Card c:fullDiamonds) {
            s.push(c);
        }
        boolean verify2 = s.verify();
        s.pop();
        boolean verify3 = s.verify();
        for (int i = 0; i < 13; i++) {
            s.pop();
        }
        boolean verify4 = s.verify();
        for (Card c:fullSpades) {
            s.push(c);
        }
        boolean verify5 = s.verify();

        // Assert
        assertFalse(verify1);
        assertTrue(verify2);
        assertFalse(verify3);
        assertFalse(verify4);
        assertTrue(verify5);
    }
}