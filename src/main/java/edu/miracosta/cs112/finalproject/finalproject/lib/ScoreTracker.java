package edu.miracosta.cs112.finalproject.finalproject.lib;

public class ScoreTracker {

    private int points;

    public ScoreTracker(int points) {
        this.points = points;
    }

    public void addPoint(int pointsToAdd) {
        points += pointsToAdd;
    }

    public int getPoints() {
        return points;
    }

}
