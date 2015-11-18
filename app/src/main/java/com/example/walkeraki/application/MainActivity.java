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
import java.util.List;

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

        try {
            FileInputStream input = openFileInput("lines.txt"); // Open input stream
            DataInputStream din = new DataInputStream(input);
            int sz = din.readInt(); // Read line count
            for (int i=0;i<sz;i++) { // Read lines
                String line = din.readUTF();
                list.add(line);

            }
            din.close();
        }
        catch (IOException exc) { exc.printStackTrace(); }

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
        Toast.makeText(getApplicationContext(), ""+list, Toast.LENGTH_LONG).show();
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);


        mRecyclerView.setHasFixedSize(true);


        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        ArrayList<MyPojo> pojos= new ArrayList<MyPojo>();
        for(int i=0;i<list.size();i++)
        {
            String item = list.get(i);
            pojos.add(new MyPojo(
            ""+item,
            "This is description text"
            ));

        }
        mAdapter = new MyAdapter(pojos);
        mRecyclerView.setAdapter(mAdapter);

        //network
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        //dobivanje podatkov iz massive arraya
        try {
            JSONObject weather = getJSON(this, "Ljubljana");

            String name = weather.getString("name");
            JSONObject details = weather.getJSONArray("weather").getJSONObject(0);
            JSONObject main = weather.getJSONObject("main");

            Log.e("test", details.getString("main") + "");
            Log.e("name",main.getString("humidity"));
            Log.e("name",name);
        }catch(Exception e){
            Log.e("SimpleWeather", "One or more fields not found in the JSON data");
        }




    }

    private static final String OPEN_WEATHER_MAP_API =
            "";
    private static String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
    private static String IMG_URL = "http://openweathermap.org/img/w/";


//dobivanje erraya
    public static JSONObject getJSON(Context context, String city){
        try {
           // URL url = new URL(String.format(OPEN_WEATHER_MAP_API, city));
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q="+city+",uk&appid=4ce55cc86a3095785f34052c7a698a4f");
            URLConnection connection = (URLConnection) url.openConnection();

            //connection.addRequestProperty("x-api-key", "4ce55cc86a3095785f34052c7a698a4f");

            InputStream is = new BufferedInputStream(connection.getInputStream());

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            StringBuffer json = new StringBuffer(1024);
            String tmp = "";
            while ((tmp = reader.readLine()) != null){
                json.append(tmp).append("\n");
                Log.e("Some website", tmp);
            }
            reader.close();

            JSONObject data = new JSONObject(json.toString());

            // This value will be 404 if the request was not
            // successful
            if(data.getInt("cod") != 200){
                return null;
            }

            return data;
        }catch(Exception e){
            Log.e("error", "ssdsd" + e.getStackTrace() );
            return null;
        }
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, AddingCities.class);

        startActivity(intent);
    }

}
