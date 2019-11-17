package com.example.faza1_baicuandrei;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {

    TextView tv;
    View thisView;
    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        thisView  = inflater.inflate(R.layout.fragment_first, container, false);
        tv = (TextView) thisView.findViewById(R.id.textViewAvg);

        return thisView;
    }

    public void updateText(String s)
    {
        tv.setText(tv.getText() + " " +s);
    }

}
