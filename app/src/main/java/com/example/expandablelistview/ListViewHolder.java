package com.example.expandablelistview;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

class ListViewHolder extends RecyclerView.ViewHolder {

    public ImageView list_item_iv;

    public ListViewHolder(View v) {
        super(v);
        list_item_iv = (ImageView) v.findViewById(R.id.list_item_iv);
    }

}