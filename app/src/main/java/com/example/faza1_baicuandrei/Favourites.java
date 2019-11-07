package com.example.faza1_baicuandrei;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Favourites extends AppCompatActivity {

    ArrayList<Country> fav = new ArrayList<Country>();
    ListView lv = null ;
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
        lv = (ListView) findViewById(R.id.favCountryList);
        tv = (TextView) findViewById(R.id.tvFav);
        populateArray();
        refresh();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Intent it = new Intent(getApplicationContext(),CountryInfo.class);
                it.putExtra("country",fav.get(position));
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

    public void populateArray()
    {
        fav = CountryInfo.getFavourites();

    }

    private void launchActivity(Country c2) {
        Intent it = new Intent(this,CountryInfo.class);
        it.putExtra("country",c2);
        startActivity(it);
    }

}
