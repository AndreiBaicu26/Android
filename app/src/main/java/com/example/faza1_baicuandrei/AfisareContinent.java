package com.example.faza1_baicuandrei;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class AfisareContinent extends AppCompatActivity {

    TextView title;
    ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afisare_continent);

        showTitle();
        setimageView();


        Bundle bundle = getIntent().getExtras();
       final  ArrayList<Country> countries = bundle.getParcelableArrayList("countries");

        lv = (ListView) findViewById(R.id.listViewCountries);
        ArrayList<String> countryNames = new ArrayList<String>();
        Collections.sort(countries,Country.alphabetical);


        for (Country c : countries) {
            countryNames.add(c.getName().toString());
        }

        for (int i = 0; i < countries.size(); i++) {
            lv.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, countryNames) {
            });
        }


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView) view;
                String countryName = tv.getText().toString();
                Country c2 = null;
                for (Country c : countries) {
                    if (c.getName().equals(countryName)) {
                        c2 = c;
                    }
                }
                launchActivity(c2);
            }
        });
    }

    private void setimageView() {
        String st = getIntent().getExtras().getString("title");
        ImageView img = (ImageView) findViewById(R.id.imgContinent);
        switch (st) {
            case ("Europe"):
                img.setImageResource(R.drawable.europe);
                break;
            case ("Asia"):
                img.setImageResource(R.drawable.asia);
                break;
            case ("Africa"):
                img.setImageResource(R.drawable.africa);
                break;
            case ("South America"):
                img.setImageResource(R.drawable.south_am);
                break;
            case ("North America"):
                img.setImageResource(R.drawable.north_am);
                break;
            case ("Oceania"):
                img.setImageResource(R.drawable.oceania);
                break;
            default:
                return;
        }
    }

    private void launchActivity(Country c2) {
        Intent it = new Intent(this, CountryInfo.class);
        it.putExtra("country", c2);
        startActivity(it);
    }


    private void showTitle() {
        title = (TextView) findViewById(R.id.titleAfCont);
        String st = getIntent().getExtras().getString("title");

        title.setText("Countries of " + st);
    }

}
