package com.example.mypackage.quotes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mypackage.quotes.R.id;
import com.example.mypackage.quotes.R.layout;


/**
 * Created by patriciaparker on 9/6/17.
 */

public class Splash extends Activity {

        // Splash screen timer
        private final int SPLASH_TIME_OUT = 5000;

    private ImageView mImageView;
    private TextView textView;
    private int mPosition;
    private DataSource mDataSource;

        @Override
        public void onCreate(Bundle savedInstanceState)

        {
            super.onCreate(savedInstanceState);

            this.setContentView(layout.splash);


            Intent i = getIntent();
            this.mPosition = i.getIntExtra("position", 0);

            this.mDataSource = new DataSource();
            this.textView = (TextView) findViewById(id.text);

            new Handler().postDelayed(new Runnable(){

                @Override
            public void run() {
                    Intent mainIntent = new Intent(Splash.this,QuoteReaderActivity.class);
                    startActivity(mainIntent);
                    finish();
        }

        } , this.SPLASH_TIME_OUT);

        }

    }


