package edu.miracosta.cs112.finalproject.finalproject.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class GameOverController {

    @FXML
    private Label gameOverLabel;

    @FXML
    private Button exitButton;

    @FXML
    private void initialize() {
        gameOverLabel.setText("Game Over!");
        exitButton.setOnAction(e -> {
            // Close the game
            Stage stage = (Stage) exitButton.getScene().getWindow();
            stage.close();
        });
    }
}
