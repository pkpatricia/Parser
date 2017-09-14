package com.shermansthill.nagla;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class AddGroceryListItemsActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText addItemEditText;
    ImageButton addItemImageButton;
    long groceryListId;
    long groceryItemId;
    ListView groceryListItemView;
    List<GroceryItem> groceryItemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_grocery_list_items);

        db = new DatabaseHelper(this);

        this.setTitle("Add Items");

        groceryListItemView = (ListView) findViewById(R.id.addGroceryListItemListView);
        addItemEditText = (EditText) findViewById(R.id.addItemEditText);
        addItemImageButton = (ImageButton) findViewById(R.id.addItemImageButton);

        // Check if GROCERYLIST_TABLE is empty
        if (db.checkIfEmpty("groceryListItems")) {
            groceryListItemView.setVisibility(View.VISIBLE);
        } else {
            groceryListItemView.setVisibility(View.INVISIBLE);
        }

        addItemImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = addItemEditText.getText().toString();
//
//                groceryItemId = db.createGroceryItem(new GroceryItem(addItemEditText.getText().toString()));
//                db.createGroceryListItem(groceryListId, groceryItemId);
                if (!item.isEmpty()) {
                    groceryItemsList.add(new GroceryItem(item));
                    groceryListItemView.setAdapter(new GroceryItemAdapter(AddGroceryListItemsActivity.this));
                }
            }
        });
    }

    /**
     * GroceryList Adapter
     */
    public class GroceryItemAdapter extends BaseAdapter {
        private Context context;
        private LayoutInflater inflater;

        public GroceryItemAdapter(Context context) {
            this.context = context;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return groceryItemsList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv;

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.grocerylistitemrow, parent, false);
            }

            tv = (TextView) convertView.findViewById(R.id.glText);
            tv.setText(groceryItemsList.get(position).getGroceryItemName());

            return convertView;
        }
    }
}
