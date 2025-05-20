package edu.miracosta.cs112.finalproject.finalproject.Items;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class Bullet {

    Circle circle;
    Location location;

    public Bullet(double startX, double startY) {
        circle = new Circle();
        circle.setCenterX(startX);
        circle.setCenterY(startY);
        location = new Location(startX, startY);
        circle.setRadius(7);
    }

    public Circle getCircle() {
        return circle;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(double x, double y) {
        this.location = new Location(x, y);
    }

    public void removeBullet(Pane root) {
        root.getChildren().remove(circle);
    }

}
