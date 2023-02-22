package com.example.expandablelistview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListViewHolder> {

    private ArrayList<ListData> listDatas;

    public void setData(ArrayList<ListData> list){
        listDatas = list;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // 사용할 아이템의 뷰를 생성해준다.
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_recycler_items, parent, false);

        ListViewHolder holder = new ListViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        ListData data = listDatas.get(position);

        holder.list_item_iv.setImageResource(data.getImg());
    }

    @Override
    public int getItemCount() {
        return listDatas.size();
    }
}