package com.shermansthill.nagla;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class GroceryListActivity extends AppCompatActivity {

    private int groceryListID;
    private String groceryListName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_list);

        Intent i = getIntent();
        groceryListID = i.getIntExtra("groceryListID", 0);
        groceryListName = i.getStringExtra("groceryListName");

        // Sets the title of the activity
        this.setTitle(groceryListName);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GroceryListActivity.this, AddGroceryListItemsActivity.class);
                startActivity(i);
            }
        });
    }
}
