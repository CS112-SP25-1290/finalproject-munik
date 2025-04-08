package edu.miracosta.cs112.finalproject.finalproject.Entities;


//creating a list of playable characters
//In order to create new characters (name, description, HP, DMG, Fire Rate, Luck, Coins, Bombs, Keys)
public class CharacterList {
    private static final CharacterList instance = new CharacterList();

    public static CharacterList getInstance() {
        return instance;
    }

    public static class PlayableCharacter {
        private String name;
        private String description;

        private int hp, speed, dmg, fireRate, luck, coins, bombs, keys;

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
    }

    private PlayableCharacter isaac = new PlayableCharacter("Isaac", "Average stats across the board", 3, 8, 3, 3, 0, 3, 1, 0);
    private PlayableCharacter character2 = new PlayableCharacter("Maggie", "Beefier but weaker", 4, 6, 2, 2, 1, 2, 0, 1);
    private PlayableCharacter character3 = new PlayableCharacter("Garbo","The ultimate spy", 3, 10, 2, 3, 6, 0, 0, 3);
    private PlayableCharacter character4 = new PlayableCharacter("Erwin", "Commander", 3, 6, 5, 2, 2, 2, 1, 0);
    public PlayableCharacter getIsaac() { return isaac; }
    public PlayableCharacter getCharacter2() { return character2; }

    public PlayableCharacter getCurrentCharacter() {
        return currentCharacter;
    }

    public void setCurrentCharacter(PlayableCharacter currentCharacter) {
        this.currentCharacter = currentCharacter;
    }

    private PlayableCharacter currentCharacter = isaac;


}