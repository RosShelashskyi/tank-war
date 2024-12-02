package edu.tcu.cs.tankwar.classes;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class GameObject {
    protected Image image;
    protected ImageView imageView;
    protected Image upImage;
    protected Image downImage;
    protected Image leftImage;
    protected Image rightImage;
    protected double speed;
    protected String direction;
    protected double x;
    protected double y;

    public ImageView getImageView() {
        return imageView;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
        switch(this.direction){
            case "U" -> this.imageView.setImage(upImage);
            case "D" -> this.imageView.setImage(downImage);
            case "L" -> this.imageView.setImage(leftImage);
            case "R" -> this.imageView.setImage(rightImage);
        }
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void moveRight(){
        x += speed;
        this.imageView.setX(x);
        setDirection("R");
    }

    public void moveLeft(){
        x -= speed;
        this.imageView.setX(x);
        setDirection("L");
    }

    public void moveUp(){
        y -= speed;
        this.imageView.setY(y);
        setDirection("U");
    }

    public void moveDown(){
        y += speed;
        this.imageView.setY(y);
        setDirection("D");
    }

    public double getHeight(){
        return imageView.getImage().getHeight();
    }

    public double getWidth(){
        return imageView.getImage().getWidth();
    }

    public Bounds getBoundsInParent(){
        return this.imageView.getBoundsInParent();
    }

    public boolean intersects(Bounds bounds){
        return this.imageView.getBoundsInParent().intersects(bounds);
    }
}
