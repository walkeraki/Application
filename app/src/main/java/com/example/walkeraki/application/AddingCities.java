package com.example.walkeraki.application;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.Intent;
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
        //shranjevanje array in igranje z podatkovnimi tipi
        SharedPreferences sharedPref = getSharedPreferences("keys", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        Set<String> set = new HashSet<String>();
        Set<String> set3 = new HashSet<String>();

        set=sharedPref.getStringSet("keys",new HashSet<String>());
        Object[] set2 = set.toArray ();
        for(int m=0;m<set.size();m++)
        {
            set3.add((String) set2[m]);

        }
        set3.add(message);
        editor.putStringSet("keys", set3);
        editor.apply();
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

    }



}
