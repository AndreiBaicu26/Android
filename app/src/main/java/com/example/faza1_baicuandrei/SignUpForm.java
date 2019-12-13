package com.example.faza1_baicuandrei;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class SignUpForm extends AppCompatActivity {

    private AppDatabase database;
    EditText etEmail;
    EditText etPassword;
    Boolean worked; //daca verificarea a reusit
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_form);

        etEmail = findViewById(R.id.editTextEmail2);
        etPassword = findViewById(R.id.editTextPassword2);
        database= Room.databaseBuilder(this, AppDatabase.class,"FavouriteCountries").allowMainThreadQueries().build();
    }

    public void insertUser(){
        if(etEmail.getText().toString().length() > 0 && etPassword.getText().toString().length() >0) {
            List<String> emails =database.userDao().selectEmailFromUser();
            if ( emails!= null) {
                if (database.userDao().selectEmailFromUser().contains(etEmail.getText().toString())) {

                Toast.makeText(getApplicationContext(), "Email already exists!", Toast.LENGTH_SHORT).show();
                worked = false;
            } else {
                User user = new User(0, etEmail.getText().toString(), etPassword.getText().toString());
                database.userDao().insertUser(user);
                Toast.makeText(getApplicationContext(), "Insert successful!", Toast.LENGTH_SHORT).show();
                worked = true;
            }

        }else{
                worked = false;
            }
        }

    }

    public void registerUser(View view) {
        insertUser();
        if(worked){
            Intent it = new Intent(getApplicationContext(), WelcomePage.class);
            it.putExtra("Email",etEmail.getText().toString());
            it.putExtra("Password", etPassword.getText().toString());
            setResult(200,it);
            finish();
        }
    }
}
