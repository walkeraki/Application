package com.example.walkeraki.application;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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


        //shranjevanje

        /*SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("tocke", 1000);
        editor.commit();*/

        //klicanje shranjevanja
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        long highScore = sharedPref.getInt("tocke", 500);

        Log.e("tocke", highScore + "");
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
}
