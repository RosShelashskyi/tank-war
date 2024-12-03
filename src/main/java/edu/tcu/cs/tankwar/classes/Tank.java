package edu.tcu.cs.tankwar.classes;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class Tank extends GameObject{

    Group group;

    int health = 10;

    List<Image> animationImages = new ArrayList<>();

    public Tank(Group group, double x, double y) {
        upImage = new Image(getClass().getResource("/edu/tcu/cs/tankwar/img/tankU.gif").toExternalForm());
        downImage = new Image(getClass().getResource("/edu/tcu/cs/tankwar/img/tankD.gif").toExternalForm());
        leftImage = new Image(getClass().getResource("/edu/tcu/cs/tankwar/img/tankL.gif").toExternalForm());
        rightImage = new Image(getClass().getResource("/edu/tcu/cs/tankwar/img/tankR.gif").toExternalForm());

        animationImages.add(new Image(getClass().getResource("/edu/tcu/cs/tankwar/img/0.gif").toExternalForm()));
        animationImages.add(new Image(getClass().getResource("/edu/tcu/cs/tankwar/img/1.gif").toExternalForm()));
        animationImages.add(new Image(getClass().getResource("/edu/tcu/cs/tankwar/img/2.gif").toExternalForm()));
        animationImages.add(new Image(getClass().getResource("/edu/tcu/cs/tankwar/img/3.gif").toExternalForm()));
        animationImages.add(new Image(getClass().getResource("/edu/tcu/cs/tankwar/img/4.gif").toExternalForm()));
        animationImages.add(new Image(getClass().getResource("/edu/tcu/cs/tankwar/img/5.gif").toExternalForm()));
        animationImages.add(new Image(getClass().getResource("/edu/tcu/cs/tankwar/img/6.gif").toExternalForm()));
        animationImages.add(new Image(getClass().getResource("/edu/tcu/cs/tankwar/img/7.gif").toExternalForm()));
        animationImages.add(new Image(getClass().getResource("/edu/tcu/cs/tankwar/img/8.gif").toExternalForm()));
        animationImages.add(new Image(getClass().getResource("/edu/tcu/cs/tankwar/img/9.gif").toExternalForm()));
        animationImages.add(new Image(getClass().getResource("/edu/tcu/cs/tankwar/img/10.gif").toExternalForm()));

        this.group = group;
        this.x = x;
        this.y = y;
        image = upImage;
        imageView = new ImageView(image);
        this.setDirection("U");
        imageView.setX(x);
        imageView.setY(y);
        speed = 1;
    }

    public void shoot(List<Wall> walls, List<Tank> tanks){
        Missile missile = new Missile(this, this.x, this.y);
        group.getChildren().add(missile.getImageView());
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                missile.moveMissile();
                if (missile.isIntersectingWall(walls)) {
                    stop();
                    group.getChildren().remove(missile.getImageView());
                }
                Tank tank = missile.getIntersectedTank(tanks);
                if(tank != null){
                    tank.takeDamage(missile.getDamage(), tanks);
                    stop();
                    group.getChildren().remove(missile.getImageView());
                }
            }
        };
        timer.start();
    }

    public void takeDamage(int damage, List<Tank> tanks){
        if(health > 0){
            this.health -= damage;
            if(health <= 0){
                destroyTank(tanks);
            }
        }
    }

    protected void destroyTank(List<Tank> tanks){
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.05), e -> {
                    imageView.setImage(animationImages.get(0));
                    imageView.setX(this.x + 25);
                    imageView.setY(this.y + 25);
                }),
                new KeyFrame(Duration.seconds(0.1), e -> {
                    imageView.setImage(animationImages.get(1));
                    imageView.setX(imageView.getX() - 2.5);
                    imageView.setY(imageView.getY() - 2.5);
                }),
                new KeyFrame(Duration.seconds(0.15), e -> {
                    imageView.setImage(animationImages.get(2));
                    imageView.setX(imageView.getX() - 3.5);
                    imageView.setY(imageView.getY() - 3.5);
                }),
                new KeyFrame(Duration.seconds(0.2), e -> {
                    imageView.setImage(animationImages.get(3));
                    imageView.setX(imageView.getX() - 4);
                    imageView.setY(imageView.getY() - 4);
                }),
                new KeyFrame(Duration.seconds(0.25), e -> {
                    imageView.setImage(animationImages.get(4));
                    imageView.setX(imageView.getX() - 2.5);
                    imageView.setY(imageView.getY() - 2.5);
                }),
                new KeyFrame(Duration.seconds(0.3), e -> {
                    imageView.setImage(animationImages.get(5));
                    imageView.setX(imageView.getX() - 2.5);
                    imageView.setY(imageView.getY() - 2.5);
                }),
                new KeyFrame(Duration.seconds(0.35), e -> {
                    imageView.setImage(animationImages.get(6));
                    imageView.setX(imageView.getX() - 2.5);
                    imageView.setY(imageView.getY() - 2.5);
                }),
                new KeyFrame(Duration.seconds(0.4), e -> {
                    imageView.setImage(animationImages.get(7));
                    imageView.setX(imageView.getX() - 2.5);
                    imageView.setY(imageView.getY() - 2.5);
                }),
                new KeyFrame(Duration.seconds(0.45), e -> {
                    imageView.setImage(animationImages.get(8));
                    imageView.setX(imageView.getX() - 2.5);
                    imageView.setY(imageView.getY() - 2.5);
                }),
                new KeyFrame(Duration.seconds(0.5), e -> {
                    imageView.setImage(animationImages.get(9));
                    imageView.setX(imageView.getX() - 2.5);
                    imageView.setY(imageView.getY() - 2.5);
                }),
                new KeyFrame(Duration.seconds(0.55), e -> {
                    imageView.setImage(animationImages.get(10));
                    imageView.setX(imageView.getX() - 2.5);
                    imageView.setY(imageView.getY() - 2.5);
                }),
                new KeyFrame(Duration.seconds(0.6), e -> group.getChildren().remove(imageView))
        );

        timeline.setCycleCount(1);
        timeline.play();
        tanks.remove(this);
    }

    public boolean isDead(){
        return health <= 0;
    }

}
