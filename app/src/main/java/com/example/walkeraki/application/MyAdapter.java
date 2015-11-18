package com.example.walkeraki.application;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by walkeraki on 17.11.2015.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<String> pojos;
    private static final String TAG = "MyActivity";
    private TextView displayTextView;
    private Context ime;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View View;
        public ViewHolder(View v) {
            super(v);
            View = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<String>pojos,Context ime) {
        this.pojos = pojos;
        this.ime = ime;

    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        TextView title= (TextView) holder.View.findViewById(R.id.title);
        TextView description= (TextView) holder.View.findViewById(R.id.description);

        title.setText(pojos.get(position));


        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ime, CityInfo.class);
                intent.putExtra("keys",pojos.get(position));
                Log.v(TAG, "index=" + pojos.get(position));
                ime.startActivity(intent);
            }


        });
    }


                // Return the size of your dataset (invoked by the layout manager)
        @Override
    public int getItemCount() {
        return pojos.size();
    }
}