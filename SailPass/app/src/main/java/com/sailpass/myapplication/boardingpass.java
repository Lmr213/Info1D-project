package com.sailpass.myapplication;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.nfc.tech.NfcBarcode;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class boardingpass extends AppCompatActivity {
    //Initialize variable
    ImageView ivOutput;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boardingpass);

        //Assign variable
        String QR = "Thank you:)";
        ivOutput = findViewById(R.id.iv_output);
        //Get input value from edit text
        String sText = QR;
        //Initialize multi format writer
        MultiFormatWriter writer = new MultiFormatWriter();
        try {
            //Initialize bit matrix
            BitMatrix matrix = writer.encode(sText, BarcodeFormat.QR_CODE, 350,350);
            //Initialize barcode encoder
            BarcodeEncoder encoder = new BarcodeEncoder();
            //Initialize bitmap
            Bitmap bitmap = encoder.createBitmap(matrix);
            //Set bitmap on image view
            ivOutput.setImageBitmap(bitmap);
            //Initialize input manager
            //InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            //Hide doft keyboard
            //manager.hideSoftInputFromWindow(etInput.getApplicationWindowToken(),0);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        back = findViewById(R.id.pass_close);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(boardingpass.this,home.class);
                startActivity(intent);
            }
        });
    }
}