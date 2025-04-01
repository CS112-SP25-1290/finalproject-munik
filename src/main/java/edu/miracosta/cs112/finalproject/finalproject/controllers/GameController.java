package edu.miracosta.cs112.finalproject.finalproject.controllers;

import edu.miracosta.cs112.finalproject.finalproject.Entities.CharacterList;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;

import java.util.HashSet;
import java.util.Set;

public class GameController {
    @FXML
    private Label hpLabel;

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

    private CharacterList.PlayableCharacter currentCharacter;

    private int PLAYER_SPEED = 10;

    @FXML
    private Pane root;

    @FXML
    private ImageView player;  // Changed from Rectangle to ImageView

    private Set<KeyCode> movementSet = new HashSet<KeyCode>();

    double x;
    double y;

    @FXML
    public void initialize() {
        System.out.println("STarted");
        //sets stats to the selected character
        currentCharacter = CharacterSelectController.selectedCharacter;
        if (currentCharacter == null) {
            currentCharacter = new CharacterList().getIsaac();
        }

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

        root.setOnMouseClicked(event -> root.requestFocus());

        x = player.getLayoutX() + player.getX();
        y = player.getLayoutY() + player.getY();

        GameLoop gameLoop = new GameLoop(this);
        gameLoop.start();
    }

    private void updateStats() {
        hpLabel.setText("HP: " + currentCharacter.getHp());
        dmgLabel.setText("DMG: " + currentCharacter.getDmg());
        fireRateLabel.setText("Fire Rate: " + currentCharacter.getFireRate());
        luckLabel.setText("Luck: " + currentCharacter.getLuck());
        coinsLabel.setText("Coins: " + currentCharacter.getCoins());
        bombsLabel.setText("Bombs: " + currentCharacter.getBombs());
        keysLabel.setText("Keys: " + currentCharacter.getKeys());
    }

    public void updatePlayerLoc() {

        if (movementSet.contains(KeyCode.A) || movementSet.contains(KeyCode.LEFT)) {
            x = x - PLAYER_SPEED;
        }

        if (movementSet.contains(KeyCode.D) || movementSet.contains(KeyCode.RIGHT)) {
            x = x + PLAYER_SPEED;
        }

        if (movementSet.contains(KeyCode.W) || movementSet.contains(KeyCode.UP)) {
            y = y - PLAYER_SPEED;
        }

        if (movementSet.contains(KeyCode.S) || movementSet.contains(KeyCode.DOWN)) {
            y = y + PLAYER_SPEED;
        }

        double paneWidth = root.getWidth();
        double paneHeight = root.getHeight();
        double playerWidth = player.getFitWidth();   // Use fitWidth for ImageView
        double playerHeight = player.getFitHeight(); // Use fitHeight for ImageView

        // Boundary checking to keep the player within the pane
        if (x < 0) {
            x = 0;
        } else if (x + playerWidth > paneWidth) {
            x = paneWidth - playerWidth;
        }

        if (y < 0) {
            y = 0;
        } else if (y + playerHeight > paneHeight) {
            y = paneHeight - playerHeight;
        }

        player.setX(x - player.getLayoutX());
        player.setY(y - player.getLayoutY());
    }
}