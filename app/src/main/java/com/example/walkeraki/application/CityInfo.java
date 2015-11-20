package com.example.walkeraki.application;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by walkeraki on 18.11.2015.
 */
public class CityInfo extends AppCompatActivity {
private String mesto="";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cityinfo);
        Bundle extras = getIntent().getExtras();
        mesto= extras.getString("keys","lol");
        Toast.makeText(getApplicationContext(), "" + mesto, Toast.LENGTH_LONG).show();
        Log.e("nameasdf", mesto);
        TextView text1 = (TextView)findViewById(R.id.textView1);
        TextView text2 = (TextView)findViewById(R.id.textView2);
        TextView text3 = (TextView)findViewById(R.id.textView3);


    if (android.os.Build.VERSION.SDK_INT > 9) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    //dobivanje podatkov iz massive arraya
    try {
        JSONObject weather = getJSON(this, mesto);

        String name = weather.getString("name");
        JSONObject details = weather.getJSONArray("weather").getJSONObject(0);
        JSONObject main = weather.getJSONObject("main");
        JSONObject wind = weather.getJSONObject("wind");
        JSONObject sys = weather.getJSONObject("sys");
        text1.setText("Humidity: "+main.getString("humidity")+"\n"+"Temperature: "+(main.getInt("temp")- 273)+"\n"+"Sea level: "+main.getString("sea_level"));
        text2.setText("Wind speed: "+wind.getString("speed"));
        text3.setText("Country: "+sys.getString("country")+"\n"+"Name: "+weather.getString("name"));
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

            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q="+city+",uk&appid=4ce55cc86a3095785f34052c7a698a4f");
            URLConnection connection = (URLConnection) url.openConnection();



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

