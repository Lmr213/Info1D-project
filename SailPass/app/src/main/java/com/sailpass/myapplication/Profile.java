package com.sailpass.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Profile extends AppCompatActivity {
    private ImageButton prev;
    private ImageButton log_out;
    private TextView account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
        String name = sp.getString("loginUserName", "");
        account = findViewById(R.id.user_name);
        account.setText(name);
        prev = findViewById(R.id.btn_previous);
        prev.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this,home.class);
                startActivity(intent);
            }
        });
        log_out = findViewById(R.id.btn_log_out);
        log_out.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}