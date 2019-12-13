package com.example.faza1_baicuandrei;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.AppCompatImageButton;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Country> europeCountries = new ArrayList<>();
    ArrayList<Country> northACountries = new ArrayList<>();
    ArrayList<Country> southACountries = new ArrayList<>();
    ArrayList<Country> asiaCountries = new ArrayList<>();
    ArrayList<Country> oceaniaCountries = new ArrayList<>();
    ArrayList<Country> africaCountries = new ArrayList<>();
    Boolean isDark;
    User user = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent it = getIntent();
        Bundle extras = it.getExtras();
        isDark = extras.getBoolean("isDark");
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



        btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(), Favourites.class);
                it.putExtra("isDark",isDark );
                startActivity(it);
            }
        });


        user = extras.getParcelable("user");
        Toast.makeText(getApplicationContext(),user.getEmail(), Toast.LENGTH_LONG).show();
    }


    private void openActivity(String s) {

        Intent it = new Intent(this, AfisareContinent.class);
        Bundle extras = new Bundle();
        extras.putString("title", s);
        extras.putParcelable("user", user);
        extras.putBoolean("isDark", isDark);
        it.putExtras(extras);
        startActivity(it);
    }

}
