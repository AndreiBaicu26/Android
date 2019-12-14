package com.example.faza1_baicuandrei;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CountryInfo extends AppCompatActivity {


    private CheckBox cb;
    private Country c;
    Button btnFeedback;
    AppDatabase database;
    User user;
    FavouriteCountriesCrossRef fav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent it = getIntent();
        Bundle extras = it.getExtras();

        if (extras.getBoolean("isDark")) {
            setTheme(R.style.DarkTheme);
        } else {
            setTheme(R.style.AppTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_info);

        database = Room.databaseBuilder(this, AppDatabase.class, "FavouriteCountries").allowMainThreadQueries().build();
        c = extras.getParcelable("country");
        user = extras.getParcelable("user");
        fav = new FavouriteCountriesCrossRef(c.getId(), user.getId());

        TextView textViewTitle = (TextView) findViewById(R.id.textViewCName);
        TextView textViewCapital = (TextView) findViewById(R.id.textViewCapital);
        TextView textViewPop = (TextView) findViewById(R.id.textViewPop);

        textViewTitle.setText(c.getName());
        textViewCapital.setText(c.getCapital());
        String s = String.format("%,d", c.getPopulation());
        textViewPop.setText(s);

        cb = (CheckBox) findViewById(R.id.checkBoxFav);

        checkIfInFavourites();
        btnFeedback = (Button) findViewById(R.id.btnFeedback);

        btnFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), Feedback.class);
                it.putExtra("isDark", getIntent().getExtras().getBoolean("isDark"));
                startActivity(it);
            }
        });


    }

    private void checkIfInFavourites() {
        FavouriteCountriesCrossRef fav2 = database.favouriteCountriesCrossRefDAO().checIfFavExists(c.getId(), user.getId());
        if (fav2 != null) {
            cb.setChecked(true);
        }
    }

    public void onCheckboxClicked(View view) {
        boolean checked = cb.isChecked();

        if (checked) {
            database.favouriteCountriesCrossRefDAO().insertFavCountries(fav);
            Toast t = Toast.makeText(this, c.getName() + " added to Favourites", Toast.LENGTH_SHORT);
            t.show();
        } else {
            database.favouriteCountriesCrossRefDAO().removeFromFav(fav);
            Toast t = Toast.makeText(this, c.getName() + " was removed from Favourites", Toast.LENGTH_SHORT);
            t.show();

        }
    }

}
