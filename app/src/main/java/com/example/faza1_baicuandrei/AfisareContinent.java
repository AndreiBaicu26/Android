package com.example.faza1_baicuandrei;

import android.app.ActionBar;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class AfisareContinent extends AppCompatActivity {

    TextView title;
    ListView lv;
    ArrayList<Country> countriesToBeDisplayed = new ArrayList<>();
    TextView tv;
    AppDatabase database;
    User user;
    public class JSONCountry extends AsyncTask<String, Void, String> {
        String result = null;

        //citim JSON si se creaza lista de tari pentru continente
        @Override
        protected String doInBackground(String... strings) {
            URL url = null;

            try {
                url = new URL(strings[0]);
                HttpURLConnection http = (HttpURLConnection) url.openConnection();

                http.connect();

                InputStream is = http.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));

                String linie = null;
                StringBuilder builder = new StringBuilder();
                while ((linie = reader.readLine()) != null) {
                    builder.append(linie);
                }
                database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "FavouriteCountries").allowMainThreadQueries().build();

                List<String> countries = database.countryDao().selectAllCountries();
                String totalText = builder.toString();
                JSONArray vectorCountries = new JSONArray(totalText);
                for (int i = 0; i < vectorCountries.length(); i++) {
                    JSONObject country = vectorCountries.getJSONObject(i);
                    Country c = new Country(country.get("alpha3Code").toString(),country.get("name").toString(), Long.parseLong(country.get("population").toString()), country.get("capital").toString());


                    if(countries.size()==0)
                        database.countryDao().insertCountry(c);

                    String st = getIntent().getExtras().getString("title");
                    if (st.equals(country.get("region").toString()) == true) {
                        countriesToBeDisplayed.add(c);
                    } else if (country.get("subregion").toString().equals("Northern America") && st.equals("North America")) {
                        countriesToBeDisplayed.add(c);
                    } else if (country.get("subregion").toString().equals("South America") && st.equals("South America")) {
                        countriesToBeDisplayed.add(c);
                    }
                }

                is.close();


            } catch (MalformedURLException | JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {

                result = "2";
            }

            return result;
        }


        @Override
        protected void onPostExecute(String s) {
                if(s != null)
                {
                    LinearLayout linearLayout = new LinearLayout(getApplicationContext());
                    TextView ProgrammaticallyTextView = new TextView(getApplicationContext());
                    ProgrammaticallyTextView.setText("Could not receive data.\n Check your internet connection");
                    ProgrammaticallyTextView.setTextSize(22);
                    ProgrammaticallyTextView.setPadding(200, 600, 20, 100);

                    linearLayout.addView(ProgrammaticallyTextView);

                    setContentView(linearLayout, new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

                }else {

                    lv = (ListView) findViewById(R.id.listViewCountries);
                    CountryAdapter adapter = new CountryAdapter(getBaseContext(), R.layout.custom_list_countries, countriesToBeDisplayed);
                    //de adaugat si poza
                    lv.setAdapter(adapter);

                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            Intent it = new Intent(getApplicationContext(), CountryInfo.class);
                            Bundle extras = new Bundle();
                            extras.putParcelable("user", user);
                            it.putExtra("country", countriesToBeDisplayed.get(position));
                            extras.putBoolean("isDark", getIntent().getExtras().getBoolean("isDark"));
                            it.putExtras(extras);
                            startActivity(it);

                        }
                    });
                }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if(getIntent().getExtras().getBoolean("isDark")){
            setTheme(R.style.DarkTheme);
        }else {
            setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afisare_continent);
        Intent it = getIntent();
        Bundle extras = it.getExtras();
        user = extras.getParcelable("user");

        showTitle();
        setimageView();


        countriesToBeDisplayed.clear();

        JSONCountry jsC = new JSONCountry();
        jsC.execute("https://restcountries.eu/rest/v2/all");



    }

    private void setimageView() {
        String st = getIntent().getExtras().getString("title");
        ImageView img =  findViewById(R.id.imgContinent);
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



    private void showTitle() {
        title = (TextView) findViewById(R.id.titleAfCont);
        String st = getIntent().getExtras().getString("title");

        title.setText("Countries of " + st);
    }

}
