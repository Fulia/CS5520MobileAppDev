package com.example.numad22sp_xuebai;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

// implements interface View.onClickListener first then use switch to implement onClick method
public class ClickyActivity extends AppCompatActivity implements View.OnClickListener {
    TextView pressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicky);

        pressed = findViewById(R.id.txtPressed);
        Button btnA = findViewById(R.id.btnA);
        Button btnB = findViewById(R.id.btnB);
        Button btnC = findViewById(R.id.btnC);
        Button btnD = findViewById(R.id.btnD);
        Button btnE = findViewById(R.id.btnE);
        Button btnF = findViewById(R.id.btnF);


        btnA.setOnClickListener(this);
        btnB.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnD.setOnClickListener(this);
        btnE.setOnClickListener(this);
        btnF.setOnClickListener(this);


    }

    // the textview will show different content per the button pressed
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnA:
                pressed.setText("Pressed: A");
                break;
            case R.id.btnB:
                pressed.setText("Pressed: B");
                break;
            case R.id.btnC:
                pressed.setText("Pressed: C");
                break;
            case R.id.btnD:
                pressed.setText("Pressed: D");
                break;
            case R.id.btnE:
                pressed.setText("Pressed: E");
                break;
            case R.id.btnF:
                pressed.setText("Pressed: F");
                break;
            default:
                pressed.setText("Pressed: -");
        }

    }
}