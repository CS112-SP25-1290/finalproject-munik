package edu.miracosta.cs112.finalproject.finalproject.Entities;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public abstract class Entity {
    private String name = null;
    private String description = null;
    private int fireRate = 0;
    private int HP = 0;
    private int speed = 0;

    public Entity(String name, String description, int fireRate, int HP, int speed) {
        this.name = name;
        this.description = description;
        this.fireRate = fireRate;
        this.HP = HP;
        this.speed = speed;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    private double x;
    private double y;

    //create an enemy from a subclass
    protected Entity() {
    }

    //set enemy name
    public void setName(String name){
        this.name = name;
    }
    //returns name
    public String getName(){
        return name;
    }
    //sets desc
    public void setDescription(String description){
        this.description = description;
    }
    //returns desc
    public String getDescription(){
        return description;
    }

    public void setFireRate(int fireRate){
        this.fireRate = fireRate;
    }

    public int getFireRate(){
        return fireRate;
    }

    public void setSpeed(int speed){
        this.speed = speed;
    }

    public int getSpeed(){
        return speed;
    }

    public void shootRandomly(double startX, double startY, Pane root) {
        // Create the circle
        Circle circle = new Circle();
        circle.setCenterX(startX);
        circle.setCenterY(startY);
        circle.setRadius(10);
        circle.setFill(Color.RED);

        // Add the circle to the scene
        root.getChildren().add(circle);

        double angle = Math.random() * 2 * Math.PI;
        double speed = 5; // Adjust speed as necessary
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