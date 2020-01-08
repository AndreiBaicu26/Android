package com.example.faza1_baicuandrei;

import android.provider.ContactsContract;
import android.service.autofill.Dataset;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdminPage extends AppCompatActivity {
    CardView cv;
    DatabaseReference myRef;
    ArrayList<Review> reviewbackList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);
        cv = findViewById((R.id.cardView1));


        ImageView imageView = findViewById(R.id.btnImg);
        String imageUrl = "https://cdn1.iconfinder.com/data/icons/data-science-flat-1/64/statistics-pie-chart-label-data-report-512.png";
        Picasso.get()
                .load(imageUrl)
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(getApplicationContext(),"No internet",Toast.LENGTH_SHORT).show();
                    }
                });


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        Toast.makeText(getApplicationContext(),"Welcome Admin",Toast.LENGTH_SHORT).show();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                reviewbackList= new ArrayList<>();
                DataSnapshot ds = dataSnapshot.child("review");
                Iterable<DataSnapshot> reviewDS = ds.getChildren();
                for(DataSnapshot d: reviewDS){
                    Review f = d.getValue(Review.class);
                    reviewbackList.add(f);
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(getApplicationContext(),"Check your internet connection", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void loadFromFirebaseAndDraw(View view) {
        if(reviewbackList==null) {
            Toast.makeText(getApplicationContext(),"Loading", Toast.LENGTH_SHORT).show();
        }else {
            PieChart pc = new PieChart(this, reviewbackList);
            cv.addView(pc);
        }
    }

}
