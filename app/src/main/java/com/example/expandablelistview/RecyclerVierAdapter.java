package com.example.expandablelistview;
import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerVierAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // adapter에 들어갈 list 입니다.
    private ArrayList<ItemData> listData = new ArrayList<>();

    // Item의 클릭 상태를 저장할 array 객체
    private SparseBooleanArray selectedItems = new SparseBooleanArray();
    // 직전에 클릭됐던 Item의 position
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
        viewHolder.setOnViewHolderItemClickListener(new OnViewHolderItemClickListener() {
            @Override
            public void onViewHolderItemClick() {
                if (selectedItems.get(position)) {
                    // 펼쳐진 Item을 클릭 시
                    selectedItems.delete(position);
                } else {
                    // 직전의 클릭됐던 Item의 클릭상태를 지움
                    selectedItems.delete(prePosition);
                    // 클릭한 Item의 position을 저장
                    selectedItems.put(position, true);
                }
                // 해당 포지션의 변화를 알림
                if (prePosition != -1) notifyItemChanged(prePosition);
                notifyItemChanged(position);
                // 클릭된 position 저장
                prePosition = position;
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    void addItem(ItemData itemData) {
        // 외부에서 item을 추가시킬 함수입니다.
        listData.add(itemData);
    }
}
