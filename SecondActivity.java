package com.example.exam;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Location;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    TextView tv;
    TextView tv2;
    ListView LW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Bundle arguments = getIntent().getExtras();
        String curCity = arguments.get("city").toString();
        String km = arguments.get("km").toString();
        String cities = arguments.get("cities_string").toString();
        Double curLat = null;
        Double curLng = null;

        tv = (TextView) findViewById(R.id.textView2);
        tv2 = (TextView) findViewById(R.id.textView3);
        LW = (ListView) findViewById(R.id.listViewCities);

        List<String> list = new ArrayList<String>();

        try {
            JSONArray obj = new JSONArray(cities);
            for (int i = 0; i < obj.length(); i++) {
                if (curCity.equals(obj.getJSONObject(i).getString("name"))) {
                    curLat = obj.getJSONObject(i).getJSONObject("coord").getDouble("lat");
                    curLng = obj.getJSONObject(i).getJSONObject("coord").getDouble("lon");
                }
            }

            for (int i = 0; i < obj.length(); i++) {
                double distance = getDistance(curLat, curLng, obj.getJSONObject(i).getJSONObject("coord").getDouble("lat"), obj.getJSONObject(i).getJSONObject("coord").getDouble("lon"));

                if (Double.parseDouble(km) >= distance && distance != 0){
                    list.add("Город: " + obj.getJSONObject(i).getString("name") + ".\nРасстояние до него: " +distance+ " км.");
                }

            }
        } catch (JSONException e) {
            System.out.println("JSON Error!");
            e.printStackTrace();
        }

        tv.setText("Вы выбрали город " + curCity);
        tv2.setText("Отображены ближайшие города в радиусе " + km + " км.");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        LW.setAdapter(adapter);
    }

    public static Double getDistance(double lat, double lng, double lat2, double lng2) {
        double totalDistance = 0;
        double distance = 0;

        Location locationA = new Location("point A");
        locationA.setLatitude(lat);
        locationA.setLongitude(lng);
        Location locationB = new Location("point B");
        locationB.setLatitude(lat2);
        locationB.setLongitude(lng2);
        int currentDistance = (int) locationA.distanceTo(locationB) / 1000;
        totalDistance += Math.abs(distance - currentDistance);
        distance = currentDistance;

        return distance/1000;
    }
}