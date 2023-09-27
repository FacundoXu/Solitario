import Card.Card;
import Foundation.FoundationStack;
import org.junit.Test;
import java.util.LinkedList;
import java.util.List;
import static org.junit.Assert.*;

public class FoundationStackTest {

    @Test
    public void oneCard() {
        // arrange
        FoundationStack s = new FoundationStack();
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
    public void invalidCard() {
        //Arrange
        FoundationStack s = new FoundationStack();
        Card c1 = new Card(Suit.DIAMONDS, 2, Color.RED);
        boolean exp_empty1 = true;
        Card exp_peek1 = null;
        boolean exp_push1 = false;
        boolean exp_empty2 = true;
        Card exp_peek2 = null;
        Card exp_pop1 = null;

        // act
        boolean empty1 = s.isEmpty();
        Card peek1 = s.peek();
        boolean push1 = s.push(c1);
        boolean empty2 = s.isEmpty();
        Card peek2 = s.peek();
        Card pop1 = s.pop();
        // assert
        assertEquals(exp_empty1, empty1);
        assertEquals(exp_empty2, empty2);
        assertEquals(exp_pop1, pop1);
        assertEquals(exp_peek1, peek1);
        assertEquals(exp_peek2, peek2);
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
        assertEquals(true,push1);
        assertEquals(true,push2);
        assertEquals(false,push3);
        assertEquals(true,push4);
        assertEquals(true,push5);
        assertEquals(true,push6);
        assertEquals(twoSpades, pop1);
        assertEquals(fourSpades, pop2);
        assertEquals(threeSpades, pop3);
        assertEquals(twoSpades, pop4);
        assertEquals(oneSpades, pop5);
        assertEquals(null, pop6);
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
        assertEquals(true,push1);
        assertEquals(false,push2);
        assertEquals(true,push3);
        assertEquals(true,push4);
        assertEquals(threeClubs, pop1);
        assertEquals(twoClubs, pop2);
        assertEquals(oneClubs, pop3);
    }
    @Test
    public void verify(){
        //Arrange
        FoundationStack s = new FoundationStack();
        List<Card> fullDiamonds = new LinkedList<>();
        List<Card> fullSpades = new LinkedList<>();

        for (int i = 1; i < 14; i++){
            fullDiamonds.add(new Card(Suit.DIAMONDS, i, Color.RED));
            fullSpades.add(new Card(Suit.SPADES, i, Color.BLACK));
        }
        //Act
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
        //Assert
        assertEquals(false, verify1);
        assertEquals(true, verify2);
        assertEquals(false, verify3);
        assertEquals(false, verify4);
        assertEquals(true, verify5);
    }
}