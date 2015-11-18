package com.example.walkeraki.application;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.widget.Toast;
import android.content.Intent;
import android.graphics.Color;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by walkeraki on 17.11.2015.
 */
public class AddingCities extends Activity {
    public final static String EXTRA_MESSAGE = "IT WORKS";

    private EditText input, digits, pass, phone;
    private Button next, display;
    private Context context;
    ArrayList<String> list = new ArrayList<String>();



    public void sendMessage(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        list.add(message);
        SharedPreferences sharedPref = getSharedPreferences("keys",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        Set<String> set = new HashSet<String>();
        set=sharedPref.getStringSet("keys",new HashSet<String>());
        set.add(message);
        editor.putStringSet("keys", set);
        editor.apply();



        /*SharedPreferences sharedPref = getSharedPreferences("tocke",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("tocke", value);
        editor.commit();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("value_key", i);
        Toast.makeText(getApplicationContext(), ""+highScore, Toast.LENGTH_LONG).show();
        editor.commit();
        value++;
        PreferenceManager.getDefaultSharedPreferences(this).edit().putInt("value", value).commit();


        /*SharedPreferences sp = getSharedPreferences("st", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("st", i);
        editor.commit();

        SharedPreferences prefs = getSharedPreferences("st", MODE_PRIVATE);
        Integer i = prefs.getInt("st", 0);

        i++;
        Toast.makeText(getApplicationContext(), ""+i, Toast.LENGTH_LONG).show();
        */


        //intent.putStringArrayListExtra("array", list);
        //intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adding_cities);
        input = (EditText) findViewById(R.id.editText);
        next = (Button) findViewById(R.id.button);
        //Toast.makeText(getApplicationContext(), ""+value, Toast.LENGTH_LONG).show();
        next.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(v);
            }
        });
        /*SharedPreferences prefs = getSharedPreferences("mesto", MODE_PRIVATE);
        String[] tocke = prefs.getString("mesto", "");
        Log.e("tocke", tocke + "");
        */
    }



}
