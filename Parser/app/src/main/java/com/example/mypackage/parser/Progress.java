package com.example.mypackage.parser;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by patriciaparker on 9/18/17.
 */

public class Progress extends Activity implements View.OnClickListener {

    Button btnProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnProgress = (Button) findViewById(R.id.btnProgress);
        btnProgress.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait data is Processing");
        progressDialog.show();

//        After 2 Seconds i dismiss progress Dialog

        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(5000);
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}

