package edu.miracosta.cs112.finalproject.finalproject.controllers;

import edu.miracosta.cs112.finalproject.finalproject.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeScreenController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onPlayButtonClick() throws IOException  {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Game.fxml"));
        Scene gameScene = new Scene(fxmlLoader.load(), 1280, 720);

        Stage stage = (Stage) welcomeText.getScene().getWindow();

        stage.setScene(gameScene);
        stage.show();
    }

    @FXML
    protected void onQuitButtonClick() {
        System.exit(0);
    }

    @FXML
    protected void onSelectCharButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("SelectChar.fxml"));
        Scene charSelectScene = new Scene(fxmlLoader.load(), 1280, 720); // Adjust size as needed
        Stage stage = (Stage) welcomeText.getScene().getWindow();
        stage.setScene(charSelectScene);
        stage.show();
    }
}