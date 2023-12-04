package ui;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import Card.*;

public class CardWrapper {
    public Card card;
    public ImageView view;
    public Pane container;

    public CardWrapper(Card card, ImageView view, Pane container) {
        this.card = card;
        this.view = view;
        this.container = container;

    }

    public void remove() {
        container.getChildren().remove(view);
    }
}
