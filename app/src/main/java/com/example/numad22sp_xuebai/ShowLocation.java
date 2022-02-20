package com.example.numad22sp_xuebai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class ShowLocation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // make sure the layout matches one in res/layout files
        setContentView(R.layout.activity_location);
        TextView latitude = findViewById(R.id.textLatitude);
        TextView longitude = findViewById(R.id.textLongitude);
        Intent intent = getIntent();
        System.out.println("has location: "+ intent.hasExtra("Location") + intent.getStringArrayListExtra("Location"));
        ArrayList<String> coordinates = intent.getStringArrayListExtra("Location");
        latitude.setText(coordinates.get(0));
        longitude.setText(coordinates.get(1));

    }
}