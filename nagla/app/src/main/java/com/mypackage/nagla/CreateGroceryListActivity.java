package com.shermansthill.nagla;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateGroceryListActivity extends AppCompatActivity implements View.OnClickListener {

    EditText groceryListNameEditText;
    DatabaseHelper db;
    Button createButton;
    Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_grocery_list);

        // Sets the title of the activity
        this.setTitle("Create new list...");

        createButton = (Button) findViewById(R.id.createButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);

        createButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);

        groceryListNameEditText = (EditText) findViewById(R.id.groceryListNameEditText);

        db = new DatabaseHelper(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.createButton:
                final String groceryListName = groceryListNameEditText.getText().toString();

                if (!isValidGroceryListName(groceryListName)) {
                    groceryListNameEditText.setError("Grocery list name cannot be blank!");
                } else {
                    db.createGroceryList(new GroceryList(groceryListNameEditText.getText().toString()));
                    Toast.makeText(this, groceryListNameEditText.getText().toString() + " created successfully!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(CreateGroceryListActivity.this, HomeScreenActivity.class);
                    startActivity(i);
                }
                break;
            case R.id.cancelButton:
                finish();
                break;
            default:
                finish();
                break;
        }
    }

    // Validating grocery list name for blank entry
    private boolean isValidGroceryListName(String listName) {
        Log.d("isValidGroceryListName",listName);
        if (listName == null || listName.isEmpty()) {
            // (editTextBox1.getText().length() != 0)
            return false;
        }
        return true;
    }
}
