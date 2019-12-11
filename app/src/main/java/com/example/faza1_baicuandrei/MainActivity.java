package com.example.faza1_baicuandrei;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.AppCompatImageButton;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Country> europeCountries = new ArrayList<>();
    ArrayList<Country> northACountries = new ArrayList<>();
    ArrayList<Country> southACountries = new ArrayList<>();
    ArrayList<Country> asiaCountries = new ArrayList<>();
    ArrayList<Country> oceaniaCountries = new ArrayList<>();
    ArrayList<Country> africaCountries = new ArrayList<>();
    Boolean isDark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        isDark = getIntent().getExtras().getBoolean("isDark");
        if(isDark ){
            setTheme(R.style.DarkTheme);
        }else {
            setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button)v;
                String text = b.getText().toString();
                openActivity(text);

            }
        };

        Button btnEurope =  findViewById(R.id.btnEurope);
        Button btnNorthA =  findViewById(R.id.btnNorthA);
        Button btnSouthA =  findViewById(R.id.btnSouthA);
        Button btnAsia =  findViewById(R.id.btnAsia);
        Button btnOceania =  findViewById(R.id.btnOceania);
        Button btnAfrica =  findViewById(R.id.btnAfrica);
        Button btnFav = findViewById(R.id.btnFav);



        btnEurope.setOnClickListener(listener);
        btnNorthA.setOnClickListener(listener);
        btnSouthA.setOnClickListener(listener);
        btnOceania.setOnClickListener(listener);
        btnAsia.setOnClickListener(listener);
        btnAfrica.setOnClickListener(listener);


        createListEurope();
        createListNorthA();
        createListSouthA();
        createListAsia();
        createListOceania();
        createListAfrica();

        btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(), Favourites.class);
                it.putExtra("isDark",isDark );
                startActivity(it);
            }
        });


    }


    private void createListAfrica() {
        Country nigeria = new Country("Nigeria", 190000000,"Abuja");
        Country kenya = new Country("Kenya",49000000,"Nairobi");

        africaCountries.add(nigeria);
        africaCountries.add(kenya);

    }


    private void createListOceania() {
        Country australia = new Country("Australia", 24000000,"Canberra");
        Country indonesia = new Country("Indonesia",264000000,"Jakarta");

        oceaniaCountries.add(australia);
        oceaniaCountries.add(indonesia);
    }

    private void createListAsia() {
        Country china = new Country("China", 1386000000,"Beijing");
        Country japan = new Country("Japan", 127000000, "Tokyo");

        asiaCountries.add(china);
        asiaCountries.add(japan);
    }

    private void createListSouthA() {
        Country brazil = new Country("Brazil", 202000000,"Brasil");
        Country argentina = new Country("Argentina",40000000,"Buenos Aires");

        southACountries.add(brazil);
        southACountries.add(argentina);
    }

    private void createListNorthA() {
        Country usa = new Country("United States", 318626220,"Washington DC");
        Country bahamas = new Country("Bahamas", 	321834, "Nassau");

        northACountries.add(usa);
        northACountries.add(bahamas);
    }

    private void createListEurope() {
        Country romania = new Country("Romania", 19860000,"Bucharest");
        Country germany = new Country("Germany", 80000000, "Berlin");

        europeCountries.add(romania);
        europeCountries.add(germany);
    }




    private void openActivity(String s) {
        Intent it = new Intent(this, AfisareContinent.class);
        it.putExtra("title", s);
        it.putExtra("isDark", isDark);
        switch(s)
        {
            case "Europe":
                it.putExtra("countries", europeCountries);
                break;
            case "Africa":
                it.putExtra("countries", africaCountries);
                break;
            case "Asia":
                it.putExtra("countries", asiaCountries);
                break;
            case "North America":
                it.putExtra("countries", northACountries);
                break;
            case "South America":
                it.putExtra("countries", southACountries);
                break;
            case "Oceania":
                it.putExtra("countries", oceaniaCountries);
                break;
            default:
                break;
        }
        startActivity(it);
    }

}
