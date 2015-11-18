package com.example.walkeraki.application;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.content.Context;



import android.view.View;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.widget.Toast;



import android.content.Intent;

import android.graphics.Color;

/**
 * Created by walkeraki on 17.11.2015.
 */
public class AddingCities extends Activity {
    public final static String EXTRA_MESSAGE = "IT WORKS";

    private EditText input, digits, pass, phone;
    private Button next, display;
    private Context context;


    public void sendMessage(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adding_cities);
        input = (EditText) findViewById(R.id.editText);
        next = (Button) findViewById(R.id.button);
        next.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(v);
            }

        });




    }



}
