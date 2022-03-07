package com.example.numad22sp_xuebai;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class WebServiceActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "WebServiceActivity";

    private ImageView mImageView;
    private Button mRandomButton;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_service);

        mImageView = findViewById(R.id.imageResult);
        mRandomButton = findViewById(R.id.btnRandomWebService);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        mRandomButton.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            // action for About Me button
            case R.id.btnRandomWebService:
                progressBar.setVisibility(View.VISIBLE);
                getRandomDog task = new getRandomDog();
                String url = "https://dog.ceo/api/breeds/image/random";
                task.execute(url);

                break;

        }
    }




    // Google is deprecating Android AsyncTask API in Android 11 and suggesting to use java.util.concurrent
    @SuppressLint("StaticFieldLeak")
    private class getRandomDog extends AsyncTask<String, Integer, JSONObject> {

//        @Override
//        protected void onProgressUpdate(Integer... values) {
////            Log.i(TAG, "Making progress...");
//            progressBar.isShown();
//        }

        @Override
        protected JSONObject doInBackground(String... params) {
            System.out.println("Do in background");
            JSONObject jObject = new JSONObject();
            try {

                URL url = new URL(params[0]);
                // Get String response from the url address
                String resp = NetworkUtil.httpResponse(url);
                //Log.i("resp",resp);
                System.out.println(resp);
                // JSONArray jArray = new JSONArray(resp);    // Use this if your web service returns an array of objects.  Arrays are in [ ] brackets.
                // Transform String into JSONObject
                jObject = new JSONObject(resp);
                System.out.println(resp);
                //Log.i("jTitle",jObject.getString("title"));
                //Log.i("jBody",jObject.getString("body"));
                return jObject;

            } catch (MalformedURLException e) {
                Log.e(TAG, "MalformedURLException");
                e.printStackTrace();
            } catch (ProtocolException e) {
                Log.e(TAG, "ProtocolException");
                e.printStackTrace();
            } catch (IOException e) {
                Log.e(TAG, "IOException");
                e.printStackTrace();
            } catch (JSONException e) {
                Log.e(TAG, "JSONException");
                e.printStackTrace();
            }

            return jObject;
        }

        @Override
        protected void onPostExecute(JSONObject jObject) {
            super.onPostExecute(jObject);

            try {

                String imgURL = jObject.getString("message");
//                System.out.println(imgURL);
//                Picasso.get().setLoggingEnabled(true);
                Picasso.get().load(imgURL).resize(800, 800).into(mImageView);
                progressBar.setVisibility(View.GONE);
            } catch (JSONException e) {
                Toast.makeText(WebServiceActivity.this, "Something went wrong.Try again.", Toast.LENGTH_SHORT).show();
            }

        }
    }

}