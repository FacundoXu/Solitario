package Klondike.Stock;

import Card.Card;

public class StockTable {

    private int talon = 0;
    private int waste = 1;
    private int passes = 1;
    private final StockStack[] stacks = new StockStack[2];

    public StockTable(Card[] cards) {
        stacks[talon] = new StockStack(cards);
        stacks[waste] = new StockStack();
    }

    public Card nextCard() {
        if (stacks[talon].isEmpty()) {
            if (passes < 3)
                switchStacks();
            return null;
        }

        Card nextCard = stacks[talon].pop();
        stacks[waste].push(nextCard);
        return stacks[waste].peek();
    }

    public Card getCard() {
        return stacks[waste].pop();
    }

    public Card peek() {
        return stacks[waste].peek();
    }

    public boolean isEmpty() {
        return stacks[talon].isEmpty() && stacks[waste].isEmpty();
    }

    private void switchStacks() {
        int oldTalon = talon;
        talon = waste;
        waste = oldTalon;
        stacks[talon].reverse();
        passes++;
    }

    public void returnCard(Card c) {
        stacks[waste].push(c);
    }
}
