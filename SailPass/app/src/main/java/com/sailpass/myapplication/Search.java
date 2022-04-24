package com.sailpass.myapplication;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Search extends AppCompatActivity implements SearchAdapter.OnBookListener {

    private SearchView searchBar;
    private SearchView searchDeparture;
    private SearchView searchArrival;
    private RecyclerView recyclerView;
    private SearchAdapter searchAdapter;
    private Model.DataSource dataSource;
    private List<Model.Ferry> ferryList;
    private ImageButton close;
    private Button op_db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        // ferryList should extract data from database and put it into an array as a datasource
        ferryList = new ArrayList<>();


        ferryList.add(new Model.Ferry("Batam, Indonesia", "" + 32.0,""+28.0 ,"29-04-2022", "09:00", "28-04-2022","23:30"));
        ferryList.add(new Model.Ferry("Penang, Malaysia", "" + 24.0,""+20.0 ,"26-04-2022","04:00", "25-04-2022", "22:00"));
        ferryList.add(new Model.Ferry("Penang, Malaysia", "" + 25.0,""+21.0 ,"25-04-2022", "17:00","25-04-2022","11:00"));


        dataSource = new Model.DataSource(ferryList);

        recyclerView = findViewById(R.id.searchResults);
        createSearchBar();
        render();

        op_db = findViewById(R.id.opdb_btn);
        op_db.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Search.this, OpDbActivity.class);
                startActivity(intent);
            }
        });


    }

    private void createSearchBar() {
        searchBar = findViewById(R.id.searchBar);
        searchBar.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                searchAdapter.getFilter().filter(s);
                return false;
            }
        });
        searchDeparture = findViewById(R.id.editDepartureDate);
        searchDeparture.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                searchAdapter.getDepartureFilter().filter(s);
                return false;
            }
        });
        searchArrival = findViewById(R.id.editArrivalDate);
        searchArrival.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                searchAdapter.getArrivalFilter().filter(s);
                return false;
            }
        });
        close = findViewById(R.id.search_close);
        close.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Search.this,home.class);
                startActivity(intent);
            }
        });

    }

    private void render() {
        searchAdapter = new SearchAdapter(Search.this, dataSource, this);
        recyclerView.setAdapter(searchAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Search.this));

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBtnClick(int position){
        // Pass data of clicked button to booking screen
        Intent intent = new Intent(this, Booking.class);
        intent.putExtra("departure",dataSource.data.get(position).getDateDeparture());
        intent.putExtra("departureTime",dataSource.data.get(position).getTimeDeparture());
        intent.putExtra("arrival",dataSource.data.get(position).getDateArrival());
        intent.putExtra("arrivalTime",dataSource.data.get(position).getTimeArrival());
        intent.putExtra("destination",dataSource.data.get(position).getDestination());
        intent.putExtra("price",dataSource.data.get(position).getPrice());
        intent.putExtra("priceChild",dataSource.data.get(position).getPriceChild());
        startActivity(intent);
    }
}