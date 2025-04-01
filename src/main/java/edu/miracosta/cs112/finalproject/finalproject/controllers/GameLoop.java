package edu.miracosta.cs112.finalproject.finalproject.controllers;

import javafx.animation.AnimationTimer;

public class GameLoop extends AnimationTimer {
    private GameController gameController;

    public GameLoop(GameController controller) {
        this.gameController = controller;
    }

    @Override
    public void handle(long now) {

        gameController.updatePlayerLoc();
        System.out.println("Game Loop update");
    }
}