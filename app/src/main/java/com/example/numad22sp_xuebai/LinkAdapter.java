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
    private LinkClickListener linkClickLstr;

    public LinkAdapter(ArrayList<Link> linkList, LinkClickListener listener){
        linkArrayList = linkList;
        linkClickLstr = listener;
    }


    // create holder to hold the link_card view
    public static class LinkViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView nameView;
        private TextView urlView;
        private LinkClickListener linkClickListener;

        public LinkViewHolder(View itemView, LinkClickListener linkClickListener) {
            super(itemView);
            nameView = itemView.findViewById(R.id.linkName);
            urlView = itemView.findViewById(R.id.linkURL);
            this.linkClickListener = linkClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            // get the position of the clicked view by getAdapterPosition()
            linkClickListener.linkOnClick(getAdapterPosition());
        }
    }


    @Override
    public LinkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // onClick listener is for link_card view, so the onclicklistener should be implemented here not in Link
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.link_card, parent, false);
        LinkViewHolder lvh = new LinkViewHolder(view, linkClickLstr);
        return lvh;
    }

    // pass data to the view in holder through the binding action
    @Override
    public void onBindViewHolder(LinkAdapter.LinkViewHolder holder, int position) {
        Link currentLink = linkArrayList.get(position);
        holder.nameView.setText(currentLink.getLinkName());
        holder.urlView.setText(currentLink.getUrl());

    }

    @Override
    public int getItemCount() {
        return linkArrayList.size();
    }

    public interface LinkClickListener {
        void linkOnClick(int position);
    }


}
