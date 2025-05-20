package edu.miracosta.cs112.finalproject.finalproject.controllers;

import edu.miracosta.cs112.finalproject.finalproject.Entities.CharacterList;
import edu.miracosta.cs112.finalproject.finalproject.Entities.Enemy;
import edu.miracosta.cs112.finalproject.finalproject.lib.AudioManager;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

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
    private Label pointsLabel;

    @FXML
    private Label roundLabel;

    @FXML
    private Label timerLabel;

    private Pane redFlashOverlay;

    private static final GameController instance = new GameController();

    private final CharacterList characterList = CharacterList.getInstance();
    private RoundManager roundManager;

    private int PLAYER_SPEED = 10;

    @FXML
    private Pane root;

    @FXML
    private ImageView player;  // Changed from Rectangle to ImageView

    private Set<KeyCode> movementSet = new HashSet<KeyCode>();
    public static ArrayList<Enemy> enemies = new ArrayList<>();

    double x;
    double y;

    private GameLoop gameLoop;
    private AudioManager audioManager;

    @FXML
    public void initialize() {
        audioManager = new AudioManager("/edu/miracosta/cs112/finalproject/finalproject/sounds/gameplay_track.mp3");
        audioManager.playBackgroundMusic();

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

        // Initialize round manager after UI elements
        roundManager = new RoundManager(root, roundLabel, timerLabel);

        // Clear any existing enemies
        enemies.clear();

        // Start the round-based game
        roundManager.startGame();

        //red flash for when a player takes damage
        redFlashOverlay = new Pane();
        redFlashOverlay.setStyle("-fx-background-color: rgba(255, 0, 0, 0.5);");
        redFlashOverlay.setVisible(false);
        redFlashOverlay.setMouseTransparent(true);
        redFlashOverlay.setPrefSize(root.getPrefWidth(), root.getPrefHeight());

        root.getChildren().add(redFlashOverlay); // make sure it's above all elements


        Platform.runLater(() -> {
            Stage stage = (Stage) dmgLabel.getScene().getWindow();
            gameLoop = new GameLoop(this, stage);
            gameLoop.start();
        });
    }

    public void updateStats() {
        CharacterList.PlayableCharacter currentCharacter = characterList.getCurrentCharacter();

        nameLabel.setText("Name: " + currentCharacter.getName());
        hpLabel.setText("HP: " + currentCharacter.getHp());
        dmgLabel.setText("DMG: " + currentCharacter.getDmg());
        pointsLabel.setText("Score: " + currentCharacter.getScoretracker().getPoints());
        xLocation.setText("X Loc: " + Math.round(currentCharacter.getX()));
        yLocation.setText("Y Loc: " + Math.round(currentCharacter.getY()));
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
    }

    private void handleShoot(double mouseX, double mouseY) {
        double startX = player.getLayoutX() + player.getX() + player.getFitWidth() / 2;
        double startY = player.getLayoutY() + player.getY() + player.getFitHeight() / 2;

        CharacterList.PlayableCharacter currentCharacter = characterList.getCurrentCharacter();
        currentCharacter.shoot(mouseX, mouseY, startX, startY, root);
    }

    public void removeEnemy(Enemy enemy) {
        if (roundManager != null) {
            roundManager.removeEnemy(enemy);
        }
        enemies.remove(enemy);
    }

    public Pane getRoot() {
        return root;
    }

    public RoundManager getRoundManager() {
        return roundManager;
    }

    public GameLoop getGameLoop() {
        return gameLoop;
    }

    public static GameController getInstance() {
        return instance;
    }

    public void flashRed() {
        if (redFlashOverlay == null) return;

        redFlashOverlay.setOpacity(1.0);
        redFlashOverlay.setVisible(true);

        FadeTransition fade = new FadeTransition(Duration.millis(300), redFlashOverlay);
        fade.setFromValue(1.0);
        fade.setToValue(0.0);
        fade.setOnFinished(e -> redFlashOverlay.setVisible(false));
        fade.play();
    }
}