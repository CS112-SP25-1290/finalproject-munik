package edu.miracosta.cs112.finalproject.finalproject.Items;

public class ItemCreation extends ItemsSTATS {
    ItemCreation(String name, int itemID, int itemPoolId, int HPup, int HPdown, int damageUp, int damageDown, int speedUp, int speedDown, int luckUp, int luckDown, int coinsUp) {
        super();
        this.setItemName(name);
        this.setItemID(itemID);
        this.setItemPoolID(itemPoolId);
        this.setItemHPup(HPup);
        this.setItemHPdown(HPdown);
        this.setItemDamageUp(damageUp);
        this.setItemDamageDown(damageDown);
        this.setItemSpeedUp(speedUp);
        this.setItemSpeedDown(speedDown);
        this.setItemLuckUp(luckUp);
        this.setItemLuckDown(luckDown);
        this.setItemCoinsUp(coinsUp);
    }
}