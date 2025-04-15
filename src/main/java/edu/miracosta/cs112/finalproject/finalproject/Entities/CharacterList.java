package edu.miracosta.cs112.finalproject.finalproject.Entities;


import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

//creating a list of playable characters
//In order to create new characters (name, description, HP, DMG, Fire Rate, Luck, Coins, Bombs, Keys)
public class CharacterList {
    private static final CharacterList instance = new CharacterList();

    public static CharacterList getInstance() {
        return instance;
    }

    public static class PlayableCharacter extends Entity {
        private String name;
        private String description;

        private int hp, speed, dmg, fireRate, luck, coins, bombs, keys;
        private double x;

        public double getY() {
            return y;
        }

        public void setY(double y) {
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public void setX(double x) {
            this.x = x;
        }

        private double y;

        public PlayableCharacter(String name, String description, int hp, int speed, int dmg, int fireRate, int luck, int coins, int bombs, int keys) {
            this.name = name;
            this.description = description;
            this.hp = hp;
            this.speed = speed;
            this.dmg = dmg;
            this.fireRate = fireRate;
            this.luck = luck;
            this.coins = coins;
            this.bombs = bombs;
            this.keys = keys;
        }
        //Getters
        public String getName() {
            return name;
        }
        public String getDescription() {
            return description;
        }
        public int getHp() {
            return hp;
        }
        public int getSpeed() {
            return speed;
        }
        public int getDmg() {
            return dmg;
        }
        public int getFireRate() {
            return fireRate;
        }
        public int getLuck() {
            return luck;
        }
        public int getCoins() {
            return coins;
        }
        public int getBombs() {
            return bombs;
        }
        public int getKeys() {
            return keys;
        }


        public void shoot(double mouseX, double mouseY, double startX, double startY, Pane root) {
            Circle circle = new Circle();
            circle.setCenterX(startX);
            circle.setCenterY(startY);
            circle.setRadius(7);
            circle.setFill(Color.GHOSTWHITE);

            System.out.println("Circle Created");

            // Add the circle to the scene
            root.getChildren().add(circle);
            System.out.println("Added circle to the scene");

            double deltaX = mouseX - startX;
            double deltaY = mouseY - startY;

            // Get the angle in radians
            double angle = Math.atan2(deltaY, deltaX);
            double speed = 5;
            double dx = Math.cos(angle) * speed;
            double dy = Math.sin(angle) * speed;

            // AnimationTimer to move the circle
            new AnimationTimer() {
                @Override
                public void handle(long now) {
                    circle.setCenterX(circle.getCenterX() + dx);
                    circle.setCenterY(circle.getCenterY() + dy);

                    // Check if the circle has reached the scene's borders
                    if (circle.getCenterX() <= 0 || circle.getCenterX() >= 1280 ||
                            circle.getCenterY() <= 0 || circle.getCenterY() >= 720) {
                        // Stop the timer and remove the circle
                        stop();
                        root.getChildren().remove(circle);
                        System.out.println("Cleaned up circle");
                    }
                }
            }.start();
        }
    }

    private PlayableCharacter character1 = new PlayableCharacter("Isaac", "Average stats across the board", 3, 8, 3, 3, 0, 3, 1, 0);
    private PlayableCharacter character2 = new PlayableCharacter("Maggie", "Beefier but weaker", 4, 6, 2, 2, 1, 2, 0, 1);
    private PlayableCharacter character3 = new PlayableCharacter("Garbo","The ultimate spy", 3, 10, 2, 3, 6, 0, 0, 3);
    private PlayableCharacter character4 = new PlayableCharacter("Erwin", "Commander", 3, 6, 5, 2, 2, 2, 1, 0);
    public PlayableCharacter getIsaac() { return character1; }
    public PlayableCharacter getCharacter2() { return character2; }
    public PlayableCharacter getCharacter3() { return character3; }
    public PlayableCharacter getCharacter4() { return character4; }

    public PlayableCharacter getCurrentCharacter() {
        return currentCharacter;
    }

    public void setCurrentCharacter(PlayableCharacter currentCharacter) {
        this.currentCharacter = currentCharacter;
    }

    private PlayableCharacter currentCharacter = character1;


}