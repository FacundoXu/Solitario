package ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import Card.*;

public class CardView {

    private static final String IMAGE_PATH = "/cards-remaster.png";
    private static final int CARD_WIDTH = 47;
    private static final int CARD_HEIGHT = 62;
    private static final Image spriteSheet = new Image(CardView.class.getResourceAsStream(IMAGE_PATH));

    public static ImageView getCard(Card c) {
        int suit;
        if (c.suit() == Suit.DIAMONDS) suit = 0;
        else if (c.suit() == Suit.CLUBS) suit = 1;
        else if (c.suit() == Suit.HEARTS) suit = 2;
        else suit = 3;
        ImageView cardView = new ImageView(spriteSheet);
        cardView.setViewport(new javafx.geometry.Rectangle2D((c.rank() - 1) * CARD_WIDTH, suit * CARD_HEIGHT, CARD_WIDTH, CARD_HEIGHT));
        return cardView;
    }

    public static ImageView getCardBack() {
        ImageView cardView = new ImageView(spriteSheet);
        cardView.setViewport(new javafx.geometry.Rectangle2D(CARD_WIDTH, 4 * CARD_HEIGHT, CARD_WIDTH, CARD_HEIGHT));
        return cardView;
    }

    public static Rectangle getEmptyPlace() {
        Rectangle rectangle = new Rectangle(CARD_WIDTH, CARD_HEIGHT);
        rectangle.setFill(Color.DARKGRAY);
        return rectangle;
    }
}
