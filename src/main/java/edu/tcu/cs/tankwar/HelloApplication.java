package edu.tcu.cs.tankwar;

import edu.tcu.cs.tankwar.classes.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class HelloApplication extends Application {
    PlayerTank playerTank;
    List<Tank> tanks;
    List<Wall> walls;
    List<Medkit> medkits;
    AnimationTimer gameLoop;
    Set<String> pressedKeys;
    Set<String> activeKeys;
    @Override
    public void start(Stage stage) throws IOException {
        Group root = new Group();
        Scene scene = new Scene(root, 1000, 1000);

        setupGame(root);
        setupGameLoop(root);
        setupControls(scene);

        gameLoop.start();
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public void updateAI(){
        for(Tank tank : tanks){
            if(tank.getClass().equals(AITank.class) && !tank.isDead()){
                ((AITank) tank).updateAI(playerTank, walls, tanks);
            }
        }
    }

    public void setupGame(Group root){
        tanks = new ArrayList<>();

        playerTank = new PlayerTank(root, 500, 100);
        Tank enemy1 = new AITank(root, 300, 800);
        Tank enemy2 = new AITank(root, 500, 800);
        Tank enemy3 = new AITank(root, 700, 800);
        Tank enemy4 = new AITank(root, 150, 500);
        Tank enemy5 = new AITank(root, 850, 500);

        tanks.add(playerTank);
        tanks.add(enemy1);
        tanks.add(enemy2);
        tanks.add(enemy3);
        tanks.add(enemy4);
        tanks.add(enemy5);

        for(Tank tank : tanks){
            root.getChildren().add(tank.getImageView());
        }

        walls = new ArrayList<>();

        Wall northBound = new Wall(root, 0, 0, 1, 1000);
        Wall eastBound = new Wall(root, 999, 0, 1000, 1);
        Wall southBound = new Wall(root, 0, 999, 1, 1000);
        Wall westBound = new Wall(root, 0, 0, 1000, 1);

        Wall wall1 = new Wall(root, 450, 450, 100, 100);
        Wall wall2 = new Wall(root, 600, 750, 40, 200);
        Wall wall3 = new Wall(root, 760, 550, 200, 40);
        Wall wall4 = new Wall(root, 250, 250, 400, 40);
        Wall wall5 = new Wall(root, 350, 800, 200, 40);
        Wall wall6 = new Wall(root, 600, 250, 40, 250);

        walls.add(northBound);
        walls.add(eastBound);
        walls.add(southBound);
        walls.add(westBound);
        walls.add(wall1);
        walls.add(wall2);
        walls.add(wall3);
        walls.add(wall4);
        walls.add(wall5);
        walls.add(wall6);

        for(Wall wall : walls){
            root.getChildren().add(wall.getRectangle());
        }

        root.getChildren().add(playerTank.getHealthBar());

        medkits = new ArrayList<>();

        Medkit medkit1 = new Medkit(150, 150);
        Medkit medkit2 = new Medkit(850, 150);
        Medkit medkit3 = new Medkit(150, 850);
        Medkit medkit4 = new Medkit(850, 850);

        medkits.add(medkit1);
        medkits.add(medkit2);
        medkits.add(medkit3);
        medkits.add(medkit4);

        for(Medkit medkit : medkits){
            root.getChildren().add(medkit.getImageView());
        }

        pressedKeys = new HashSet<>();
        activeKeys = new HashSet<>();
    }

    public void setupGameLoop(Group root){
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if(tanks.isEmpty()){
                    win
                }
                updateAI();
                if(!playerTank.isDead()){
                    for(Medkit medkit : medkits){
                        if (playerTank.intersects(medkit.getBoundsInParent())){
                            playerTank.healTank(medkit.getHealth());
                            root.getChildren().remove(medkit.getImageView());
                            medkits.remove(medkit);
                            break;
                        }
                    }
                    if (pressedKeys.contains("W")) {
                        playerTank.moveUp();
                        for (Wall wall : walls){
                            if(playerTank.intersects(wall.getBoundsInParent())) {
                                playerTank.moveDown();
                                playerTank.setDirection("U");
                            }
                        }
                    }
                    if (pressedKeys.contains("S")) {
                        playerTank.moveDown();
                        for (Wall wall : walls){
                            if(playerTank.intersects(wall.getBoundsInParent())) {
                                playerTank.moveUp();
                                playerTank.setDirection("D");
                            }
                        }
                    }
                    if (pressedKeys.contains("A")) {
                        playerTank.moveLeft();
                        for (Wall wall : walls){
                            if(playerTank.intersects(wall.getBoundsInParent())) {
                                playerTank.moveRight();
                                playerTank.setDirection("L");
                            }
                        }
                    }
                    if (pressedKeys.contains("D")) {
                        playerTank.moveRight();
                        for (Wall wall : walls){
                            if(playerTank.intersects(wall.getBoundsInParent())) {
                                playerTank.moveLeft();
                                playerTank.setDirection("R");
                            }
                        }
                    }
                }

            }
        };
    }

    void setupControls(Scene scene){
        scene.setOnKeyPressed(event -> {
            pressedKeys.add(event.getCode().toString());
            if (Objects.requireNonNull(event.getCode()) == KeyCode.SPACE) {
                String key = event.getCode().toString();
                if (!activeKeys.contains(key)) {
                    activeKeys.add(key);
                    playerTank.shoot(walls, tanks);
                }
            }
        });

        scene.setOnKeyReleased(event -> {
            pressedKeys.remove(event.getCode().toString());
            activeKeys.remove(event.getCode().toString());
        });
    }

    void win(){

    }

    public static void main(String[] args) {
        launch();
    }
}