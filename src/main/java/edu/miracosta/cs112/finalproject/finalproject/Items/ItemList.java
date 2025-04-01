package edu.miracosta.cs112.finalproject.finalproject.Items;

import java.util.ArrayList;
import java.util.List;
//Item ID in order starting from 1 used to identify items to be randomly selected later
//Item Pool ID (0-2) 0 being treasure room, 1 being shop room, 2 being boss room
//the rest are easy to understand lol
public class ItemList {
    private List<ItemCreation> allItems = new ArrayList<>();
    private ItemCreation[] treasureRoomItems;  // Array for treasure room items
    private ItemCreation[] shopRoomItems;
    private ItemCreation[] bossRoomItems;

    public ItemList() {

        //Item ID in order starting from 1 used to identify items to be randomly selected later
        //Item Pool ID (0-2) 0 being treasure room, 1 being shop room, 2 being boss room
        //the rest are easy to understand lol

        allItems.add(new ItemCreation("Coin Bag", 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7));
        allItems.add(new ItemCreation("Boxing Glove", 2, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0));
        allItems.add(new ItemCreation("Dental Floss", 3, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0));
        //Create a temporary list to hold treasure room items
        List<ItemCreation> tempTreasureRoomItems = new ArrayList<>();
        List<ItemCreation> tempShopRoomItems = new ArrayList<>();
        List<ItemCreation> tempBossRoomItems = new ArrayList<>();
        //Loop through all items and use an if statement to filter ItemPoolID equaling zero
        for (ItemCreation item : allItems) {
            if (item.getItemPoolID() == 0) {
                tempTreasureRoomItems.add(item);
            }
        }

        //Convert the temporary list to an array
        treasureRoomItems = new ItemCreation[tempTreasureRoomItems.size()];
        for (int i = 0; i < tempTreasureRoomItems.size(); i++) {
            treasureRoomItems[i] = tempTreasureRoomItems.get(i);
        }
    }

    // Getter for the treasure room items array
    public ItemCreation[] getTreasureRoomItems() {
        return treasureRoomItems;
    }

    //Getter for all items (if needed elsewhere)
    public List<ItemCreation> getAllItems() {
        return allItems;
    }
}