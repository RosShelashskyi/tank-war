package edu.tcu.cs.tankwar.classes;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;

public class Missile extends GameObject{
    Tank tank;

    int damage = 10;

    public Missile(Tank tank, double x, double y){
        upImage = new Image("C:\\Users\\Ros\\Desktop\\Code\\tank-war\\src\\main\\resources\\edu\\tcu\\cs\\tankwar\\img\\missileU.gif");
        downImage = new Image("C:\\Users\\Ros\\Desktop\\Code\\tank-war\\src\\main\\resources\\edu\\tcu\\cs\\tankwar\\img\\MissileD.gif");
        leftImage = new Image("C:\\Users\\Ros\\Desktop\\Code\\tank-war\\src\\main\\resources\\edu\\tcu\\cs\\tankwar\\img\\missileL.gif");
        rightImage = new Image("C:\\Users\\Ros\\Desktop\\Code\\tank-war\\src\\main\\resources\\edu\\tcu\\cs\\tankwar\\img\\missileR.gif");
        this.tank = tank;
        imageView = new ImageView();
        setDirection(tank.getDirection());
        //setImage();
        speed = 2;
        positionMissile(x, y);
        imageView.setX(this.x);
        imageView.setY(this.y);
    }

    private void positionMissile(double x, double y) {
        switch(direction){
            case "U" -> {
                this.x = (x + tank.getWidth() / 2) - (this.getWidth() / 3);
                this.y = y;
            }
            case "D" -> {
                this.x = (x + tank.getWidth() / 2) - (this.getWidth() / 1.5);
                this.y = y + tank.getHeight();
            }
            case "L" -> {
                this.x = x;
                this.y = y + tank.getHeight() / 2 - this.getHeight();
            }
            case "R" -> {
                this.x = (x + tank.getWidth());
                this.y = (y + tank.getHeight() / 2);
            }
        }
    }

    private void setImage(){
        System.out.println(direction);
        switch(direction){
            case "D":
                image = downImage;
                break;
            case "L" :
                image = leftImage;
                break;
            case "R":
                image = rightImage;
                break;
            default:
                image = upImage;
        }
    }

    public void moveMissile(){
        switch(this.direction){
            case "U" -> {
                moveUp();
            }
            case "D" -> {
                moveDown();
            }
            case "L" -> {
                moveLeft();
            }
            case "R" -> {
                moveRight();
            }
        }
    }

    public boolean isIntersectingWall(List<Wall> walls){
        for(Wall wall : walls){
            if (this.intersects(wall.getBoundsInParent())) return true;
        }
        return false;
    }

    public Tank getIntersectedTank(List<Tank> tanks){
        for(Tank tank : tanks){
            if(this.intersects(tank.getBoundsInParent()) && tank != this.tank) return tank;
        }
        return null;
    }

    public int getDamage() {
        return damage;
    }
}
