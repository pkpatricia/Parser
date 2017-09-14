package com.example.patriciaparker.bookreviews;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by patriciaparker on 4/6/17.
 */

public class SqlHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 32;
    // Database Name
    private static final String DATABASE_NAME = "BookDB";

    // Books table name
    private static final String TABLE_BOOKS = "books";

    // Books Table Columns names
    public static String KEY_ID = "id";
    public static String KEY_TITLE = "title";
    public static String KEY_AUTHOR = "author";
    public static String KEY_IMAGE = "image";
    public static String KEY_RATING = "rating";


    public SqlHelper(Context context) {
        super(context, SqlHelper.DATABASE_NAME, null, SqlHelper.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table
        String CREATE_BOOK_TABLE = "CREATE TABLE books ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT, "+
                "author TEXT )";

        // create books table
        db.execSQL(CREATE_BOOK_TABLE);

        Log.d("onCreate","exiting onCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS books");

        // create fresh books table
        this.onCreate(db);


        String upgradeQuery = "ALTER TABLE books ADD COLUMN rating TEXT";
        if (oldVersion == 31 && newVersion == 32)
            db.execSQL(upgradeQuery);


        Log.d("onUpgrade", "exiting onUpgrade");
    }


     /*CRUD operations (create "add", read "get", update, delete) */

    public void addBook(Book book){
        Log.d("addBook", book.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(SqlHelper.KEY_TITLE, book.getTitle()); // get title
        values.put(SqlHelper.KEY_AUTHOR, book.getAuthor()); // get author
        //values.put(SqlHelper.KEY_IMAGE, book.image);
        //values.put(SqlHelper.KEY_RATING, book.rating);


        // 3. insert
        db.insert(SqlHelper.TABLE_BOOKS, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/values

        // 4. Close dbase
        db.close();
    }


    // Get All Books
    public List<Book> getAllBooks() {
        List<Book> books = new LinkedList<Book>();

        // 1. build the query
        String query = "SELECT  * FROM " + SqlHelper.TABLE_BOOKS;

        // 2. get reference to writable DB
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
        Book book = null;
        if (cursor.moveToFirst()) {
            do {
                book = new Book();
                book.setId(Integer.parseInt(cursor.getString(0)));
                book.setTitle(cursor.getString(1));
                book.setAuthor(cursor.getString(2));
                //book.image = cursor.getString(3);
                book.setRating(cursor.getString(3));

                // Add book to books
                books.add(book);
            } while (cursor.moveToNext());
        }

        Log.d("getAllBooks()", books.toString());

        return books; // return books
    }

    // Updating single book
    //public int updateBook(Book book) **********  Original Run **********************
    //
    public int updateBook(Book book, String newTitle, String newAuthor) {


        // 1. get reference to writable DB
        SQLiteDatabase db = getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("title", newTitle); // get title
        values.put("author", newAuthor); // get author

        // 3. updating row
        int i = db.update(SqlHelper.TABLE_BOOKS, //table
                values, // column/value
                SqlHelper.KEY_ID +" = ?", // selections
                new String[] { String.valueOf(book.getId()) }); //selection args

        // 4. close dbase
        db.close();
        Log.d("UpdateBook", book.toString());
        return i;
    }


    // Deleting single book
    public void deleteBook(Book book) {

        // 1. get reference to writable DB
        SQLiteDatabase db = getWritableDatabase();

        // 2. delete
        db.delete(SqlHelper.TABLE_BOOKS,
                SqlHelper.KEY_ID +" = ?",
                new String[] { String.valueOf(book.getId()) });

        // 3. close
        db.close();

        Log.d("deleteBook", book.toString());
    }

    public String getIDs()
    {
        String selectQuery = "SELECT id FROM books";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        c.moveToFirst();
        int total = c.getCount();
        String t = Integer.toString(total);
        Log.d("getID()", Integer.toString(total));
        db.close();
        return t;

    }



    public String getRatingMax() {
        StringBuilder s = new StringBuilder();
        String selectQuery = "SELECT title FROM books WHERE rating IN (SELECT MAX(rating) FROM " +
                "books)";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()) {
            do {
                s.append(cursor.getString(cursor.getColumnIndex("title")));  //get author's value
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return s.toString();
    }


    public String getRatingMin() {
        StringBuilder s = new StringBuilder();
        String selectQuery = "SELECT title FROM books WHERE rating IN (SELECT MIN(rating) FROM " +
                "books)";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()) {
            do {
                s.append(cursor.getString(cursor.getColumnIndex("title"))); //get author's value
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return s.toString();
    }

    // Gives the count of total records in database
    /*public String getTotal()
    {
        StringBuilder s = new StringBuilder();
        String selectQuery = "SELECT COUNT(id) FROM books";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        cursor.close();
        db.close();
        return s.toString();
    }*/

    public String getBooks() {
        StringBuilder s = new StringBuilder();
        String selectQuery = "SELECT title from books WHERE title " +
                "LIKE '%Android%' ";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()) {
            do {
                s.append(cursor.getString(cursor.getColumnIndex("title")));  //get author's value
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return s.toString();
    }

    public String getIdsGreaterThanOne()
    {
        StringBuilder s = new StringBuilder();
        String selectQuery = "SELECT title from books " +
                "WHERE (id > 1" +
                ")";


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()) {
            do {
                s.append(cursor.getString(cursor.getColumnIndex("title")));  //get author's value
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return s.toString();
    }





}

