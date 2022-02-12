package com.example.numad22sp_xuebai;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class LinkCollectorActivity extends AppCompatActivity implements LinkInputDialog.LinkDialogListener, LinkAdapter.linkClickListener {
    private RecyclerView recyclerView; // xml in layout
    // adapter:bridge between the data (arraylist) and the recyclerView
    // performance improve
    private RecyclerView.Adapter adapter;
    // preset layout to align items
    private RecyclerView.LayoutManager layoutManager;
    // an arraylist to store the link items
    private ArrayList<Link> linkArrayList;
    // floating button
    private Button floatButton;
    private View v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_collector);

        createLinkList();
        buildRecyclerView();
        v = findViewById(R.id.linkCollectorLayout);

        floatButton = findViewById(R.id.addLinkBtn);
        // clicking on the add link button will open the dialog for input
        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });

    }

    private void openDialog() {
        LinkInputDialog inputDialog = new LinkInputDialog();
        inputDialog.show(getSupportFragmentManager(), "link input dialog");
    }


    private void createLinkList() {
        linkArrayList = new ArrayList<>();
//        linkArrayList.add(new Link("test1", "url1"));
//        linkArrayList.add(new Link("test2", "url2"));
//        linkArrayList.add(new Link("test3", "url3"));
    }

    private void buildRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        // no matter how many items in the list, set to fixed size to improve performance
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new LinkAdapter(linkArrayList, this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }


    @Override
    public void applyInputs(String Name, String URL) {
        // check if the input url is valid and reachable
        if (URLUtil.isValidUrl(URL)) {
            Link newLink = new Link(Name, URL);
//            Toast.makeText(this, "new link created", Toast.LENGTH_SHORT).show();
            linkArrayList.add(newLink);
            adapter.notifyDataSetChanged();
            showSnackbar(true);
            // notify + success notice
        }else{
            showSnackbar(false);
//            Toast.makeText(this, "Invalid url", Toast.LENGTH_SHORT).show();
        }
    }


//    public boolean urlValidation(String urlString) {
////        try{
////            URL url = new URL(urlString);
////            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
////            connection.setRequestMethod("GET");
////            connection.setConnectTimeout(3000);
////            connection.connect();
////
////            int code = connection.getResponseCode();
////            return code == 200;
////
////        } catch (IOException e){
////            return false;
////        }
//        return URLUtil.isValidUrl(urlString);
//
//    }


    // show different snackbars per validation result of input URL
    public void showSnackbar(boolean succeeded){
        if (succeeded){
            Snackbar snkbar1 = Snackbar.make(v, "A new link was successfully added!", Snackbar.LENGTH_LONG);
            snkbar1.show();
        }else{
            Snackbar snkbar2 = Snackbar.make(v, "Invalid URL. Try again.", Snackbar.LENGTH_LONG);
            snkbar2.show();
        }
    }

    @Override
    public void linkOnClick(int position) {
        Link link = linkArrayList.get(position);
        Uri uri = Uri.parse(link.getUrl());
        // open the url from the clicked link
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}