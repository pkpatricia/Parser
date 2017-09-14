package com.shermansthill.nagla;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HomeScreenActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DatabaseHelper db;
    List<GroceryList> groceryListObjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Sets the title of the activity
        this.setTitle("com.example.mypackage.nagla");

        db = new DatabaseHelper(this);

        final ListView groceryListView = (ListView) findViewById(R.id.groceryListView);
        ImageView glListOptions = (ImageView) findViewById(R.id.glListOptions);
        ImageView glIcon = (ImageView) findViewById(R.id.glIconImageView);
        TextView glPromptTextView = (TextView) findViewById(R.id.glPromptTextView);

        groceryListObjects = new ArrayList<>();

        // Check if GROCERYLIST_TABLE is empty
        if (db.checkIfEmpty("groceryLists")) {
            groceryListView.setVisibility(View.VISIBLE);
            glIcon.setVisibility(View.INVISIBLE);
            glPromptTextView.setVisibility(View.INVISIBLE);

            groceryListObjects = db.getAllGroceryLists();
            groceryListView.setAdapter(new GroceryListAdapter(this));
        } else {
            groceryListView.setVisibility(View.INVISIBLE);
            glIcon.setVisibility(View.VISIBLE);
            glPromptTextView.setVisibility(View.VISIBLE);
        }

        // onItemClickListener for Grocery List items
        groceryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Intent i = new Intent(HomeScreenActivity.this, GroceryListActivity.class);
                i.putExtra("groceryListID", groceryListObjects.get(position).getId());
                i.putExtra("groceryListName", groceryListObjects.get(position).getGroceryListName());
                startActivity(i);
            }
        });

        // Register the GroceryListView for Context menu
        registerForContextMenu(groceryListView);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeScreenActivity.this, CreateGroceryListActivity.class);
                startActivity(i);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent i = new Intent(this, HomeScreenActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_coupons) {

        } else if (id == R.id.nav_nearby_stores) {
            Intent i = new Intent(this, StoreMapsActivity.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Creates and displays floating context menu when list item is long pressed
     *
     * @param menu      Contextmenu object parameter
     * @param v         View object parameter
     * @param menuInfo  ContextMenuInfo object parameter
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.grocerylist_options_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Integer id = (int) (long) info.id;

        switch (item.getItemId()) {
            case R.id.find_nearest_store:
                break;
            case R.id.copy_list_items:
                break;
            case R.id.share_list:
                break;
            case R.id.glSettings:
                break;
            case R.id.delete_list:
                Log.d("Delete List Selected: ", String.valueOf(info.id));
                db.deleteGroceryList(groceryListObjects.get(id));

                Intent i = new Intent(this, HomeScreenActivity.class);
                startActivity(i);

                Toast.makeText(getApplicationContext(), groceryListObjects.get(id).getGroceryListName() + " deleted!", Toast.LENGTH_SHORT).show();
                break;
            default:
                return super.onContextItemSelected(item);
        }
        return super.onContextItemSelected(item);
    }

    /**
     * GroceryList Adapter
     */
    public class GroceryListAdapter extends BaseAdapter {
        private Context context;
        private LayoutInflater inflater;

        public GroceryListAdapter(Context context) {
            this.context = context;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return groceryListObjects.size();
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
            tv.setText(groceryListObjects.get(position).getGroceryListName());

            return convertView;
        }
    }
}
