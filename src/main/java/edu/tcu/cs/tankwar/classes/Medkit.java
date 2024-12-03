package edu.tcu.cs.tankwar.classes;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Medkit {

    ImageView imageView;

    Image image = new Image(getClass().getResource("/edu/tcu/cs/tankwar/img/Medkit.gif").toExternalForm());

    int health = 10;

    public Medkit(double x, double y) {
        imageView = new ImageView(image);
        imageView.setX(x);
        imageView.setY(y);
    }

    public int getHealth() {
        return health;
    }

    public Bounds getBoundsInParent(){
        return this.imageView.getBoundsInParent();
    }

    public ImageView getImageView() {
        return imageView;
    }
}
