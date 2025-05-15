package edu.miracosta.cs112.finalproject.finalproject.Entities;

import edu.miracosta.cs112.finalproject.finalproject.Items.Location;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Enemy extends Entity {
    private final Pane root;
    private CharacterList characterList = CharacterList.getInstance();
    private double x, y;
    private Location loc;
    private int ENEMY_SPEED = 1;
    private Rectangle tempEnemy;

    public Enemy(Pane root) {
        super("Enemy 1", "Temp Description", 4, 50, 5);
        this.root = root;
    }

    public void spawnEntity() {
        System.out.println("Started spawning enemy");
        if(root == null) return;

        System.out.println("Root is nonnull");
        tempEnemy = new Rectangle();

        Point2D randomLocation = getRandomLocation(root);
        tempEnemy.setX(randomLocation.getX());
        tempEnemy.setY(randomLocation.getY());
        x = randomLocation.getX();
        y = randomLocation.getY();
        loc = new Location(x, y);

        System.out.println("Random X: " + randomLocation.getX() + " Random Y: " + randomLocation.getY());

        tempEnemy.setHeight(100);
        tempEnemy.setWidth(50);

        root.getChildren().add(tempEnemy);
    }

    public Point2D getRandomLocation(Pane pane) {
        Random random = new Random();
        System.out.println(pane.getHeight() + " " + pane.getWidth());

        double x = random.nextDouble() * 1280;
        double y = random.nextDouble() * 720;

        return new Point2D(x, y);
    }

    public void attack() {

    }

    public void eliminateEnemy() {
        root.getChildren().remove(tempEnemy);
    }

    public void chasePlayer(Location playerLoc) {
        if(this.loc == null || this.tempEnemy == null) return;
        Location enemyLoc = this.getLocation(); // Assume your enemy has a Location

        double dx = playerLoc.getX() - enemyLoc.getX();
        double dy = playerLoc.getY() - enemyLoc.getY();

        double magnitude = Math.sqrt(dx * dx + dy * dy);

        if (magnitude > 0) {
            dx /= magnitude;
            dy /= magnitude;

            // Move enemy
            double newX = enemyLoc.getX() + dx * ENEMY_SPEED;
            double newY = enemyLoc.getY() + dy * ENEMY_SPEED;

            // Update enemy's location
            enemyLoc.setX(newX);
            enemyLoc.setY(newY);

            // Also update any graphical representation if needed
            this.tempEnemy.setX(newX);
            this.tempEnemy.setY(newY);
        }
    }

    public Location getLocation() {
        return loc;
    }

    public Rectangle getTempEnemy() {
        return tempEnemy;
    }
}
