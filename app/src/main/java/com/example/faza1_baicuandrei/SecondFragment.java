package com.example.faza1_baicuandrei;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {

    RatingBar rbIntuity;
    RatingBar rbDesign;
    RatingBar rbInfo;
    float total = 0;
    Button submit;
    public SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View v= inflater.inflate(R.layout.fragment_second, container, false);
        rbInfo = (RatingBar)v.findViewById(R.id.ratingBarInfoProv);
        rbDesign = (RatingBar) v.findViewById((R.id.ratingBarDesign));
        rbIntuity = (RatingBar) v.findViewById((R.id.ratingBarIntuity));
        submit  = (Button)v.findViewById(R.id.btnSubmit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rbInfo.getRating() >0 && rbIntuity.getRating() > 0 && rbDesign.getRating()>0)
                {
                    total =rbInfo.getRating() + rbIntuity.getRating() + rbDesign.getRating();
                    Toast t = Toast.makeText(getContext(),"Thank you!", Toast.LENGTH_SHORT);
                    t.setGravity(Gravity.CENTER,0,0);
                    t.show();
                    FirstFragment frag = (FirstFragment) getFragmentManager().findFragmentById(R.id.firstLayout);
                    frag.updateText(Float.toString(total));


                }else
                {
                    Toast toast = Toast.makeText(getContext(),"Please rate all attributes", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER,0,0);

                    toast.show();
                }
            }
        });

        return v;
    }

}
