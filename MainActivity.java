package com.example.exam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String activeCity;
    EditText numb;
    Intent intent;
    String cities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numb = findViewById(R.id.kmEdit);
        cities = "[{\"id\":833,\"name\":\"Ḩeşār-e Sefīd\",\"state\":\"\",\"country\":\"IR\",\"coord\":{\"lon\":47.159401,\"lat\":34.330502}},{\"id\":2960,\"name\":\"‘Ayn Ḩalāqīm\",\"state\":\"\",\"country\":\"SY\",\"coord\":{\"lon\":36.321911,\"lat\":34.940079}},{\"id\":3245,\"name\":\"Taglag\",\"state\":\"\",\"country\":\"IR\",\"coord\":{\"lon\":44.98333,\"lat\":38.450001}},{\"id\":3530,\"name\":\"Qabāghlū\",\"state\":\"\",\"country\":\"IR\",\"coord\":{\"lon\":46.168499,\"lat\":36.173302}},{\"id\":5174,\"name\":\"‘Arīqah\",\"state\":\"\",\"country\":\"SY\",\"coord\":{\"lon\":36.48336,\"lat\":32.889809}},{\"id\":7264,\"name\":\"Kalāteh-ye Dowlat\",\"state\":\"\",\"country\":\"IR\",\"coord\":{\"lon\":57.616982,\"lat\":36.163841}},{\"id\":8084,\"name\":\"Behjatābād\",\"state\":\"\",\"country\":\"IR\",\"coord\":{\"lon\":51.461639,\"lat\":36.667431}},{\"id\":9874,\"name\":\"Ţālesh Maḩalleh\",\"state\":\"\",\"country\":\"IR\",\"coord\":{\"lon\":50.679192,\"lat\":36.894329}},{\"id\":11263,\"name\":\"Shahrīār Kandeh\",\"state\":\"\",\"country\":\"IR\",\"coord\":{\"lon\":53.19902,\"lat\":36.631939}},{\"id\":11754,\"name\":\"Bālā Aḩmad Kolā\",\"state\":\"\",\"country\":\"IR\",\"coord\":{\"lon\":52.667271,\"lat\":36.649059}},{\"id\":12795,\"name\":\"Aş Şūrah aş Şaghīrah\",\"state\":\"\",\"country\":\"SY\",\"coord\":{\"lon\":36.573872,\"lat\":33.032669}},{\"id\":14177,\"name\":\"Āqdūz\",\"state\":\"\",\"country\":\"IR\",\"coord\":{\"lon\":44.26556,\"lat\":39.197498}},{\"id\":14256,\"name\":\"Āzādshahr\",\"state\":\"\",\"country\":\"IR\",\"coord\":{\"lon\":48.570728,\"lat\":34.790878}},{\"id\":18007,\"name\":\"Gollar\",\"state\":\"\",\"country\":\"IR\",\"coord\":{\"lon\":46.25,\"lat\":37.383331}},{\"id\":18093,\"name\":\"Dīgāleh\",\"state\":\"\",\"country\":\"IR\",\"coord\":{\"lon\":45.106998,\"lat\":37.552502}},{\"id\":18557,\"name\":\"Qarālar-e Mīrzā Ḩoseynqolī\",\"state\":\"\",\"country\":\"IR\",\"coord\":{\"lon\":45.067501,\"lat\":37.674702}},{\"id\":18918,\"name\":\"Protaras\",\"state\":\"\",\"country\":\"CY\",\"coord\":{\"lon\":34.058331,\"lat\":35.012501}},{\"id\":23814,\"name\":\"Kahrīz\",\"state\":\"\",\"country\":\"IR\",\"coord\":{\"lon\":47.055302,\"lat\":34.383801}},{\"id\":24851,\"name\":\"Nūrābād\",\"state\":\"\",\"country\":\"IR\",\"coord\":{\"lon\":47.9725,\"lat\":34.073399}},{\"id\":29033,\"name\":\"Rokan Sarā\",\"state\":\"\",\"country\":\"IR\",\"coord\":{\"lon\":49.608101,\"lat\":37.32085}},{\"id\":30321,\"name\":\"Zīrjān\",\"state\":\"\",\"country\":\"IR\",\"coord\":{\"lon\":58.43586,\"lat\":34.214401}},{\"id\":30485,\"name\":\"Dahasuways\",\"state\":\"\",\"country\":\"YE\",\"coord\":{\"lon\":50.729439,\"lat\":15.72389}},{\"id\":30490,\"name\":\"Ash Shihr\",\"state\":\"\",\"country\":\"YE\",\"coord\":{\"lon\":49.606392,\"lat\":14.75863}},{\"id\":30543,\"name\":\"Al Ghaylah\",\"state\":\"\",\"country\":\"YE\",\"coord\":{\"lon\":45.583328,\"lat\":14.59583}},{\"id\":30616,\"name\":\"Judaydah\",\"state\":\"\",\"country\":\"YE\",\"coord\":{\"lon\":45.299622,\"lat\":15.07512}},{\"id\":30689,\"name\":\"Dawran\",\"state\":\"\",\"country\":\"YE\",\"coord\":{\"lon\":44.441959,\"lat\":13.77436}}]";

        List<String> cities_list = new ArrayList<String>();
        Spinner spinner = (Spinner) findViewById(R.id.citiesSpinner);

        try {
            JSONArray obj = new JSONArray(cities);
            for (int i = 0; i < obj.length(); i++) {
                cities_list.add(obj.getJSONObject(i).getString("name"));
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cities_list);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    activeCity = cities_list.get(position);
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void onButtonClick(View v) {
        System.out.println("Город: " + activeCity + " километраж: " + numb.getText());
        intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra("city", activeCity);
        intent.putExtra("km", numb.getText());
        intent.putExtra("cities_string", cities);
        startActivity(intent);

    }
}