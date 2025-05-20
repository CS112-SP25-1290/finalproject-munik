package edu.miracosta.cs112.finalproject.finalproject.controllers;

import edu.miracosta.cs112.finalproject.finalproject.Entities.CharacterList;
import edu.miracosta.cs112.finalproject.finalproject.Entities.Enemy;
import edu.miracosta.cs112.finalproject.finalproject.Items.Bullet;
import edu.miracosta.cs112.finalproject.finalproject.Items.Location;
import edu.miracosta.cs112.finalproject.finalproject.lib.AttackException;
import javafx.animation.AnimationTimer;
import javafx.stage.Stage;

import java.util.Iterator;

public class GameLoop extends AnimationTimer {
    private GameController gameController;
    private CharacterList currentCharacter = CharacterList.getInstance();
    private Stage stage;

    public GameLoop getInstance() {
        return this;
    }

    public GameLoop(GameController controller, Stage stage) {
        this.gameController = controller;
        this.stage = stage;
    }


    @Override
    public void handle(long now) {
        gameController.updatePlayerLoc();
        gameController.updateStats();
        Location currentPlayerLoc = currentCharacter.getCurrentCharacter().getLocation();

        // Use iterator to safely remove enemies during iteration
        Iterator<Enemy> enemyIterator = GameController.enemies.iterator();
        while (enemyIterator.hasNext()) {
            Enemy enemy = enemyIterator.next();

            // Skip this enemy if it's been removed or not properly initialized
            if (enemy == null || enemy.getTempEnemy() == null || enemy.getLocation() == null) {
                enemyIterator.remove();
                continue;
            }

            // Check for bullet collisions
            Iterator<Bullet> bulletIterator = CharacterList.bullets.iterator();
            boolean enemyHit = false;

            while (bulletIterator.hasNext() && !enemyHit) {
                Bullet bullet = bulletIterator.next();

                if (bullet.getCircle() != null &&
                        bullet.getCircle().getBoundsInParent().intersects(enemy.getTempEnemy().getBoundsInParent())) {
                    // Handle collision
                    enemy.eliminateEnemy();
                    bullet.removeBullet(gameController.getRoot());
                    enemyIterator.remove();
                    bulletIterator.remove();
                    gameController.removeEnemy(enemy); // Notify RoundManager
                    enemyHit = true;
                }
            }

            if (!enemyHit) {
                // Update enemy to chase player
                enemy.chasePlayer(currentPlayerLoc);

                // Check if enemy is touching player (could implement damage here)
                if (enemy.getLocation().distanceTo(currentPlayerLoc) < 50) {
                    try {
                        enemy.attack(stage, gameController);
                    } catch (AttackException exc) {
                        exc.printStackTrace();
                    }

                }
            }
        }
    }
}