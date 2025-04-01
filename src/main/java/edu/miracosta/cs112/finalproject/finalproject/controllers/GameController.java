package edu.miracosta.cs112.finalproject.finalproject.controllers;

import edu.miracosta.cs112.finalproject.finalproject.Entities.CharacterList;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;



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


    @FXML
    public void initialize() {
        //sets stats to the selected character
        currentCharacter = CharacterSelectController.selectedCharacter;
        if (currentCharacter == null) {
            // Fallback to a default character if none selected
            currentCharacter = new CharacterList().getIsaac();
        }

        updateStats();

        root.setOnKeyPressed(this::handleMovement);
        root.requestFocus();

        root.setOnMouseClicked(event -> root.requestFocus());
    }
    //updates stats in Top left of running game
    private void updateStats() {
        hpLabel.setText("HP: " + currentCharacter.getHp());
        dmgLabel.setText("DMG: " + currentCharacter.getDmg());
        fireRateLabel.setText("Fire Rate: " + currentCharacter.getFireRate());
        luckLabel.setText("Luck: " + currentCharacter.getLuck());
        coinsLabel.setText("Coins: " + currentCharacter.getCoins());
        bombsLabel.setText("Bombs: " + currentCharacter.getBombs());
        keysLabel.setText("Keys: " + currentCharacter.getKeys());
    }

    private void handleMovement(KeyEvent event) {
        double x = player.getLayoutX() + player.getX();
        double y = player.getLayoutY() + player.getY();

        if (event.getCode() == KeyCode.A || event.getCode() == KeyCode.LEFT) {
            x = x - PLAYER_SPEED;
        } else if (event.getCode() == KeyCode.D) {
            x = x + PLAYER_SPEED;
        }

        if (event.getCode() == KeyCode.W || event.getCode() == KeyCode.UP) {
            y = y - PLAYER_SPEED;
        } else if (event.getCode() == KeyCode.S) {
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