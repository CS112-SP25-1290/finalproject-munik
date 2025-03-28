package edu.miracosta.cs112.finalproject.finalproject.controllers;

import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class GameController {

    private int PLAYER_SPEED = 10;

    @FXML
    private Pane root;

    @FXML
    private Rectangle player;

    @FXML
    public void initialize() {
        root.setOnKeyPressed(this::handleMovement);
        root.requestFocus();
    }

    private void handleMovement(KeyEvent event) {
        double x = player.getX();
        double y = player.getY();

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
        double playerWidth = player.getWidth();
        double playerHeight = player.getHeight();

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

        player.setX(x);
        player.setY(y);
    }
}
