package com.example.expandablelistview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.animation.PathInterpolatorCompat;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {

    boolean start = false;
    TextView title;
    TextView category;
    ImageView iv_1,iv_2;
    LinearLayout linearlayout;
    FrameLayout rcv_list_parents;
    FrameLayout icn_search_fl;
    RecyclerView rcv_list;
    public Context ctx;
    Handler handler;
    Runnable runnable;
    private int mOriginalHeight = 0;
    private boolean mIsViewExpanded = false;
    OnViewHolderItemClickListener onViewHolderItemClickListener;
    int currentIdx;
    int lastIdx;
    FrameLayout arrow_fl;
    ImageView arrow_iv;

    int headerHeight;
    int childHeight;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        ctx = itemView.getContext();
        title = itemView.findViewById(R.id.title);
        category = itemView.findViewById(R.id.category);
        linearlayout = itemView.findViewById(R.id.linearlayout);
        icn_search_fl = itemView.findViewById(R.id.icn_search_fl);
        rcv_list_parents = itemView.findViewById(R.id.rcv_list_parents);
        rcv_list = itemView.findViewById(R.id.rcv_list);
        rcv_list.addItemDecoration(new RecyclerDecoration(8, "right"));


        arrow_fl = itemView.findViewById(R.id.arrow_fl);
        arrow_iv = itemView.findViewById(R.id.arrow_iv);
        itemView.setOnClickListener(this);
        Utils_Anim.AlphaAnimCusEase(rcv_list_parents, 0, 0, 0, Utils_Anim.interpolator_easeOut );
        icn_search_fl.bringChildToFront(rcv_list_parents);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                start = true;
                headerHeight = icn_search_fl.getHeight();
                childHeight = rcv_list_parents.getHeight();

                ValueAnimator va;
                mIsViewExpanded = false;
                va = ValueAnimator.ofInt(headerHeight, headerHeight);
                va.setDuration(0);
                va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator animation) {
                        Integer value = (Integer) animation.getAnimatedValue();
                        linearlayout.getLayoutParams().height = value.intValue();
                        linearlayout.requestLayout();
                        linearlayout.invalidate();
                    }
                });
                va.start();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setItemAnim(0, RecyclerVierAdapter.ivArr[0]);
                    }
                }, 100);
            }
        }, 100);

    }

    public void onBind(ItemData itemData, int position, SparseBooleanArray selectedItems){
        title.setText(itemData.getTitle());
        category.setText(itemData.getCategory());
        changeVisibility(selectedItems.get(position));
//        Utils_Anim.AlphaAnimCusEase(rcv_list, 0, 0, 0, Utils_Anim.interpolator_easeOut);
    }

    private void changeVisibility(final boolean isExpanded) {

    }

    @Override
    public void onClick(final View view) {
        ViewHolder holder = (ViewHolder) view.getTag();
        currentIdx = this.getPosition();

        lastIdx = currentIdx;
        setItemAnim(currentIdx, view);
    }

    public void setOnViewHolderItemClickListener(OnViewHolderItemClickListener onViewHolderItemClickListener) {
        this.onViewHolderItemClickListener = onViewHolderItemClickListener;
    }

    public void setItemAnim(int index, View view){
        int durationN = 400;
        Interpolator customInterpolator = PathInterpolatorCompat.create(0.33f,1f,0.68f,1f);

        for (int i = 0; i < RecyclerVierAdapter.ivArr.length; i++) {
            if (i == index){
                if (RecyclerVierAdapter.ivArr[i].getHeight() == headerHeight) {
                    icn_search_fl.setSelected(true);

                    ValueAnimator va;
                    va = ValueAnimator.ofInt(headerHeight, childHeight + headerHeight);
                    va.setInterpolator(customInterpolator);
                    va.setDuration(durationN);

                    int finalI = i;
                    RecyclerVierAdapter.icn_search_flArr[finalI].setSelected(true);

                    Utils_Anim.RotateAnim(RecyclerVierAdapter.arrowArr[finalI], 0, 90, 300, Utils_Anim.interpolator_easeOut );

                    Handler handler = new android.os.Handler();
                    handler.removeCallbacks(runnable);
                    Runnable runnable = new Runnable() {
                        public void run() {
                            Utils_Anim.AlphaAnimCusEase(RecyclerVierAdapter.rcv_list_parentsArr[finalI], 0, 1, 300, Utils_Anim.interpolator_easeOut);
                        }
                    };


                    handler.postDelayed(runnable, 200);

                    va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        public void onAnimationUpdate(ValueAnimator animation) {
                            Integer value = (Integer) animation.getAnimatedValue();
                            view.getLayoutParams().height = value.intValue();
                            view.requestLayout();
                            view.invalidate();
                        }
                    });
                    va.start();
                }
                if (RecyclerVierAdapter.ivArr[i].getHeight() == childHeight+headerHeight) {
                    ValueAnimator va3;
                    va3 = ValueAnimator.ofInt(childHeight+headerHeight, headerHeight);
                    va3.setInterpolator(customInterpolator);
                    va3.setDuration(durationN);
                    int finalI3 = i;
                    RecyclerVierAdapter.icn_search_flArr[finalI3].setSelected(false);
                    Utils_Anim.AlphaAnimCusEase(RecyclerVierAdapter.rcv_list_parentsArr[finalI3], 1, 0, 150, Utils_Anim.interpolator_easeOut );
                    Utils_Anim.RotateAnim(RecyclerVierAdapter.arrowArr[finalI3], 90, 0, 300, Utils_Anim.interpolator_easeOut );
                    va3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        public void onAnimationUpdate(ValueAnimator animation) {
                            Integer value = (Integer) animation.getAnimatedValue();
                            view.getLayoutParams().height = value.intValue();
                            view.requestLayout();
                            view.invalidate();
                        }
                    });
                    va3.start();
                }
            } else {
                if (RecyclerVierAdapter.ivArr[i].getHeight() == childHeight+headerHeight) {
                    ValueAnimator va2;
                    va2 = ValueAnimator.ofInt(childHeight+headerHeight, headerHeight);
                    va2.setInterpolator(customInterpolator);
                    va2.setDuration(durationN);
                    int finalI2 = i;
                    Utils_Anim.AlphaAnimCusEase(RecyclerVierAdapter.rcv_list_parentsArr[finalI2], 1, 0, 150, Utils_Anim.interpolator_easeOut );
                    Utils_Anim.RotateAnim(RecyclerVierAdapter.arrowArr[finalI2], 90, 0, 300, Utils_Anim.interpolator_easeOut );
                    RecyclerVierAdapter.icn_search_flArr[finalI2].setSelected(false);
                    va2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        public void onAnimationUpdate(ValueAnimator animation) {
                            Integer value = (Integer) animation.getAnimatedValue();
                            RecyclerVierAdapter.ivArr[finalI2].getLayoutParams().height = value.intValue();
                            RecyclerVierAdapter.ivArr[finalI2].requestLayout();
                            RecyclerVierAdapter.ivArr[finalI2].invalidate();
                        }
                    });
                    va2.start();
                }
            }
        }
    }
}