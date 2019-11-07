package com.example.faza1_baicuandrei;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Favourites extends AppCompatActivity {

    ArrayList<Country> fav = new ArrayList<Country>();
    ListView lv = null;
    TextView tv;

    ArrayAdapter<String> adapter1 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_favourites);
        Spinner spinner = findViewById(R.id.spinnerSortID);
        lv = (ListView) findViewById(R.id.favCountryList);
        tv = (TextView) findViewById(R.id.tvFav);
        populateArray();
        refresh();

        String[] elements = {"Alphabetical", "Population asc", "Population desc"};

        adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, elements);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter1);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
              @Override
              public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                  switch (position) {
                      case 0:
                          Collections.sort(fav, Country.alphabetical);
                          CountryAdapter adapter = new CountryAdapter(getApplicationContext(), R.layout.custom_list_countries, fav);
                          lv.setAdapter(adapter);
                          break;
                      case 1:
                          Collections.sort(fav, Country.popAsc);
                          CountryAdapter adapter2 = new CountryAdapter(getApplicationContext(), R.layout.custom_list_countries, fav);
                          lv.setAdapter(adapter2);
                          break;
                      case 2:
                          Collections.sort(fav, Country.popDesc);
                          CountryAdapter adapter3 = new CountryAdapter(getApplicationContext(), R.layout.custom_list_countries, fav);
                          lv.setAdapter(adapter3);
                          break;
                      default:
                          return;
                  }


              }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                CountryAdapter adapter4 = new CountryAdapter(getApplicationContext(), R.layout.custom_list_countries, fav);
                lv.setAdapter(adapter4);
            }
        });



        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Intent it = new Intent(getApplicationContext(), CountryInfo.class);
                it.putExtra("country", fav.get(position));
                startActivity(it);

            }
        });


    }


    private void refresh() {
        if (fav.size() > 0) {

            CountryAdapter adapter = new CountryAdapter(this, R.layout.custom_list_countries, fav);
            lv.setAdapter(adapter);
            tv.setTextSize(25);
            tv.setText("Your favourite countries list");
        } else {
            CountryAdapter adapter = new CountryAdapter(this, R.layout.custom_list_countries, fav);
            lv.setAdapter(adapter);
            tv.setTextSize(20);
            tv.setText("You do not have any favourite countries");
        }
    }

    @Override
    protected void onRestart() {
        populateArray();
        refresh();
        super.onRestart();

    }

    public void populateArray() {
        fav = CountryInfo.getFavourites();

    }

    private void launchActivity(Country c2) {
        Intent it = new Intent(this, CountryInfo.class);
        it.putExtra("country", c2);
        startActivity(it);
    }

}
