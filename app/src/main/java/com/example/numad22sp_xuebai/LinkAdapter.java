package com.example.numad22sp_xuebai;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

// adapter: to bind data from data set to views
public class LinkAdapter extends RecyclerView.Adapter<LinkAdapter.LinkViewHolder> {
    private ArrayList<Link> linkArrayList;

    public LinkAdapter(ArrayList<Link> linkList){
        linkArrayList = linkList;
    }


    // to hold the link_card view
    public static class LinkViewHolder extends RecyclerView.ViewHolder{
        public TextView nameView;
        public TextView urlView;
        public LinkViewHolder(View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.linkName);
            urlView = itemView.findViewById(R.id.linkURL);
        }
    }


    @Override
    public LinkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.link_card, parent, false);
        LinkViewHolder lvh = new LinkViewHolder(view);
        return lvh;
    }

    // pass data to the view in holder through the binding action
    @Override
    public void onBindViewHolder( LinkAdapter.LinkViewHolder holder, int position) {
        Link currentLink = linkArrayList.get(position);
        holder.nameView.setText(currentLink.getLinkName());
        holder.urlView.setText(currentLink.getUrl());

    }

    @Override
    public int getItemCount() {
        return linkArrayList.size();
    }


}
