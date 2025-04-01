package edu.miracosta.cs112.finalproject.finalproject.Entities;

public abstract class Entity {
    private String name = null;
    private String description = null;
    private int fireRate = 0;
    private int HP = 0;
    private int speed = 0;

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

    public void setHP(int HP){
        this.HP = HP;
    }

    public int getHP(){
        return HP;
    }

    public void setSpeed(int speed){
        this.speed = speed;
    }

    public int getSpeed(){
        return speed;
    }

}