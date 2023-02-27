package com.example.expandablelistview;
import android.content.Context;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerVierAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<ItemData> listData = new ArrayList<>();
    static LinearLayout[] ivArr = new LinearLayout[5];
    static FrameLayout[] rcv_list_parentsArr = new FrameLayout[5];
    static FrameLayout[] icn_search_flArr = new FrameLayout[5];
    static FrameLayout[] arrowArr = new FrameLayout[5];
    private SparseBooleanArray selectedItems = new SparseBooleanArray();
    private int prePosition = -1;
    Context ctx;

    private RecyclerView mVerticalView;
    private ListAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private int MAX_ITEM_COUNT = 4;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        ctx = v.getContext();

        mVerticalView = (RecyclerView) v.findViewById(R.id.rcv_list);
        ArrayList<ListData> data = new ArrayList<>();
        int i = 0;
        while (i < MAX_ITEM_COUNT) {
            switch (i){
                case 0:
                    data.add(new ListData(R.drawable.item1));
                    break;
                case 1:
                    data.add(new ListData(R.drawable.item2));
                    break;
                case 2:
                    data.add(new ListData(R.drawable.item3));
                    break;
                case 3:
                    data.add(new ListData(R.drawable.item4));
                    break;
            }
            i++;
        }

        mLayoutManager = new LinearLayoutManager(ctx);
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        mVerticalView.setLayoutManager(mLayoutManager);
        mAdapter = new ListAdapter();
        mAdapter.setData(data);
        mVerticalView.setAdapter(mAdapter);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.onBind(listData.get(position), position, selectedItems);
        ivArr[position] = ((ViewHolder) holder).linearlayout;
        icn_search_flArr[position] = ((ViewHolder) holder).icn_search_fl;
        arrowArr[position] = ((ViewHolder) holder).arrow_fl;
        rcv_list_parentsArr[position] = ((ViewHolder) holder).rcv_list_parents;
        for (int i = 0; i < ivArr.length; i++) {
            ivArr[position].setId(i);
        }
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    void addItem(ItemData itemData) {
        listData.add(itemData);
    }
}
