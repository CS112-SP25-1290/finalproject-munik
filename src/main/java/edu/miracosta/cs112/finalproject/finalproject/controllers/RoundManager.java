package edu.miracosta.cs112.finalproject.finalproject.controllers;

import edu.miracosta.cs112.finalproject.finalproject.Entities.CharacterList;
import edu.miracosta.cs112.finalproject.finalproject.Entities.Enemy;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class RoundManager {
    private static final int MAX_ROUNDS = 40;
    private static final int BASE_ENEMIES = 3;
    private static final int ENEMIES_INCREMENT = 1;
    private static final int SCALING_FACTOR = 2;

    private int currentRound = 0;
    private Timeline roundTimer;
    private Pane gamePane;
    private Label roundLabel;
    private Label timerLabel;
    private int secondsRemaining;
    private List<Enemy> activeEnemies = new ArrayList<>();
    private CharacterList characterList = CharacterList.getInstance();

    public RoundManager(Pane gamePane, Label roundLabel, Label timerLabel) {
        this.gamePane = gamePane;
        this.roundLabel = roundLabel;
        this.timerLabel = timerLabel;
        initializeRoundTimer();
    }

    private void initializeRoundTimer() {
        roundTimer = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    secondsRemaining--;
                    updateTimerDisplay();

                    if (secondsRemaining <= 0) {
                        if (currentRound < MAX_ROUNDS) {
                            startNextRound();
                        } else {
                            gameWon();
                        }
                    }
                })
        );
        roundTimer.setCycleCount(secondsRemaining);
    }

    public void startGame() {
        currentRound = 0;
        startNextRound();
    }

    public void startNextRound() {
        // Clear any remaining enemies from previous round if needed
        // For now we're letting them persist between rounds

        currentRound++;
        secondsRemaining = calculateRoundDuration(currentRound);


        // Update the round display
        updateRoundDisplay();
        updateTimerDisplay();

        // Spawn enemies for this round
        spawnEnemiesForRound();

        // Start the timer for the next round
        roundTimer.stop();
        roundTimer = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    secondsRemaining--;
                    updateTimerDisplay();

                    if (secondsRemaining <= 0) {
                        if (currentRound < MAX_ROUNDS) {
                            startNextRound();
                        } else {
                            gameWon();
                        }
                    }
                })
        );
        roundTimer.setCycleCount(secondsRemaining);
        roundTimer.play();
    }

    private void spawnEnemiesForRound() {
        int enemiesThisRound = calculateEnemiesForRound(currentRound);

        for (int i = 0; i < enemiesThisRound; i++) {
            Enemy enemy = new Enemy(gamePane);
            enemy.spawnEntity();
            activeEnemies.add(enemy);
            GameController.enemies.add(enemy);
        }

        System.out.println("Round " + currentRound + ": Spawned " + enemiesThisRound + " enemies");
    }

    private int calculateEnemiesForRound(int round) {
        // Calculate number of enemies based on the round number
        // This formula gives BASE_ENEMIES for round 1 and increases by ENEMIES_INCREMENT each round
        return BASE_ENEMIES + (round - 1) * ENEMIES_INCREMENT;
    }

    private void updateRoundDisplay() {
        if (roundLabel != null) {
            roundLabel.setText("Round: " + currentRound + "/" + MAX_ROUNDS);
        }
    }

    private void updateTimerDisplay() {
        if (timerLabel != null) {
            timerLabel.setText("Next Round: " + secondsRemaining + "s");
        }
    }

    private void gameWon() {
        roundTimer.stop();
        if (timerLabel != null) {
            timerLabel.setText("Game Complete!");
        }
        // Additional game completion logic could go here
    }

    public boolean isGameActive() {
        return currentRound > 0 && currentRound <= MAX_ROUNDS;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public void pauseGame() {
        if (roundTimer != null) {
            roundTimer.pause();
        }
    }

    public void resumeGame() {
        if (roundTimer != null && isGameActive()) {
            roundTimer.play();
        }
    }

    public void stopGame() {
        if (roundTimer != null) {
            roundTimer.stop();
        }
    }

    public List<Enemy> getActiveEnemies() {
        return activeEnemies;
    }

    public void removeEnemy(Enemy enemy) {
        activeEnemies.remove(enemy);
        CharacterList.PlayableCharacter currentCharacter = characterList.getCurrentCharacter();
        currentCharacter.getScoretracker().addPoint(1);
    }

    private int calculateRoundDuration(int round) {
        int newTime = (int) Math.round(4 + Math.log(round + 1) * SCALING_FACTOR);

        return newTime > 7 ? 7 : newTime;
    }
}