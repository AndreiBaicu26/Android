package com.example.faza1_baicuandrei;

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
import android.widget.ListAdapter;
import android.widget.ListView;
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

import javax.net.ssl.HttpsURLConnection;

public class AfisareContinent extends AppCompatActivity {

    TextView title;
    ListView lv;
    ArrayList<Country> countriesToBeDisplayed = new ArrayList<>();
    TextView tv;

    public class JSONCountry extends AsyncTask<String, Void, String> {
        String result = null;


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


                String totalText = builder.toString();
                JSONArray vectorCountries = new JSONArray(totalText);
                for (int i = 0; i < vectorCountries.length(); i++) {
                    JSONObject country = vectorCountries.getJSONObject(i);

                    Country c = new Country(country.get("name").toString(), Long.parseLong(country.get("population").toString()), country.get("capital").toString());
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
                e.printStackTrace();
            }

            return result;
        }


        @Override
        protected void onPostExecute(String s) {
            lv = (ListView) findViewById(R.id.listViewCountries);
            CountryAdapter adapter = new CountryAdapter(getApplicationContext(), R.layout.custom_list_countries, countriesToBeDisplayed);
            //de adaugat si poza
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent it = new Intent(getApplicationContext(), CountryInfo.class);
                    it.putExtra("country", countriesToBeDisplayed.get(position));
                    startActivity(it);
                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afisare_continent);
        tv = (TextView) findViewById(R.id.tVLoading);
        showTitle();
        setimageView();
        Bundle bundle = getIntent().getExtras();

        countriesToBeDisplayed.clear();

        JSONCountry jsC = new JSONCountry();
        jsC.execute("https://restcountries.eu/rest/v2/all");

        while (countriesToBeDisplayed.size() == 0) {
            tv.setVisibility(View.VISIBLE);

        }
        tv.setVisibility(View.INVISIBLE);



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
