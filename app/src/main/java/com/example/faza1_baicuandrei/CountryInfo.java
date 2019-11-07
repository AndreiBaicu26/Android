package com.example.faza1_baicuandrei;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CountryInfo extends AppCompatActivity {
    private static ArrayList<Country> favourites = new ArrayList<>();
    private CheckBox cb;
    private Country c = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_info);

        Intent it = getIntent();
        c = it.getExtras().getParcelable("country");

        TextView textViewTitle = (TextView) findViewById(R.id.textViewCName);
        TextView textViewCapital = (TextView) findViewById(R.id.textViewCapital);
        TextView textViewPop = (TextView) findViewById(R.id.textViewPop);

        textViewTitle.setText(c.getName());
        textViewCapital.setText(c.getCapital());
        String s = String.format("%,d", c.getPopulation());
        textViewPop.setText(s);

        cb = (CheckBox) findViewById(R.id.checkBoxFav);

        checkIfInFavourites();


    }

    private void checkIfInFavourites() {
        for (Country c2 : favourites) {
            if (c2.getName().equals(c.getName()))
                cb.setChecked(true);
        }
    }

    public void onCheckboxClicked(View view) {
        boolean checked = cb.isChecked();

        if (checked) {
            favourites.add(c);
            Toast t = Toast.makeText(this,c.getName() +" added to Favourites", Toast.LENGTH_SHORT);
            t.show();
        } else {
            for (Country c3 : favourites) {
                if (c3.getName().equals(c.getName())) {
                    favourites.remove(c3);
                    break;
                }

            }
            Toast t = Toast.makeText(this,c.getName() +" was removed from Favourites", Toast.LENGTH_SHORT);
            t.show();

        }
    }

    public static ArrayList<Country> getFavourites() {
        return favourites;
    }
}
