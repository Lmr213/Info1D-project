package com.sailpass.myapplication;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Booking extends AppCompatActivity {

    private TextView dateDeparture;
    private TextView timeDeparture;
    private TextView dateArrival;
    private TextView timeArrival;
    private TextView destination;
    private TextView totalPrice;
    private TextView adultsPrice;
    private TextView childPrice;
    private EditText adults;
    private EditText children;
    private Button cancelBtn;
    private Button payBtn;
    private ImageButton close;
    private int check=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        initialise();

    }

    private void calculateTotal(){
        String price = getIntent().getStringExtra("price");
        String priceChild = getIntent().getStringExtra("priceChild");
        int noOfAdults = Integer.parseInt(adults.getText().toString());
        int noOfChildren = Integer.parseInt(children.getText().toString());
        double priceAdultTicket = Double.parseDouble(price);
        double priceChildTicket = Double.parseDouble(priceChild);
        double total = (noOfAdults*priceAdultTicket) + (noOfChildren*priceChildTicket);
        if(total!=0){
            check = 1;
        }
        totalPrice.setText("SGD $"+total);
    }

    private void initialise(){
        String departure = getIntent().getStringExtra("departure");
        String destinationName= getIntent().getStringExtra("destination");
        String price = getIntent().getStringExtra("price");
        String priceChild = getIntent().getStringExtra("priceChild");
        String arrival = getIntent().getStringExtra("arrival");
        String timeArrive = getIntent().getStringExtra("arrivalTime");
        String timeDepart = getIntent().getStringExtra("departureTime");
        adultsPrice = findViewById(R.id.adultTicketPrice);
        childPrice = findViewById(R.id.priceChildTickets);
        adultsPrice.setText("SGD $"+ price);
        childPrice.setText("SGD $"+ priceChild);
        dateDeparture = findViewById(R.id.dateDeparture);
        dateDeparture.setText("Date of Departure: "+departure);
        timeDeparture = findViewById(R.id.timeDepart);
        timeDeparture.setText(timeDepart);
        dateArrival = findViewById(R.id.dateArrive);
        dateArrival.setText("Date of Arrival: "+ arrival);
        timeArrival = findViewById(R.id.timeArrive);
        timeArrival.setText(timeArrive);
        destination = findViewById(R.id.destination);
        destination.setText("Singapore, Singapore ->"+destinationName);
        totalPrice = findViewById(R.id.total);
        adults = (EditText) findViewById(R.id.adultTickets);
        adults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateTotal();
            }
        });
        children = (EditText) findViewById(R.id.childTickets);
        children.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateTotal();
            }
        });
        cancelBtn = findViewById(R.id.cancel_button);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        payBtn = findViewById(R.id.payBtn);
        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(check==1){
                    check = 0;
                    Toast.makeText(Booking.this, "Book successfully.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Booking.this,home.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Booking.this, "Haven't purchase any tickets.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        close = findViewById(R.id.confirm_close);
        close.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Booking.this,Search.class);
                startActivity(intent);
            }
        });
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


}

