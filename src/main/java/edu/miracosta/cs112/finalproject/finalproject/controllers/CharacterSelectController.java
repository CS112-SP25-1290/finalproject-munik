package edu.miracosta.cs112.finalproject.finalproject.controllers;

import edu.miracosta.cs112.finalproject.finalproject.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class CharacterSelectController {

    @FXML
    private Button backButton;

    @FXML
    protected void selectCharacter1() {
        System.out.println("Character 1 selected");
    }

    @FXML
    protected void selectCharacter2() {
        System.out.println("Character 2 selected");
    }

    @FXML
    protected void onBackButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Player.fxml"));
        Scene homeScene = new Scene(fxmlLoader.load(), 1280, 720);

        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(homeScene);
        stage.show();
    }
}