package com.example.numad22sp_xuebai;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationRequest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FusedLocationProviderClient fusedLocationProviderClient;
    ArrayList<String> coordinates = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        // button About Me
        Button btnAboutMe = findViewById(R.id.btnAbout);
        // button Clicky Clicky
        Button btnClicky = findViewById(R.id.btnClicky);
        // button Link Collector
        Button btnLink = findViewById(R.id.btnLinkCollector);

        Button btnLocation = findViewById(R.id.btnLocation);

        // onClick listeners for each button
        btnAboutMe.setOnClickListener(this);
        btnClicky.setOnClickListener(this);
        btnLink.setOnClickListener(this);
        btnLocation.setOnClickListener(this);


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

    @RequiresApi(api = 31)
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            // action for About Me button
            case R.id.btnAbout:
                Intent itnt = new Intent(this, AboutMeActivity.class);
                startActivity(itnt);
//                Toast.makeText(MainActivity.this, "Hi! My name is Xue Bai and you can contact me" +
//                        "at bai.xue1@northeastern.edu. Nice to meet you!", Toast.LENGTH_SHORT).show();
                break;
            // action for Clicky Clicky button
            case R.id.btnClicky:
                // start a new activity after clicking a button
                // Intent: an abstract description of an operation to be performed.
                Intent intent = new Intent(this, ClickyActivity.class);
                startActivity(intent);
                break;
            // action for Link Collector button
            case R.id.btnLinkCollector:
                Intent intent2 = new Intent(this, LinkCollectorActivity.class);
                startActivity(intent2);
                break;
            // action for getting current location
            case R.id.btnLocation:
                // request permission and return location
                getLocation();
                System.out.println("coordinates:" + coordinates);
                if (coordinates != null && coordinates.size() >0) {
                    Intent intent3 = new Intent(this, ShowLocation.class);
                    intent3.putStringArrayListExtra("Location", coordinates);
                    startActivity(intent3);
                }
                break;


        }
    }

    // https://developer.android.com/training/permissions/requesting#:~:text=And%20this%20code%20snippet%20demonstrates%20the%20recommended%20process%20of%20checking%20for%20a%20permission%2C%20and%20requesting%20a%20permission%20from%20the%20user%20when%20necessary%3A
    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                System.out.println("isGranted: " + isGranted);
                if (isGranted != null && !isGranted){
                    // Explain to the user that the feature is unavailable because the
                    // features requires a permission that the user has denied. At the
                    // same time, respect the user's decision. Don't link to system
                    // settings in an effort to convince the user to change their
                    // decision.
                    Snackbar snkbar = Snackbar.make(findViewById(R.id.mainLayout),
                            "To show the location, permission to access related data will need to be allowed.",
                            Snackbar.LENGTH_LONG);
                    snkbar.show();

                }
            });

    @RequiresApi(api = 31)
    private void getLocation() {

        // check permission first
        // if no permission, ask for user's permission
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED){
            // You can directly ask for the permission.
            // The registered ActivityResultCallback gets the result of this request.
            requestPermissionLauncher.launch(
                    Manifest.permission.ACCESS_FINE_LOCATION);

        }
        if(ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) {
            // one of the situations where null is return (location)
//            if(isGPSEnabled()){
            // public static final int PRIORITY_BALANCED_POWER_ACCURACY constant value: 102
            // https://developers.google.com/android/reference/com/google/android/gms/location/LocationRequest#public-static-final-int-priority_balanced_power_accuracy
            fusedLocationProviderClient.getCurrentLocation(
                    LocationRequest.QUALITY_HIGH_ACCURACY,
                    new CancellationTokenSource().getToken())
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // null situations in getLastL https://developer.android.com/training/location/retrieve-current#:~:text=The%20getLastLocation()%20method%20returns,see%20Receiving%20Location%20Updates.
                            if (location != null) {
                                // store latitude and longitude into the String Arraylist coordinates
                                coordinates.add(String.valueOf(location.getLatitude()));
                                coordinates.add(String.valueOf(location.getLongitude()));
                            }
                        }
                    });
        }

    }

//    private boolean isGPSEnabled() {
//    }

}