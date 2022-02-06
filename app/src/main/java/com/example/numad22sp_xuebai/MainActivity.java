package com.example.numad22sp_xuebai;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // add a btnClicky
        Button btnAboutMe = findViewById(R.id.btnAbout);
        // action onClick to display some text
        btnAboutMe.setOnClickListener(new View.OnClickListener() {
            // abstract class
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Hi! My name is Xue Bai and you can contact me" +
                        "at bai.xue1@northeastern.edu. Nice to meet you!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}