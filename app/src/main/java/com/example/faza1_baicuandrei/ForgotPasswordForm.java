package com.example.faza1_baicuandrei;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class ForgotPasswordForm extends AppCompatActivity {

    AppDatabase database;
    EditText etEmail;
    EditText etPassword;
    Button btnReset;
    Button btnCheck;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent it = getIntent();

        final Boolean isDark = it.getExtras().getBoolean("isDark");
        if(isDark){
            setTheme(R.style.DarkTheme);
        }else {
            setTheme(R.style.AppTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pasword_form);

        database= Room.databaseBuilder(this, AppDatabase.class,"FavouriteCountries").allowMainThreadQueries().build();
        etEmail = findViewById(R.id.editTextEmail3);
        etPassword = findViewById(R.id.editTextPassword3);
        btnReset = findViewById(R.id.buttonReset);
        btnCheck = findViewById(R.id.buttonCheckUser);
        btnReset.setVisibility(View.INVISIBLE);
        etPassword.setVisibility(View.INVISIBLE);
        user = null;

    }

    public void checkUser() {
        if (etEmail.getText().toString().length() >0) {
             user = database.userDao().checkUserWithEmailGiven(etEmail.getText().toString());
            if (user != null) {
                etPassword.setVisibility(View.VISIBLE);
                btnReset.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(),"Insert your new password",Toast.LENGTH_LONG).show();
                btnCheck.setVisibility(View.INVISIBLE);
                etEmail.setEnabled(false);
                return;
                } else {
                   Toast.makeText(getApplicationContext(),"Could not find user",Toast.LENGTH_LONG).show();
                    return;
                }

        }
        Toast.makeText(getApplicationContext(),"Please insert an email",Toast.LENGTH_LONG).show();
        return;
    }


    public void resetPassword(View view) {
        if(etPassword.getText().toString().length() >0 ){
            user.setPassword(etPassword.getText().toString());
            database.userDao().updatePassword(user);
            Toast.makeText(getApplicationContext(),"Password changed!", Toast.LENGTH_LONG).show();
            Intent it = new Intent(getApplicationContext(), WelcomePage.class);
            it.putExtra("Email",etEmail.getText().toString());
            it.putExtra("Password", etPassword.getText().toString());
            setResult(100,it);
            finish();
        }
    }

    public void check(View view) {
        checkUser();
    }
}
