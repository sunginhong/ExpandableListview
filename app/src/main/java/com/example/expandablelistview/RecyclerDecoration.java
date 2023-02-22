package com.example.expandablelistview;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class RecyclerDecoration extends RecyclerView.ItemDecoration {
    private int padding;
    private String pos;

    public RecyclerDecoration(int n, String posn){
        padding = n;
        pos = posn;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (pos == "top"){
            outRect.top = padding;
        }
        if (pos == "right"){
            outRect.right = padding;
        }
    }
}