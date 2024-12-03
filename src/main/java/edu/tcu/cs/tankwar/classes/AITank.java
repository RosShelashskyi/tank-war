package edu.tcu.cs.tankwar.classes;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.util.Duration;

import java.util.List;
import java.util.Random;

public class AITank extends Tank{

    public AITank(Group group, double x, double y) {
        super(group, x, y);
    }

    double moveCounter = 0;

    Random random = new Random();

    boolean canMove = true;

    public void updateAI(Tank playerTank, List<Wall> walls, List<Tank> tanks){

        if(moveCounter <= 0 && canMove){
            shootPlayer(playerTank, walls, tanks);
            canMove = false;
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(e -> {
                canMove = true;
                int dir = random.nextInt(4);
                switch(dir){
                    case 0 -> setDirection("U");
                    case 1 -> setDirection("D");
                    case 2 -> setDirection("L");
                    case 3 -> setDirection("R");
                }
                moveCounter = random.nextDouble(50, 201);
            });
            pause.play();
        }
        if(canMove) doRandomMove(walls);
    }

    private void doRandomMove(List<Wall> walls){
        switch(direction){
            case "U" -> {
                moveUp();
                for (Wall wall : walls){
                    if(this.intersects(wall.getBoundsInParent())) {
                        moveDown();
                        setDirection("U");
                    }
                }
            }
            case "D" -> {
                moveDown();
                for (Wall wall : walls){
                    if(this.intersects(wall.getBoundsInParent())) {
                        moveUp();
                        setDirection("D");
                    }
                }
            }
            case "L" -> {
                moveLeft();
                for (Wall wall : walls){
                    if(this.intersects(wall.getBoundsInParent())) {
                        moveRight();
                        setDirection("L");
                    }
                }
            }
            case "R" -> {
                moveRight();
                for (Wall wall : walls){
                    if(this.intersects(wall.getBoundsInParent())) {
                        moveLeft();
                        setDirection("R");
                    }
                }
            }
        }

        moveCounter -= speed;
    }

    private void shootPlayer(Tank playerTank, List<Wall> walls, List<Tank> tanks){
        this.setDirection(getRelativePlayerLocation(playerTank));
        this.shoot(walls, tanks);
    }

    private String getRelativePlayerLocation(Tank playerTank){
        double dx = playerTank.getX() - this.getX();
        double dy = playerTank.getY() - this.getY();

        if(Math.abs(dx) > Math.abs(dy)){
            if(dx > 0){
                return "R";
            }else{
                return "L";
            }
        }else{
            if(dy > 0){
                return "D";
            }else{
                return "U";
            }
        }
    }
}
