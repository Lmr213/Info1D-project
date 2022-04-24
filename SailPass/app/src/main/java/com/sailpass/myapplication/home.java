package com.sailpass.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.*;

public class home extends AppCompatActivity {
    private SearchView searchBar;
    private ImageButton list;
    private ImageButton profile;
    private ImageButton QR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        list = findViewById(R.id.go_list);
        list.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(home.this,checklist.class);
                startActivity(intent);
            }
        });

        profile = findViewById(R.id.go_profile);
        profile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(home.this,Profile.class);
                startActivity(intent);
            }
        });
        QR = findViewById(R.id.go_QR);
        QR.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(home.this,boardingpass.class);
                startActivity(intent);
            }
        });

        searchBar = findViewById(R.id.searchBar);
        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String s) {
                toSearchPage();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                toSearchPage();
                return true;
            }
        });

        searchBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toSearchPage();
            }
        });

    }
    private void toSearchPage(){
        Intent openSearchPage = new Intent(home.this, Search.class);
        startActivity(openSearchPage);
    }
}