package edu.miracosta.cs112.finalproject.finalproject.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.util.HashSet;
import java.util.Set;

public class GameController {

    private final Set<KeyCode> keysPressed = new HashSet<>();

    private int PLAYER_SPEED = 10;

    @FXML
    private Pane root;

    @FXML
    private ImageView player;  // Changed from Rectangle to ImageView

    @FXML
    public void initialize() {
        root.requestFocus();
        root.setOnKeyPressed(this::handleKeyPress);
        root.setOnKeyReleased(this::handleKeyRelease);
        root.setOnMouseClicked(event -> root.requestFocus());
    }

    private void handleKeyPress(KeyEvent event) {
        keysPressed.add(event.getCode());
        updatePlayer();
    }

    private void handleKeyRelease(KeyEvent event) {
        keysPressed.remove(event.getCode());
        updatePlayer();
    }

    public void updatePlayer() {
        double x = player.getLayoutX() + player.getX();
        double y = player.getLayoutY() + player.getY();

        if (keysPressed.contains(KeyCode.A) || keysPressed.contains(KeyCode.LEFT)) {
            x -= PLAYER_SPEED;
        } else if (keysPressed.contains(KeyCode.D) || keysPressed.contains(KeyCode.RIGHT)) {
            x += PLAYER_SPEED;
        }

        if (keysPressed.contains(KeyCode.W) || keysPressed.contains(KeyCode.UP)) {
            y -= PLAYER_SPEED;
        } else if (keysPressed.contains(KeyCode.S) || keysPressed.contains(KeyCode.DOWN)) {
            y += PLAYER_SPEED;
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