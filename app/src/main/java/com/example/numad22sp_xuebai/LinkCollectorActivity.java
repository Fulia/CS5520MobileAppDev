package com.example.numad22sp_xuebai;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class LinkCollectorActivity extends AppCompatActivity implements LinkInputDialog.LinkDialogListener {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_collector);

        createLinkList();
        buildRecyclerView();

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
        adapter = new LinkAdapter(linkArrayList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }


    @Override
    public void applyInputs(String Name, String URL) {
        if (urlValidation(URL)) {
            Link newLink = new Link(Name, URL);
            linkArrayList.add(newLink);
            adapter.notifyDataSetChanged();
            // notify + success notice
        }else{

        }
    }

    // check if the input url is valid and reachable
    public boolean urlValidation(String urlString) {
        try{
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(3000);
            connection.connect();

            int code = connection.getResponseCode();
            return code == 200;

        } catch (IOException e){
            return false;
        }

    }
}