package com.shermansthill.nagla;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ssthill on 4/19/2017.
 * DatabaseHelper to hold SQLite commands from CRUD functions
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "GroceryListDB";

    // GroceryListDB Table Name
    private static final String TABLE_GROCERYLISTS = "groceryLists";
    private static final String TABLE_GROCERYITEMS = "groceryItemsList";
    private static final String TABLE_GROCERYLISTITEMS = "groceryListItems";

    // Common column names
    private static final String KEY_ID = "id";

    // GroceryLists Table Column Names
    private static final String KEY_GROCERYLISTNAME = "grocery_list_name";

    // GroceryItems Table Column Names
    private static final String KEY_GROCERYITEMNAME = "grocery_item_name";

    // GroceryItems Table Column Names
    private static final String KEY_GROCERYITEM_ID = "grocery_item_id";
    private static final String KEY_GROCERYLIST_ID = "grocery_list_id";

    // Table Create Statements--------------------
    // GroceryLists Table Create Statement
    String CREATE_TABLE_GROCERY_LIST = "CREATE TABLE "
            + TABLE_GROCERYLISTS + "(" + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_GROCERYLISTNAME
            + " TEXT" + ")";

    // GroceryItems Table Create Statement
    String CREATE_TABLE_GROCERY_ITEM = "CREATE TABLE "
            + TABLE_GROCERYITEMS + "(" + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_GROCERYITEMNAME
            + " TEXT" + ")";

    // GroceryListItems Table Create Statement
    String CREATE_TABLE_GROCERY_LIST_ITEM = "CREATE TABLE "
            + TABLE_GROCERYLISTITEMS + "(" + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_GROCERYLIST_ID
            + " INTEGER, " + KEY_GROCERYITEM_ID + " INTEGER" + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creating required tables
        db.execSQL(CREATE_TABLE_GROCERY_LIST);
        db.execSQL(CREATE_TABLE_GROCERY_ITEM);
        db.execSQL(CREATE_TABLE_GROCERY_LIST_ITEM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // On Upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GROCERYLISTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GROCERYITEMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GROCERYLISTITEMS);

        this.onCreate(db);
    }

    //-------------COMMON TABLE METHODS-------------//

    /**
     * Check if Table is empty
     */
    public Boolean checkIfEmpty(String tableName) {
        Boolean result = true;

        // 1. Build the query
        String count = "SELECT * FROM " + tableName;

        // 2. Get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(count, null);

        // 3. Move the cursor to the first row. This method will return false if the cursor is empty.
        result = cursor.moveToFirst();

        // 4. Close the cursor to free it up
        cursor.close();

        return result;
    }

    //-------------GROCERY_LISTS TABLE METHODS-------------//

    /**
     * Creating a grocery list
     */
    public long createGroceryList(GroceryList groceryList) {
        // 1. Get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. Create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_GROCERYLISTNAME, groceryList.getGroceryListName());  // Get groceryListName

        // 3. Insert
        long id = db.insert(TABLE_GROCERYLISTS, null, values);

        // 4. Close database
        db.close();

        return id;
    }

    /**
     * Get All Grocery Lists
     *
     * @return groceryLists
     */
    public List<GroceryList> getAllGroceryLists() {
        List<GroceryList> groceryLists = new LinkedList<GroceryList>();

        // 1. Build the query
        String query = "SELECT * FROM " + TABLE_GROCERYLISTS;

        // 2. Get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. Go over each row, build groceryList and add it to list
        GroceryList groceryList = null;
        if (cursor.moveToFirst()) {
            do {
                groceryList = new GroceryList();
                groceryList.setId(Integer.parseInt(cursor.getString(0)));
                groceryList.setGroceryListName(cursor.getString(1));

                groceryLists.add(groceryList);
            } while (cursor.moveToNext());
        }

        // 4. Close the cursor to free it up
        cursor.close();

        Log.d("getAllGroceryLists", groceryList.toString());

        return groceryLists;
    }

    /**
     * Delete a grocery list by id
     *
     * @param groceryList
     */
    public void deleteGroceryList(GroceryList groceryList) {

        // 1. Get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. Delete grocery list
        db.delete(TABLE_GROCERYLISTS, KEY_ID + " = ?", new String[]{String.valueOf(groceryList.getId())});

        // 3. Close database
        db.close();
    }

    //-------------GROCERY_ITEMS TABLE METHODS-------------//

    /**
     * Creating a grocery item
     */
    public long createGroceryItem(GroceryItem groceryItem) {
        // 1. Get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. Create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_GROCERYITEMNAME, groceryItem.getGroceryItemName());  // Get groceryItemName

        // 3. Insert
        long id = db.insert(TABLE_GROCERYITEMS, null, values);

        // 4. Close database
        db.close();

        return id;
    }

    //-------------GROCERY_LIST_ITEMS TABLE METHODS-------------//

    /**
     * Add item to a grocery list (creates a link between an item and a grocery list)
     */
    public long createGroceryListItem(long groceryList_id, long groceryItem_id) {
        // 1. Get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. Create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_GROCERYLIST_ID, groceryList_id);  // Get groceryList_id
        values.put(KEY_GROCERYITEM_ID, groceryItem_id);  // Get groceryItem_id

        // 3. Insert
        long id = db.insert(TABLE_GROCERYLISTITEMS, null, values);

        // 4. Close database
        db.close();

        return id;
    }

    /**
     * Get groceries items for list by id
     */
}
