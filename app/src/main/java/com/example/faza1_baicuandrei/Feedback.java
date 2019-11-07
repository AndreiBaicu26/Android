package com.example.faza1_baicuandrei;

import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Feedback extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        send();
    }

    private void send() {
        Button btnSend = (Button)findViewById(R.id.btnSend);
        final EditText emailTxt = (EditText)findViewById(R.id.emailID);
        final EditText subjectTxt = (EditText)findViewById(R.id.subjectID);
        final EditText messageTxt = (EditText)findViewById(R.id.messageID);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Intent.ACTION_SEND);
                it.putExtra(Intent.EXTRA_EMAIL,emailTxt.getText().toString());
                it.putExtra(Intent.EXTRA_SUBJECT, subjectTxt.getText().toString());
                it.putExtra(Intent.EXTRA_TEXT, messageTxt.getText().toString());


                startActivity(Intent.createChooser(it,"Choose your email"));
            }
        });
    }
}
