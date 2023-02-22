package com.example.expandablelistview;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public Context ctx;
    RecyclerVierAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        getData();
    }

    private void init(){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new RecyclerDecoration(6, "top"));
        adapter = new RecyclerVierAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void getData(){
        ItemData itemData = new ItemData("금리인상", "경제");
        adapter.addItem(itemData);
        itemData = new ItemData("이상한변호사우영우", "드라마");
        adapter.addItem(itemData);
        itemData = new ItemData("창문형에어컨", "리빙");
        adapter.addItem(itemData);
        itemData = new ItemData("프로야구 예매", "스포츠");
        adapter.addItem(itemData);
        itemData = new ItemData("2023 투싼 하이브리드", "자동차");
        adapter.addItem(itemData);
    }
}