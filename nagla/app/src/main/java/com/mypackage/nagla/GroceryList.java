package com.shermansthill.nagla;

/**
 * Created by ssthill on 4/18/2017.
 */

public class GroceryList {
    private int id;
    private String groceryListName;

    public GroceryList() {
    }

    public GroceryList(int id, String groceryListName) {
        this.id = id;
        this.groceryListName = groceryListName;
    }

    public GroceryList(String groceryListName) {
        this.groceryListName = groceryListName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroceryListName() {
        return groceryListName;
    }

    public void setGroceryListName(String groceryListName) {
        this.groceryListName = groceryListName;
    }

    @Override
    public String toString() {
        return "GroceryList{" +
                "id=" + id +
                ", groceryListName='" + groceryListName + '\'' +
                '}';
    }
}
