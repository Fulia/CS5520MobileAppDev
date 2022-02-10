package com.example.numad22sp_xuebai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // button About Me
        Button btnAboutMe = findViewById(R.id.btnAbout);
        // button Clicky Clicky
        Button btnClicky = findViewById(R.id.btnClicky);

        // action onClick to display some text
        btnAboutMe.setOnClickListener(this);
        btnClicky.setOnClickListener(this);

//        replaced by the switch
//        btnAboutMe.setOnClickListener(new View.OnClickListener() {
//            // abstract class
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "Hi! My name is Xue Bai and you can contact me" +
//                        "at bai.xue1@northeastern.edu. Nice to meet you!", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAbout:
                Intent itnt = new Intent(this, AboutMeActivity.class);
                startActivity(itnt);
//                Toast.makeText(MainActivity.this, "Hi! My name is Xue Bai and you can contact me" +
//                        "at bai.xue1@northeastern.edu. Nice to meet you!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnClicky:
                // start a new activity after clicking a button
                // Intent: an abstract description of an operation to be performed.
                Intent intent = new Intent(this, ClickyActivity.class);
                startActivity(intent);

        }
    }
}