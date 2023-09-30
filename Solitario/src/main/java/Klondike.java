import Foundation.FoundationTable;
import Stock.StockTable;
import Tableu.TableuTable;
import Card.*;

import java.util.Arrays;

public class Klondike {

    private FoundationTable ft;
    private StockTable st;
    private TableuTable tt;

    public Klondike(Card[] cards){
        initializeGame(cards);
    }

    public Klondike(){ initializeGame(Deck.createDeck());}

    private void initializeGame(Card[] cards){
        tt = new TableuTable(Arrays.copyOfRange(cards, 0, 28));
        st = new StockTable(Arrays.copyOfRange(cards, 28, 52));
        ft = new FoundationTable();
    }
    public Card stockNextCard(){
        return st.nextCard();
    }
    public boolean moveStockToTableu(int tableu){
        Card c = st.getCard();
        if (!tt.insert(tableu, c)) {
            st.returnCard(c);
            return false;
        }
        return true;
    }

    public boolean moveStockToFoundation(int foundation){
        Card c = st.getCard();
        if (!ft.insert(foundation, c)) {
            st.returnCard(c);
            return false;
        }
        return true;
    }

    public boolean moveTableuToFoundation(int tableu, int foundation){
        Card c = tt.pickUp(tableu);
        if (!ft.insert(foundation, c)){
            tt.returnCard(tableu, c);
            return false;
        }
        return true;
    }
    public boolean moveFoundationToTableu(int foundation, int tableu){
        Card c = ft.get(foundation);
        if (!tt.insert(tableu, c)){
            ft.insert(foundation, c);
            return false;
        }
        return true;
    }
    public boolean moveTableuToTableu(int originCardIdx,int originTableu, int destTableu){
        return tt.move(originCardIdx,originTableu,destTableu);
    }
    public boolean verifyVictory(){
        return ft.verify();
    }

}