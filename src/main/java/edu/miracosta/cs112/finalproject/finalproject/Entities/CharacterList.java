package edu.miracosta.cs112.finalproject.finalproject.Entities;


//creating a list of playable characters
//In order to create new characters (name, description, HP, DMG, Fire Rate, Luck, Coins, Bombs, Keys)
public class CharacterList {
    public static class Playable1 {
        private String name;
        private String description;
        private int hp, dmg, fireRate, luck, coins, bombs, keys;

        public Playable1(String name, String description, int hp, int dmg, int fireRate, int luck, int coins, int bombs, int keys) {
            this.name = name;
            this.description = description;
            this.hp = hp;
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
    }

    private Playable1 isaac = new Playable1("Isaac", "Average stats across the board", 3, 3, 3, 0, 3, 1, 0);
    private Playable1 character2 = new Playable1("Maggie", "Beefier but weaker", 4, 2, 2, 1, 2, 0, 1);

    public Playable1 getIsaac() { return isaac; }
    public Playable1 getCharacter2() { return character2; }
}
