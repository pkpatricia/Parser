package com.example.mypackage.quotes;

import java.util.ArrayList;

/**
 * Created by patriciaparker on 9/6/17.
 */

public class DataSource {

    private ArrayList<Integer> mPhotoPool;
    private ArrayList<Integer> mQuotePool;
    private ArrayList<Integer> mPhotoHdPool;

    public ArrayList<Integer> getmPhotoHdPool() {
        return mPhotoHdPool;
    }

    public ArrayList<Integer> getmPhotoPool() {
        return mPhotoPool;
    }

    public ArrayList<Integer> getmQuotePool() {
        return mQuotePool;
    }

    private void setupPhotoPool() {
        mPhotoPool.add(R.drawable.message1);
        mPhotoPool.add(R.drawable.message2);
        mPhotoPool.add(R.drawable.message3);
        mPhotoPool.add(R.drawable.message4);
        mPhotoPool.add(R.drawable.message5);
        mPhotoPool.add(R.drawable.message6);
        mPhotoPool.add(R.drawable.message7);
        mPhotoPool.add(R.drawable.message8);
        mPhotoPool.add(R.drawable.message9);
        mPhotoPool.add(R.drawable.message10);
    }
    private void setupQuotePool() {
        mQuotePool.add(R.string.quote_1);
        mQuotePool.add(R.string.quote_2);
        mQuotePool.add(R.string.quote_3);
        mQuotePool.add(R.string.quote_4);
        mQuotePool.add(R.string.quote_5);
        mQuotePool.add(R.string.quote_6);
        mQuotePool.add(R.string.quote_7);
        mQuotePool.add(R.string.quote_8);
        mQuotePool.add(R.string.quote_9);
        mQuotePool.add(R.string.quote_10);

    }
    private void setupPhotoHDPool() {
        mPhotoHdPool.add(R.drawable.message1);
        mPhotoHdPool.add(R.drawable.message2);
        mPhotoHdPool.add(R.drawable.message3);
        mPhotoHdPool.add(R.drawable.message4);
        mPhotoHdPool.add(R.drawable.message5);
        mPhotoHdPool.add(R.drawable.message6);
        mPhotoHdPool.add(R.drawable.message7);
        mPhotoHdPool.add(R.drawable.message8);
        mPhotoHdPool.add(R.drawable.message9);
        mPhotoHdPool.add(R.drawable.message10);
    }

    public int getDataSourceLength() {
        return mPhotoPool.size();
    }


    //Constructor
    public DataSource() {

        mPhotoPool = new ArrayList();
        mQuotePool = new ArrayList();
        mPhotoHdPool = new ArrayList();

        setupPhotoPool();
        setupQuotePool();
        setupPhotoHDPool();

    }
}
