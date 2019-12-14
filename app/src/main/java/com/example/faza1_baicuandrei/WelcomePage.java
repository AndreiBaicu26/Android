package com.example.faza1_baicuandrei;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;


import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class WelcomePage extends AppCompatActivity {

    SharedPreferences shPref;
    Switch switchDark;
    EditText etEmail;
    EditText etPassword;
    Button btnLogIn;
    Button btnSignUp;
    Button btnGoToMain;
    String email;
    String password;
    AppDatabase database;
    User user;
    Boolean connected;
    Boolean isDark;
    SharedPreferences.Editor editor = null;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        shPref = getSharedPreferences("DarkMode", MODE_PRIVATE);
        loadSharedPreference(this);
        loadSharedPrefForUser(this);
        editor = shPref.edit();

        if (isDark == true) {
            setTheme(R.style.DarkTheme);
        } else {
            setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        database = Room.databaseBuilder(this, AppDatabase.class, "FavouriteCountries").allowMainThreadQueries().build();
        switchDark = (Switch) findViewById(R.id.switchDark);

        if (isDark == true) {
            switchDark.setChecked(true);
        } else
            switchDark.setChecked(false);


        btnGoToMain = findViewById(R.id.buttonMainPage);
        etEmail = findViewById(R.id.editTextEmail);
        etPassword = findViewById(R.id.editTextPassword);

        etEmail.setText(email);
        etPassword.setText(password);


        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                  btnGoToMain.setVisibility(View.INVISIBLE);
                  connected = false;
                  saveSharedPrefForUser();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                btnGoToMain.setVisibility(View.INVISIBLE);
                connected = false;
                saveSharedPrefForUser();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        switchDark.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    isDark = true;
                    saveSharedPreference(true);
                    recreate();
                } else {
                    isDark = false;
                    saveSharedPreference(false);

                    recreate();
                }
            }
        });

        if(connected == true){
            btnGoToMain.setVisibility(View.VISIBLE);
        }
        else
        {
            btnGoToMain.setVisibility(View.INVISIBLE);
        }


    }

    public void saveSharedPreference(Boolean isDark) {
        editor.putBoolean("Dark", isDark);

        editor.commit();

    }

    public void loadSharedPreference(Context context) {
        isDark = shPref.getBoolean("Dark", false);
    }

    public void saveSharedPrefForUser() {
        editor.putString("email", etEmail.getText().toString());
        editor.putString("password", etPassword.getText().toString());
        editor.putBoolean("connected", connected);
        editor.commit();
    }



    public void loadSharedPrefForUser(Context context) {
        email = shPref.getString("email", "");
        password = shPref.getString("password", "");
        connected = shPref.getBoolean("connected", false);
    }

    public void goToRateUs(View view) {
        Intent it = new Intent(getApplicationContext(), Rate_Us_Fragments.class);
        it.putExtra("isDark", switchDark.isChecked());
        startActivity(it);
    }

    public void goToMain(View view) {
        Intent it = new Intent(this, MainActivity.class);
        Bundle extras = new Bundle();
        extras.putParcelable("user", user);
        extras.putBoolean("isDark", switchDark.isChecked());
        it.putExtras(extras);
        startActivity(it);
    }

    public void signUpUser(View view) {
        Intent it = new Intent(getApplicationContext(), SignUpForm.class);
        it.putExtra("isDark", switchDark.isChecked());
        startActivityForResult(it, 200);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 200) {
            etEmail.setText(data.getStringExtra("Email"));
            etPassword.setText((data.getStringExtra("Password")));
        }
    }

    public void logInUser(View view) {
        if (etPassword.getText().toString().length() > 0 && etEmail.getText().toString().length() > 0) {
            user = database.userDao().checkIfUserExists(etEmail.getText().toString(), etPassword.getText().toString());
            if (user != null) {

                Toast.makeText(getApplicationContext(), "welcome " + user.getEmail(), Toast.LENGTH_SHORT).show();
                connected = true;
                btnGoToMain.setVisibility(View.VISIBLE);
                saveSharedPrefForUser();

            } else {
                connected = false;
                btnGoToMain.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(), "Wrong email or password ", Toast.LENGTH_SHORT).show();
                saveSharedPrefForUser();
            }
        } else {

            connected = false;
            btnGoToMain.setVisibility(View.INVISIBLE);
            Toast.makeText(getApplicationContext(), "Please fill both fields", Toast.LENGTH_SHORT).show();
            saveSharedPrefForUser();
        }
    }

    @Override
    protected void onDestroy() {
        saveSharedPreference(isDark);
        saveSharedPrefForUser();
        super.onDestroy();
    }
}
