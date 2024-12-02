package edu.tcu.cs.tankwar.classes;

import javafx.scene.Group;
import javafx.scene.control.ProgressBar;

import java.util.List;

public class PlayerTank extends Tank{

    ProgressBar healthBar = new ProgressBar(1.0);
    public PlayerTank(Group group, double x, double y) {
        super(group, x, y);
        health = 100;
        healthBar.setStyle("-fx-accent: green;");
    }

    @Override
    public void takeDamage(int damage, List<Tank> tanks){
        if(health > 0){
            this.health -= damage;
            double healthPercentage = (double) this.health / 100;
            healthBar.setProgress(healthPercentage);
            if(health <= 0){
                destroyTank(tanks);
            }
        }
    }

    public void healTank(int health){
        this.health += health;
        double healthPercentage = (double) this.health / 100;
        healthBar.setProgress(healthPercentage);
    }

    public ProgressBar getHealthBar() {
        return healthBar;
    }
}
