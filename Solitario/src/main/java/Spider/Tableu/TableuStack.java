package Spider.Tableu;

import Card.Card;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TableuStack {

    private final List<Card> visibleCards;
    private final List<Card> hiddenCards;

    public TableuStack(Card[] cardsArray) {
        hiddenCards = new LinkedList<>(Arrays.asList(cardsArray));
        visibleCards = new LinkedList<>();

        int hiddenSize = hiddenCards.size();
        Card lastHiddenCard = hiddenCards.remove(hiddenSize - 1);
        visibleCards.add(lastHiddenCard);
    }

    public Card peekVisibleCards() {
        if (!visibleCards.isEmpty())
            return null;
        int size = visibleCards.size();
        return visibleCards.get(size - 1);
    }
}
