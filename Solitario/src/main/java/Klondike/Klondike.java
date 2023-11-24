package Klondike;

import Card.*;
import Klondike.Foundation.FoundationTable;
import Klondike.Stock.StockTable;
import Klondike.Tableau.TableauTable;

import java.io.*;
import java.util.Arrays;

public class Klondike implements Serializable {

    // Constants
    private static final String KLONDIKE_PATH = "saves/klondike.txt";

    // Attributes
    private FoundationTable foundationTable;
    private StockTable stockTable;
    private TableauTable tableauTable;

    // Constructor
    public Klondike(Card[] cards) {
        initializeGame(cards);
    }

    public Klondike() {
        initializeGame(Deck.createDeck());
    }

    // Methods
    private void initializeGame(Card[] cards) {
        tableauTable = new TableauTable(Arrays.copyOfRange(cards, 0, 28));
        stockTable = new StockTable(Arrays.copyOfRange(cards, 28, 52));
        foundationTable = new FoundationTable();
    }

    public Card stockNextCard() {
        return stockTable.nextCard();
    }

    public boolean moveStockToTableau(int tableau) {
        Card c = stockTable.getCard();

        if (!tableauTable.insert(tableau, c)) {
            stockTable.returnCard(c);
            return false;
        }
        return true;
    }

    public boolean moveStockToFoundation(int foundation) {
        Card c = stockTable.getCard();

        if (!foundationTable.insert(foundation, c)) {
            stockTable.returnCard(c);
            return false;
        }
        return true;
    }

    public boolean moveTableauToFoundation(int tableau, int foundation) {
        Card c = tableauTable.pickUp(tableau);

        if (!foundationTable.insert(foundation, c)) {
            tableauTable.returnCard(tableau, c);
            return false;
        }
        return true;
    }

    public boolean moveFoundationToTableau(int foundation, int tableau) {
        Card c = foundationTable.get(foundation);

        if (!tableauTable.insert(tableau, c)) {
            foundationTable.insert(foundation, c);
            return false;
        }
        return true;
    }

    public boolean moveTableauToTableau(int originCardIdx, int originTableau, int destTableau) {
        return tableauTable.move(originCardIdx, originTableau, destTableau);
    }

    public boolean verifyVictory() {
        return foundationTable.verify();
    }

    public void saveGame() {
        File path = new File(KLONDIKE_PATH);

        try {
            ObjectOutputStream o = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(path)));
            o.writeObject(this);
            o.close();

        } catch (IOException e) {
            System.out.print("Unable to save your game :(\n");
        }
    }

    public void loadGame() {
        File path = new File(KLONDIKE_PATH);

        try {
            ObjectInputStream o = new ObjectInputStream(new BufferedInputStream(new FileInputStream(path)));
            Klondike k = (Klondike) o.readObject();
            this.foundationTable = k.foundationTable;
            this.stockTable = k.stockTable;
            this.tableauTable = k.tableauTable;
            o.close();

        } catch (IOException | ClassNotFoundException e) {
            System.out.print("Unable to load your game :(\n");
        }
    }
    public Card peekTableauTopCard(int TableauIdx){
        return tableauTable.peek(TableauIdx);
    }

    public int stockCardsLeft() {return stockTable.cardsLeft(); }

    public int stockPass() {return stockTable.currentPass();}
}