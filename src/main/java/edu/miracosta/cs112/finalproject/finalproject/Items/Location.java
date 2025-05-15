package edu.miracosta.cs112.finalproject.finalproject.Items;

public class Location {

    public Location(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double x, y;

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

    //helper method to check coords
    public String getCoordinates() {
        return "Current x,y : " + x + ", " + y;
    }

    //helper method to check distance to another location
    //useful for checking if the enemy is on top of the player's location
    public double distanceTo(Location other) {
        double dx = other.getX() - x;
        double dy = other.getY() - y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
