package edu.miracosta.cs112.finalproject.finalproject.Items;

public abstract class ItemsSTATS {
    private String name = null;
    private int itemID = 0;
    private int itemPoolID = 0;
    private int HPup = 0;
    private int HPdown = 0;
    private double damageUp = 0;
    private double damageDown = 0;
    private double speedUp = 0;
    private double speedDown = 0;
    private int luckUp = 0;
    private int luckDown = 0;
    private int coinsUp = 0;

    protected ItemsSTATS() {
    }

    public void setItemName(String name) {
        this.name = name;
    }

    public String getItemName() {
        return name;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemPoolID(int itemPoolID) {
        this.itemPoolID = itemPoolID;
    }

    public int getItemPoolID() {
        return itemPoolID;
    }

    public void setItemHPup(int HPup) {
        this.HPup = HPup;
    }

    public int getItemHPup() {
        return HPup;
    }

    public void setItemHPdown(int HPdown) {
        this.HPdown = HPdown;
    }

    public int getItemHPdown() {
        return HPdown;
    }

    public void setItemDamageUp(double damageUp) {
        this.damageUp = damageUp;
    }

    public double getItemDamageUp() {
        return damageUp;
    }

    public void setItemDamageDown(double damageDown) {
        this.damageDown = damageDown;
    }

    public double getItemDamageDown() {
        return damageDown;
    }

    public void setItemSpeedUp(double speedUp) {
        this.speedUp = speedUp;
    }

    public double getItemSpeedUp() {
        return speedUp;
    }

    public void setItemSpeedDown(double speedDown) {
        this.speedDown = speedDown;
    }

    public double getItemSpeedDown() {
        return speedDown;
    }

    public void setItemLuckUp(int luckUp) {
        this.luckUp = luckUp;
    }

    public int getItemLuckUp() {
        return luckUp;
    }

    public void setItemLuckDown(int luckDown) {
        this.luckDown = luckDown;
    }

    public int getItemLuckDown() {
        return luckDown;
    }

    public void setItemCoinsUp(int coinsUp) {
        this.coinsUp = coinsUp;
    }

    public int getItemCoinsUp() {
        return coinsUp;
    }





}
