package edu.miracosta.cs112.finalproject.finalproject.controllers;

import edu.miracosta.cs112.finalproject.finalproject.Entities.CharacterList;
import edu.miracosta.cs112.finalproject.finalproject.Entities.Enemy;
import edu.miracosta.cs112.finalproject.finalproject.Items.Bullet;
import edu.miracosta.cs112.finalproject.finalproject.Items.Location;
import javafx.animation.AnimationTimer;

public class GameLoop extends AnimationTimer {
    private GameController gameController;
    private CharacterList currentCharacter = CharacterList.getInstance();


    public GameLoop(GameController controller) {
        this.gameController = controller;
    }

    @Override
    public void handle(long now) {
        gameController.updatePlayerLoc();
        gameController.updateStats();
        Location currentPlayerLoc = currentCharacter.getCurrentCharacter().getLocation();

        for(Enemy enemy : GameController.enemies) {

            for(Bullet bullet : CharacterList.bullets) {
//              System.out.println("Enemy Loc: " + enemy.getLocation().getCoordinates());
//              System.out.println("Bullet Loc: " + bullet.getLocation().getCoordinates());

                if (bullet.getCircle().getBoundsInParent().intersects(enemy.getTempEnemy().getBoundsInParent())) {
                    // Handle collision (e.g., reduce enemy health)
                    enemy.eliminateEnemy();
                    bullet.removeBullet(gameController.getRoot());
                    GameController.enemies.remove(enemy);
                    CharacterList.bullets.remove(bullet);
                    break; // Stop checking further once bullet hits an enemy
                }
            }

            enemy.chasePlayer(currentPlayerLoc);

            if (enemy.getLocation().distanceTo(currentPlayerLoc) < 1) {
                enemy.attack();
            }
        }

    }
}