package Spider;

import Card.*;
import Spider.Foundation.FoundationTable;
import Spider.Stock.Stock;
import Spider.Tableau.TableauTable;

public class Spider {

    private FoundationTable foundationTable;
    private Stock stock;
    private TableauTable tableauTable;

    public Spider(Card[] cards) {
        initializeGame(cards);
    }

    public Spider() {
        Card[] deck1 = Deck.createDeck();
        Card[] deck2 = Deck.createDeck();
        int length1 = deck1.length;
        int length2 = deck2.length;
        Card[] combinedDeck = new Card[length1 + length2];
        System.arraycopy(deck1, 0, combinedDeck, 0, length1);
        System.arraycopy(deck2, 0, combinedDeck, length1, length2);
        initializeGame(combinedDeck);
    }

    private void initializeGame(Card[] cards) {
        foundationTable = new FoundationTable();
        stock = new Stock(cards);
        tableauTable = new TableauTable(stock);
    }

    public void drawStockCards() {
        tableauTable.assignStockCards(stock);
    }

    public boolean moveTopCardToTableau(int origin, int target) {
        Card card = tableauTable.pickUp(origin);

        if (!tableauTable.insert(target, card)) {
            tableauTable.returnCard(origin, card);
            return false;
        }
        return true;
    }

    public boolean moveTableauToTableau(int origin, int i, int target) {
        return tableauTable.move(origin, i, target);
    }

    public void addWonColumnsToFoundations() {
        for (int i = 0; i < 10; i++) {
            Card[] wonColumn = tableauTable.verifyTable();

            if (wonColumn != null)
                foundationTable.assignCards(wonColumn);
        }
    }

    public boolean verifyVictory() {
        return foundationTable.gameWon();
    }
}