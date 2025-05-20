package edu.miracosta.cs112.finalproject.finalproject.Entities;

import edu.miracosta.cs112.finalproject.finalproject.Items.Location;
import edu.miracosta.cs112.finalproject.finalproject.Main;
import edu.miracosta.cs112.finalproject.finalproject.controllers.GameController;
import edu.miracosta.cs112.finalproject.finalproject.lib.AttackException;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class Enemy extends Entity {
    private final Pane root;
    private CharacterList characterList = CharacterList.getInstance();
    private double x, y;
    private Location loc;
    private int ENEMY_SPEED = 1;
    private ImageView tempEnemy;
    private long lastAttackTime = 0; // Stores the time of last attack
    private final long attackCooldownMillis = 1000; // 1 second cooldown (you can adjust this)

    public Enemy(Pane root) {
        super("Enemy 1", "Temp Description", 4, 50, 5);
        this.root = root;
    }

    public void spawnEntity() {
        System.out.println("Started spawning enemy");
        if(root == null) return;

        System.out.println("Root is nonnull");
        tempEnemy = new ImageView(new Image(getClass().getResource("/edu/miracosta/cs112/finalproject/finalproject/images/enemy.png").toExternalForm()));

        Point2D randomLocation = getRandomLocation(root);
        tempEnemy.setX(randomLocation.getX());
        tempEnemy.setY(randomLocation.getY());
        x = randomLocation.getX();
        y = randomLocation.getY();
        loc = new Location(x, y);

        System.out.println("Random X: " + randomLocation.getX() + " Random Y: " + randomLocation.getY());

        root.getChildren().add(tempEnemy);
    }

    public Point2D getRandomLocation(Pane pane) {
        Random random = new Random();
        System.out.println(pane.getHeight() + " " + pane.getWidth());

        double x = random.nextDouble() * 1280;
        double y = random.nextDouble() * 720;

        return new Point2D(x, y);
    }

    public void attack(Stage stage, GameController gameController) throws AttackException {
        long currentTime = System.currentTimeMillis();

        //if this statement passes, that means attack is still on cooldown
        if (currentTime - lastAttackTime < attackCooldownMillis) {
            return;
        }

        lastAttackTime = currentTime;

        CharacterList.PlayableCharacter currentCharacter = characterList.getCurrentCharacter();

        if(currentCharacter.getHp() - 1 <= 0) {
            //stopping our game loop
            gameController.getGameLoop().stop();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("GameOver.fxml"));
                Scene charSelectScene = new Scene(fxmlLoader.load(), 1280, 720); // Adjust size as needed
                stage.setScene(charSelectScene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                throw new AttackException("Attack is on cooldown!");
            }
            return;
        }

        currentCharacter.setHp(currentCharacter.getHp() - 1);
        gameController.flashRed();
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

    public ImageView getTempEnemy() {
        return tempEnemy;
    }
}
