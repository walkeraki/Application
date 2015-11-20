package com.example.walkeraki.application;
import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by walkeraki on 17.11.2015.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<String> pojos;
    private static final String TAG = "MyActivity";
    private TextView displayTextView;
    private Context ime;


    public static class ViewHolder extends RecyclerView.ViewHolder  {

        public View View;
        public ViewHolder(View v) {
            super(v);
            View = v;
        }
    }


    public MyAdapter(ArrayList<String>pojos,Context ime) {
        this.pojos = pojos;
        this.ime = ime;

    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }
    public void removeItem(int position) {
        pojos.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        TextView title= (TextView) holder.View.findViewById(R.id.title);
        TextView temp= (TextView) holder.View.findViewById(R.id.temp);


        title.setText(pojos.get(position));
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ime, CityInfo.class);
                intent.putExtra("keys",pojos.get(position));
                notifyItemRemoved(position);
                //Log.v(TAG, "index=" + pojos.get(position));
                ime.startActivity(intent);
            }


        });

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        //dobivanje podatkov iz massive arraya
        try {
            JSONObject weather = getJSON(pojos.get(position));

            String name = weather.getString("name");
            JSONObject details = weather.getJSONArray("weather").getJSONObject(0);
            JSONObject main = weather.getJSONObject("main");
            temp.setText("Temperature: "+(main.getInt("temp")- 273)+"C");
            /*Log.e("test", details.getString("main") + "");
            Log.e("name",main.getString("humidity"));
            Log.e("name",name);
            */
        }catch(Exception e){
            Log.e("Error", "Not found");
        }
    }





    private static final String OPEN_WEATHER_MAP_API = "";
    private static String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
    private static String IMG_URL = "http://openweathermap.org/img/w/";


    //dobivanje erraya
    public static JSONObject getJSON(String city){
        try {

            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q="+city+",uk&appid=4ce55cc86a3095785f34052c7a698a4f");
            URLConnection connection = (URLConnection) url.openConnection();



            InputStream is = new BufferedInputStream(connection.getInputStream());

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            StringBuffer json = new StringBuffer(1024);
            String tmp = "";
            while ((tmp = reader.readLine()) != null){
                json.append(tmp).append("\n");
                //Log.e("Some website", tmp);
            }
            reader.close();

            JSONObject data = new JSONObject(json.toString());


            if(data.getInt("cod") != 200){
                return null;
            }

            return data;
        }catch(Exception e){
            //Log.e("error", "ssdsd" + e.getStackTrace() );
            return null;
        }
    }
        @Override
    public int getItemCount() {
        return pojos.size();
    }

}