package edu.miracosta.cs112.finalproject.finalproject;

public class Playable1 extends Entity {

    Playable1(String name, String desc, int HP, int damage, int fireRate, int luck, int coins, int bombs, int keys) {
        this.setName(name);
        this.setDesc(desc);
        this.setHP(HP);
        this.setDamage(damage);
        this.setFireRate(fireRate);
        this.setLuck(luck);
        this.setCoins(coins);
        this.setKeys(keys);
        this.setBombs(bombs);
    }


}
