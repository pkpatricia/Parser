package com.example.patriciaparker.bookreviews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;


/**
 * Created by patriciaparker on 4/11/17.
 */


public class ListAdapter extends ArrayAdapter<Book> {

    private List<Book> items;

    public ListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public ListAdapter(Context context, int resource, List<Book> items) {
        super(context, resource, items);

        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {

            LayoutInflater vi;
            vi = LayoutInflater.from(this.getContext());
            v = vi.inflate(R.layout.itemlistrow, null);
        }

        Book p = this.getItem(position);

        if (p != null) {

            //TextView tt = (TextView) v.findViewById(R.id._id);
            ImageView image = (ImageView) v.findViewById(R.id.image);
            TextView tt1 = (TextView) v.findViewById(R.id.title);
            TextView tt3 = (TextView) v.findViewById(R.id.author);
            RatingBar rb = (RatingBar) v.findViewById(R.id.rating);
            //ImageView icon = (ImageView) v.findViewById(R.id.icon);

           /* if (tt != null) {

                tt.setText("" + p.getId());*/


                if (image != null) {
                    switch (p.getId()) {

                        case 1:
                            image.setImageResource(R.drawable.android_studio_development_essentials);
                            break;


                        case 2:
                            image.setImageResource(R.drawable.beginning_android_application_development);
                            break;


                        case 3:
                            image.setImageResource(R.drawable.hello_android);
                            break;


                        case 4:
                            image.setImageResource(R.drawable.programming_android);
                            break;


                        default:
                            break;

                    }

                }
                if (tt1 != null) {
                    tt1.setText(p.getTitle());
                }
                if (tt3 != null) {
                    tt3.setText(p.getAuthor());
                }
                if (rb != null) {
                    float rating = Float.parseFloat(p.getRating());
                    rb.setRating(rating);
                }
            }

            return v;
        }}
