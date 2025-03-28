package edu.miracosta.cs112.finalproject.finalproject.controllers;

import edu.miracosta.cs112.finalproject.finalproject.Entities.CharacterList;
import edu.miracosta.cs112.finalproject.finalproject.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class CharacterSelectController {

    @FXML
    private Button backButton;

    @FXML
    private Button character1Button;

    @FXML
    private Button character2Button;

    // Stats display labels
    @FXML
    private Label nameLabel;

    @FXML
    private Label descLabel;

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

    private CharacterList characterList = new CharacterList();

    @FXML
    public void initialize() {
        character1Button.setText(characterList.getIsaac().getName());
        character2Button.setText(characterList.getCharacter2().getName());
        // Optionally set default stats (e.g., Isaac) on load
        updateStats(characterList.getIsaac());
    }

    @FXML
    protected void selectCharacter1() {
        CharacterList.Playable1 isaac = characterList.getIsaac();
        System.out.println(isaac.getName() + " selected");
        updateStats(isaac);
    }

    @FXML
    protected void selectCharacter2() {
        CharacterList.Playable1 character2 = characterList.getCharacter2();
        System.out.println(character2.getName() + " selected");
        updateStats(character2);
    }

    @FXML
    protected void onBackButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Player.fxml"));
        Scene homeScene = new Scene(fxmlLoader.load(), 1280, 720);
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(homeScene);
        stage.show();
    }

    // Helper method to update stats display
    private void updateStats(CharacterList.Playable1 character) {
        nameLabel.setText("Name: " + character.getName());
        descLabel.setText("Description: " + character.getDescription());
        hpLabel.setText("HP: " + character.getHp());
        dmgLabel.setText("DMG: " + character.getDmg());
        fireRateLabel.setText("Fire Rate: " + character.getFireRate());
        luckLabel.setText("Luck: " + character.getLuck());
        coinsLabel.setText("Coins: " + character.getCoins());
        bombsLabel.setText("Bombs: " + character.getBombs());
        keysLabel.setText("Keys: " + character.getKeys());
    }
}