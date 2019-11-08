package com.example.faza1_baicuandrei;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Rate_Us_Fragments extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate__us__fragments);

        FirstFragment firstFragment = new FirstFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.firstLayout, firstFragment, firstFragment.getTag()).commit();

        SecondFragment secondFragment = new SecondFragment();
        manager.beginTransaction().replace(R.id.secondLayout, secondFragment, secondFragment.getTag()).commit();

    }
}
