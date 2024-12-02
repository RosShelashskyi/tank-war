package edu.tcu.cs.tankwar.classes;

import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Wall {
    Rectangle rectangle;

    public Wall(Group group, double x, double y, double height, double width) {
        rectangle = new Rectangle();
        rectangle.setX(x);
        rectangle.setY(y);
        rectangle.setHeight(height);
        rectangle.setWidth(width);
        rectangle.setFill(Color.BLACK);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public double getX() {
        return rectangle.getX();
    }

    public void setX(double x) {
        rectangle.setX(x);
    }

    public double getY() {
        return rectangle.getY();
    }

    public void setY(double y) {
        rectangle.setY(y);
    }

    public double getHeight() {
        return rectangle.getHeight();
    }

    public void setHeight(double height) {
        rectangle.setHeight(height);
    }

    public double getWidth() {
        return rectangle.getWidth();
    }

    public void setWidth(double width) {
        rectangle.setWidth(width);
    }

    public Bounds getBoundsInParent(){
        return this.rectangle.getBoundsInParent();
    }
}
