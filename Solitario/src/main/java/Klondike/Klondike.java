package Klondike;

import Card.Card;
import Card.Deck;
import Klondike.Foundation.FoundationTable;
import Klondike.Stock.StockTable;
import Klondike.Tableu.TableuTable;

import java.util.Arrays;

public class Klondike {

    private FoundationTable foundationTable;
    private StockTable stockTable;
    private TableuTable tableuTable;

    public Klondike(Card[] cards) {
        initializeGame(cards);
    }

    public Klondike() {
        initializeGame(Deck.createDeck());
    }

    private void initializeGame(Card[] cards) {
        tableuTable = new TableuTable(Arrays.copyOfRange(cards, 0, 28));
        stockTable = new StockTable(Arrays.copyOfRange(cards, 28, 52));
        foundationTable = new FoundationTable();
    }

    public Card stockNextCard() {
        return stockTable.nextCard();
    }

    public boolean moveStockToTableu(int tableu) {
        Card c = stockTable.getCard();

        if (!tableuTable.insert(tableu, c)) {
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

    public boolean moveTableuToFoundation(int tableu, int foundation) {
        Card c = tableuTable.pickUp(tableu);

        if (!foundationTable.insert(foundation, c)) {
            tableuTable.returnCard(tableu, c);
            return false;
        }
        return true;
    }

    public boolean moveFoundationToTableu(int foundation, int tableu) {
        Card c = foundationTable.get(foundation);

        if (!tableuTable.insert(tableu, c)) {
            foundationTable.insert(foundation, c);
            return false;
        }
        return true;
    }

    public boolean moveTableuToTableu(int originCardIdx, int originTableu, int destTableu) {
        return tableuTable.move(originCardIdx, originTableu, destTableu);
    }

    public boolean verifyVictory() {
        return foundationTable.verify();
    }
}