package Spider;

import Card.*;
import Spider.Foundation.FoundationTable;
import Spider.Stock.Stock;
import Spider.Tableau.TableauTable;

import java.io.*;

public class Spider implements Serializable {

    // Constants
    private static final String SPIDER_PATH = "saves/spider.txt";

    // Attributes
    private FoundationTable foundationTable;
    private Stock stock;
    private TableauTable tableauTable;

    // Constructor
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

    // Methods
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

    public void saveGame() {
        File path = new File(SPIDER_PATH);

        try {
            ObjectOutputStream o = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(path)));
            o.writeObject(this);
            o.close();

        } catch (IOException e) {
            System.out.print("Unable to save your game :(\n");
        }
    }

    public void loadGame() {
        File path = new File(SPIDER_PATH);

        try {
            ObjectInputStream o = new ObjectInputStream(new BufferedInputStream(new FileInputStream(path)));
            Spider s = (Spider) o.readObject();
            this.foundationTable = s.foundationTable;
            this.stock = s.stock;
            this.tableauTable = s.tableauTable;
            o.close();

        } catch (IOException | ClassNotFoundException e) {
            System.out.print("Unable to load your game :(\n");
        }
    }

    public Card peekTableauTopCard(int i) {
        return tableauTable.peek(i);
    }

    public int getTableauSize(int i) {
        return tableauTable.getTableauSize(i);
    }

    public int getTableauHiddenCardsSize(int i) {
        return tableauTable.getTableauHiddenCardsSize(i);
    }

    public int getTableauVisibleCardsSize(int i) {
        return tableauTable.getTableauVisibleCardsSize(i);
    }

    public Card[] peekTableauTopCards() {
        return tableauTable.peek();
    }

    public int getStockSize() {
        return stock.size();
    }
}