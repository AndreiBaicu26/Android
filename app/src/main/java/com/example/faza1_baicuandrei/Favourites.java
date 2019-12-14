package com.example.faza1_baicuandrei;


import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class Favourites extends AppCompatActivity {

    List<Country> fav;
    ListView lv = null;
    TextView tv;
    AppDatabase database;
    ArrayAdapter<String> adapter1 = null;
    User user;
    Button btnGetTXT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent it = getIntent();
        Bundle extras = it.getExtras();
        final Boolean isDark = extras.getBoolean("isDark");
        if(isDark){
            setTheme(R.style.DarkTheme);
        }else {
            setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_favourites);
        Spinner spinner = findViewById(R.id.spinnerSortID);
        lv =  findViewById(R.id.favCountryList);
        tv =  findViewById(R.id.tvFav);
        btnGetTXT = findViewById(R.id.btnGetTXT);

        database = Room.databaseBuilder(this,AppDatabase.class,"FavouriteCountries").allowMainThreadQueries().build();
        user = extras.getParcelable("user");
        populateArray();
        refresh();
        setVisibilityForBtnGetTXT();

        String[] elements = {"Alphabetical", "Population asc", "Population desc"};

        adapter1 = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_spinner_item, elements);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter1);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
              @Override
              public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 if(isDark) {
                     ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
                     ((TextView) parent.getChildAt(0)).setTextSize(17);
                 }
                  switch (position) {
                      case 0:
                          Collections.sort(fav, Country.alphabetical);
                          CountryAdapter adapter = new CountryAdapter(getBaseContext(), R.layout.custom_list_countries, fav);
                          lv.setAdapter(adapter);
                          break;
                      case 1:
                          Collections.sort(fav, Country.popAsc);
                          CountryAdapter adapter2 = new CountryAdapter(getBaseContext(), R.layout.custom_list_countries, fav);
                          lv.setAdapter(adapter2);
                          break;
                      case 2:
                          Collections.sort(fav, Country.popDesc);
                          CountryAdapter adapter3 = new CountryAdapter(getBaseContext(), R.layout.custom_list_countries, fav);
                          lv.setAdapter(adapter3);
                          break;
                      default:
                          return;
                  }


              }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                CountryAdapter adapter4 = new CountryAdapter(getBaseContext(), R.layout.custom_list_countries, fav);
                lv.setAdapter(adapter4);
            }
        });



        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Intent it = new Intent(getApplicationContext(), CountryInfo.class);
                Bundle extras = new Bundle();
                extras.putParcelable("user", user);
                it.putExtra("country", fav.get(position));
                extras.putBoolean("isDark", getIntent().getExtras().getBoolean("isDark"));
                it.putExtras(extras);
                startActivity(it);

            }
        });


    }

    private void setVisibilityForBtnGetTXT() {
        if (fav.size() > 0 )
            btnGetTXT.setVisibility(View.VISIBLE);
        else
            btnGetTXT.setVisibility(View.INVISIBLE);
    }


    private void refresh() {
        if (fav.size() > 0) {

            CountryAdapter adapter = new CountryAdapter(getBaseContext(), R.layout.custom_list_countries, fav);
            lv.setAdapter(adapter);
            tv.setTextSize(25);
            tv.setText(user.getEmail() + "'s favourite countries list");
        } else {
            CountryAdapter adapter = new CountryAdapter(getBaseContext(), R.layout.custom_list_countries, fav);
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
        fav = database.favouriteCountriesCrossRefDAO().returnUsersFavouriteCountries(user.getId());

    }


    public void onClickGetTextFile(View view){
        FileOutputStream fos =null;
        StringBuilder sb= new StringBuilder();
        String path = "/data/user/0/com.example.faza1_baicuandrei/files/" + user.getEmail() + ".txt";
        File file = new File(path);

        for (int i = 0; i < fav.size(); i ++){
            sb.append(((i + 1) + ". " + fav.get(i).toString()));
        }
        String text = sb.toString();
        try {
            fos = openFileOutput(file.getName(), Context.MODE_PRIVATE);
            fos.write(text.getBytes());

            Toast.makeText(this, "Saved to " + file.getPath(),
                    Toast.LENGTH_LONG).show();

        }catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }finally {
            if(fos!= null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
