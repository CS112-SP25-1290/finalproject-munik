package edu.miracosta.cs112.finalproject.finalproject.controllers;

import edu.miracosta.cs112.finalproject.finalproject.Entities.CharacterList;
import edu.miracosta.cs112.finalproject.finalproject.Entities.Enemy;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class GameController {
    @FXML
    private Label nameLabel, hpLabel;

    @FXML
    private Label xLocation, yLocation;

    @FXML
    private Label dmgLabel;

    @FXML
    private Label fireRateLabel;

    @FXML
    private Label luckLabel;

    @FXML
    private Label coinsLabel;

    @FXML
    private Label bombsLabel;

    @FXML
    private Label keysLabel;

    private CharacterList characterList = CharacterList.getInstance();

    private int PLAYER_SPEED = 10;

    @FXML
    private Pane root;

    @FXML
    private ImageView player;  // Changed from Rectangle to ImageView

    private Set<KeyCode> movementSet = new HashSet<KeyCode>();
    public static ArrayList<Enemy> enemies = new ArrayList<>();

    double x;
    double y;

    @FXML
    public void initialize() {
        System.out.println("Started");
        //sets stats to the selected character
        CharacterList.PlayableCharacter currentCharacter = characterList.getCurrentCharacter();
        if (currentCharacter == null) {
            currentCharacter = new CharacterList().getIsaac();
        }

        PLAYER_SPEED = currentCharacter.getSpeed();
        updateStats();

        root.setOnKeyPressed(event -> {
            KeyCode key = event.getCode();
            movementSet.add(key);
        });

        root.setOnKeyReleased(event -> {
            KeyCode key = event.getCode();
            movementSet.remove(key);
        });

        root.requestFocus();

        root.setOnMouseClicked(mouseEvent -> {
            handleShoot(mouseEvent.getX(), mouseEvent.getY());
            root.requestFocus();
        });

        x = player.getLayoutX() + player.getX();
        y = player.getLayoutY() + player.getY();

        for(int i = 0; i < 3; i++) {
            Enemy enemy = new Enemy(root);
            enemy.spawnEntity();
            enemies.add(enemy);
        }
        GameLoop gameLoop = new GameLoop(this);
        gameLoop.start();
    }

    public void updateStats() {
        CharacterList.PlayableCharacter currentCharacter = characterList.getCurrentCharacter();

        nameLabel.setText("Name: " + currentCharacter.getName());
        hpLabel.setText("HP: " + currentCharacter.getHp());
        dmgLabel.setText("DMG: " + currentCharacter.getDmg());
        fireRateLabel.setText("Fire Rate: " + currentCharacter.getFireRate());
        luckLabel.setText("Luck: " + currentCharacter.getLuck());
        coinsLabel.setText("Coins: " + currentCharacter.getCoins());
        bombsLabel.setText("Bombs: " + currentCharacter.getBombs());
        keysLabel.setText("Keys: " + currentCharacter.getKeys());
        xLocation.setText("X Loc: " + currentCharacter.getX());
        yLocation.setText("Keys: " + currentCharacter.getY());
    }

    public void updatePlayerLoc() {
        double dx = 0; // Change in X coordinate
        double dy = 0; // Change in Y coordinate

        if (movementSet.contains(KeyCode.A)) {
            dx -= PLAYER_SPEED;
        }

        if (movementSet.contains(KeyCode.D)) {
            dx += PLAYER_SPEED;
        }

        if (movementSet.contains(KeyCode.W)) {
            dy -= PLAYER_SPEED;
        }

        if (movementSet.contains(KeyCode.S)) {
            dy += PLAYER_SPEED;
        }

        // Normalize the movement vector
        double magnitude = Math.sqrt(dx * dx + dy * dy);
        if (magnitude > 0) {
            dx /= magnitude;
            dy /= magnitude;
        }

        // Apply normalized movement
        double newX = player.getLayoutX() + player.getX() + dx * PLAYER_SPEED;
        double newY = player.getLayoutY() + player.getY() + dy * PLAYER_SPEED;

        double paneWidth = root.getWidth();
        double paneHeight = root.getHeight();
        double playerWidth = player.getFitWidth();   // Use fitWidth for ImageView
        double playerHeight = player.getFitHeight(); // Use fitHeight for ImageView

        // Boundary checking to keep the player within the pane
        if (newX < 0) {
            newX = 0;
        } else if (newX + playerWidth > paneWidth) {
            newX = paneWidth - playerWidth;
        }

        if (newY < 0) {
            newY = 0;
        } else if (newY + playerHeight > paneHeight) {
            newY = paneHeight - playerHeight;
        }

        // Update player position
        double finalX = newX - player.getLayoutX();
        double finalY = newY - player.getLayoutY();

        player.setX(finalX);
        player.setY(finalY);

        characterList.getCurrentCharacter().setX(finalX);
        characterList.getCurrentCharacter().setY(finalY);

//        System.out.println(finalX + " " + finalY);
    }

    private void handleShoot(double mouseX, double mouseY) {
        double startX = player.getLayoutX() + player.getX() + player.getFitWidth() / 2;
        double startY = player.getLayoutY() + player.getY() + player.getFitHeight() / 2;

        CharacterList.PlayableCharacter currentCharacter = characterList.getCurrentCharacter();
        currentCharacter.shoot(mouseX, mouseY, startX, startY, root);
    }

    public Pane getRoot() {
        return root;
    }

}