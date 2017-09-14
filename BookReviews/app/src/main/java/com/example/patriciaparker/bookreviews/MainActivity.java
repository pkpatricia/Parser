package com.example.patriciaparker.bookreviews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener

{

    //SqlHelper db = null;
    //db = new SqlHelper(this);
    SqlHelper db = new SqlHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.spinner);




        // Spinner element
        Spinner spinner;

        // Spinner element
        spinner = (Spinner) findViewById(R.id.spinner);


        /*  CRUD Operations **/
        // add Books
       db.addBook(new Book("Android Studio Development Essentials", "Neil Smyth"));
       db.addBook(new Book("Beginning Android Application Development", "Wei-Meng Lee"));
       db.addBook(new Book("Programming Android", "Wallace Jackson"));
       db.addBook(new Book("Hello, Android", "Ben Jackson"));
        //String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar
                //.getInstance().getTime());
       // System.out.println("\nCurrent Date and Time is: " + timeStamp + "\nProgrammed by: " +
                //"Patricia Kay Parker\n");
        /*list = db.getAllBooks();
        ListView listContent = (ListView) findViewById(R.id.list);
        list = new ArrayList<Book>();
        list = db.getAllBooks();*/


        // get all books
        db.getAllBooks();

        //get all books
        List<Book> list = db.getAllBooks();

        ListView listContent = (ListView) findViewById(R.id.list);
        list = new ArrayList<Book>();
        list= db.getAllBooks();

        ////get data from the table by the ListAdapter
        ListAdapter customAdapter = new ListAdapter(this, R.layout.itemlistrow,list);
        listContent.setAdapter(customAdapter);

        // update one book   ***************** Original Run ******************
        //int j = db.updateBook(list.get(0), book_n, book_a);

        // update last book in list
        //int j = db.updateBook(list.get(3), book_n, book_a);

        // delete one book
        //db.deleteBook(list.get(0));



        // get Count of All Books
        //int x = db.getIds();

        //Create spinner item listing
        List<String> blist = new ArrayList<String>();
        blist.add(0,"Select Analytics...");
        blist.add(1,"Get Highest Rated Title(s)");
        blist.add(2,"Get Lowest Rated Title(s)");
        blist.add(3,"Get Total Number of Books");
        blist.add(4,"Retrieve Title(s) with word Android");
        blist.add(5,"Get List of Books with ID greater than 1");


        //Sort list in Alphabetical order
        /*Collections.sort(blist, new Comparator<String>() {
            @Override
            public int compare(String lhs, String rhs) {
                return lhs.compareTo(rhs);
            }
        });*/


        ArrayAdapter<String> adapter = new
                ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, blist);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setWillNotDraw(false);
        spinner.setOnItemSelectedListener(this);

    }  //end onCreate() method

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position,
                               long arg3) {

        switch (position) {


            case 1:

                Toast.makeText(this, "Title :: " + db.getRatingMax(),
                        Toast.LENGTH_LONG).show();
                break;

            case 2:

                Toast.makeText(this, "Title :: " + db.getRatingMin(),
                        Toast.LENGTH_LONG).show();
                break;

            case 3:
                Toast.makeText(this, "Number of Books :: " + db.getIDs(),
                        Toast.LENGTH_LONG).show();
                break;

            case 4:
                Toast.makeText(this, "Title :: " + db.getBooks(),
                        Toast.LENGTH_LONG).show();
                break;

            case 5:
                Toast.makeText(this, "Title ::  + {db.getIdsGreaterThanOne()}",
                        Toast.LENGTH_LONG).show();
                break;

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0){

    }



}