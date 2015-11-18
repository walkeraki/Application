package com.example.walkeraki.application;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Button next;
    ArrayList<String> list = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Intent intent = getIntent();
        //list = intent.getStringArrayListExtra("array");

       /* SharedPreferences sharedPref = getSharedPreferences("tocke",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("tocke", 1000);
        editor.commit();
        */

        next = (Button) findViewById(R.id.button2);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(v);
            }

        });
        // Intent intent = getIntent();
        //String message = intent.getStringExtra(AddingCities.EXTRA_MESSAGE);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);


        mRecyclerView.setHasFixedSize(true);


        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        SharedPreferences sharedPref = getSharedPreferences("keys", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        Set<String> set = new HashSet<String>();
        set = sharedPref.getStringSet("keys", new HashSet<String>());
        ArrayList<String> pojos = new ArrayList<>();


        for (String str : set)
            pojos.add(str);

        mAdapter = new MyAdapter(pojos, this);

        mRecyclerView.setAdapter(mAdapter);

        //network
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, AddingCities.class);
        startActivity(intent);
    }

}
