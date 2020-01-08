package com.example.faza1_baicuandrei;
import android.content.Context;
import android.support.annotation.NonNull;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.faza1_baicuandrei.Country;
import com.example.faza1_baicuandrei.R;

import java.util.List;
import java.util.zip.Inflater;

public class CountryAdapter extends ArrayAdapter<Country> {

    private int resourceID;

    public CountryAdapter(@NonNull Context context, int resource, @NonNull List<Country> objects) {
        super(context, resource, objects);
        resourceID  = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        String s = null;
        Country c = getItem(position);
        LayoutInflater inflater =  LayoutInflater.from(getContext());
        View v = inflater.inflate(resourceID,null);
        TextView cName = (TextView) v.findViewById(R.id.cnameID);
        TextView cPop = (TextView) v.findViewById(R.id.cPopID);
        TextView cCapital= (TextView) v.findViewById(R.id.cCapitalID);

        cName.setText(c.getName());
        s = String.format("%,d", c.getPopulation());
        cPop.setText(cPop.getText() + s);
        cCapital.setText(cCapital.getText() + c.getCapital());

        return v;
    }
}
