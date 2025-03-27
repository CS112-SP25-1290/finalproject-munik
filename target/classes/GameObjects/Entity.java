public abstract class Entity {
    private String name = null;
    private String desc = null;
    private int HP = 0;
    private int damage = 0;
    private int fireRate = 0;
    private int luck = 0;
    private int bombs = 0;
    private int keys = 0;

    protected Entity() {
    }

    public void setName(){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setDesc(){
        desc = name;
    }

    public String getDesc(){
        return desc;
    }

    public void setHP(int HP){
        this.HP = HP;
    }

    public int getHP(){
        return this.HP;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDamage(){
        return this.damage;
    }

    public void setFireRate(int fireRate) {
        this.fireRate = fireRate;
    }

    public int getFireRate(){
        return this.fireRate;
    }

    public int setLuck(int luck) {
        this.luck = luck;
    }

    public int getLuck(){
        return this.luck;
    }

    public void setBombs(int bombs){
        this.bombs = bombs;
    }

    public int getBombs(){
        return this.bombs;
    }

    public void setKeys(int keys) {
        this.keys = keys;
    }

    public int getKeys(){
        return this.keys;
    }


}
