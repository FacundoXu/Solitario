package Spider.Tableu;

import Card.Card;
import Spider.Stock.Stock;

import java.util.LinkedList;
import java.util.List;

public class TableuTable {

    private final TableuStack[] stacks = new TableuStack[10];

    public TableuTable(Stock stock) {
        for (int i = 0; i < 4; i++) {
            List<Card> leftCards = new LinkedList<>();

            for (int j = 0; i < 6; i++) {
                leftCards.add(stock.drawCard());
            }

            stacks[i] = new TableuStack(leftCards.toArray(new Card[0]));
        }

        for (int i = 4; i < 10; i++) {
            List<Card> rightCards = new LinkedList<>();

            for (int j = 0; i < 5; i++) {
                rightCards.add(stock.drawCard());
            }

            stacks[i] = new TableuStack(rightCards.toArray(new Card[0]));
        }
    }

    public Card pickUp(int tableau) {
        return stacks[tableau].pop();
    }

    public Card peek(int tableau) {
        return stacks[tableau].peek();
    }

    public Card[] peek() {
        Card[] topCards = new Card[10];

        for (int i = 0; i < 10; i++) {
            topCards[i] = this.peek(i);
        }
        return topCards;
    }

    public boolean insert(int tableau, Card card) {
        return stacks[tableau].push(card);
    }

    public boolean move(int origin, int card, int target) {
        Card[] cardsArray = stacks[origin].popArray(card);

        if (!stacks[target].pushArray(cardsArray)) {
            stacks[origin].pushArray(cardsArray);
            return false;
        }
        return true;
    }

    public Card[] verify() {
        for (int i = 0; i < 10; i++) {
            Card[] cards = stacks[i].getWinnerCards();

            if (cards != null) {
                return cards;
            }
        }
        return null;
    }
}