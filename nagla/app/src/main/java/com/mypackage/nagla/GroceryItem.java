package com.shermansthill.nagla;

/**
 * Created by ssthill on 5/2/2017.
 */

public class GroceryItem {
    private int id;
    private String groceryItemName;

    public GroceryItem(String groceryItemName) {
        this.groceryItemName = groceryItemName;
    }

    public GroceryItem(int id, String groceryItemName) {
        this.id = id;
        this.groceryItemName = groceryItemName;
    }

    public GroceryItem() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroceryItemName() {
        return groceryItemName;
    }

    public void setGroceryItemName(String groceryItemName) {
        this.groceryItemName = groceryItemName;
    }

    @Override
    public String toString() {
        return "GroceryItem{" +
                "id=" + id +
                ", groceryItemName='" + groceryItemName + '\'' +
                '}';
    }
}
