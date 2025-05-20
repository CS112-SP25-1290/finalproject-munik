package edu.miracosta.cs112.finalproject.finalproject;

import edu.miracosta.cs112.finalproject.finalproject.Entities.CharacterList;
import edu.miracosta.cs112.finalproject.finalproject.controllers.GameController;
import edu.miracosta.cs112.finalproject.finalproject.controllers.GameLoop;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Player.fxml"));
        Scene homeScreen = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("Cave Fighter");
        stage.setScene(homeScreen);
        stage.show();
    }
}