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
    OnViewHolderItemClickListener onViewHolderItemClickListener;

    FrameLayout arrow_fl;
    ImageView arrow_iv;

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

//        rcv_list_parents.setVisibility(View.GONE);
        Utils_Anim.AlphaAnimCusEase(rcv_list, 0, 0, 0, Utils_Anim.interpolator_easeOut );
        icn_search_fl.bringChildToFront(rcv_list_parents);
//        icn_search_fl.bringToFront();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                start = true;
                rcv_list_parents.setVisibility(View.VISIBLE);
                Utils_Anim.AlphaAnimCusEase(rcv_list, 0, 0, 0, Utils_Anim.interpolator_easeOut );
            }
        }, 200);

        linearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (start){
                    handler.removeCallbacks(runnable);
                    onViewHolderItemClickListener.onViewHolderItemClick();
                    Utils_Anim.AlphaAnimCusEase(rcv_list, 0, 0, 0, Utils_Anim.interpolator_easeOut );
                }
            }
        });
    }

    public void onBind(ItemData itemData, int position, SparseBooleanArray selectedItems){
        title.setText(itemData.getTitle());
        category.setText(itemData.getCategory());
        changeVisibility(selectedItems.get(position));
        Utils_Anim.AlphaAnimCusEase(rcv_list, 0, 0, 0, Utils_Anim.interpolator_easeOut);
    }

    private void changeVisibility(final boolean isExpanded) {
//        ValueAnimator va = isExpanded ? ValueAnimator.ofInt(0, 500) : ValueAnimator.ofInt(500, 0);
        int durationN = 300;
        Interpolator customInterpolator = PathInterpolatorCompat.create(0.33f,1f,0.68f,1f);

//        va.setInterpolator(customInterpolator);
//        va.setDuration(durationN);

        ValueAnimator va;
        if (isExpanded) {
            va = ValueAnimator.ofInt(0, 500);
        } else {
            va = ValueAnimator.ofInt(500, 0);
        }
        va.setInterpolator(customInterpolator);
        va.setDuration(durationN);

        runnable = new Runnable() {
            public void run() {
                if (isExpanded) {
                    Utils_Anim.AlphaAnimCusEase(rcv_list, 0, 1, durationN-100, Utils_Anim.interpolator_easeOut);
                }
            }
        };

        handler = new android.os.Handler();
        if (isExpanded){
            icn_search_fl.setSelected(true);
            Utils_Anim.AlphaAnimCusEase(rcv_list, 0, 0, 0, Utils_Anim.interpolator_easeOut );
            Utils_Anim.RotateAnim(arrow_iv, 0, 90, 300, Utils_Anim.interpolator_easeOut );
            handler.postDelayed(runnable, durationN);
        } else {
            icn_search_fl.setSelected(false);
            Utils_Anim.AlphaAnimCusEase(rcv_list, 0, 0, 0, Utils_Anim.interpolator_easeOut );
            Utils_Anim.RotateAnim(arrow_iv, 90, 0, 300, Utils_Anim.interpolator_easeOut );
            handler.removeCallbacks(runnable);
        }
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // imageView의 높이 변경
                rcv_list_parents.getLayoutParams().height = (int) animation.getAnimatedValue();
                rcv_list_parents.requestLayout();
//                rcv_list_parents.setVisibility(isExpanded ? View.VISIBLE : View.GONE);

                if (!isExpanded) {
                    Utils_Anim.AlphaAnimCusEase(rcv_list, 0, 0, 0, Utils_Anim.interpolator_easeOut );
//                    Utils_Anim.AlphaAnimCusEase(rcv_list, 0, 0, 0, Utils_Anim.interpolator_easeOut );
                } else {
                    rcv_list_parents.setVisibility(View.VISIBLE);
                }
                itemView.invalidate();
            }
        });
        va.start();
    }

    @Override
    public void onClick(final View view) {
//        if (mOriginalHeight == 0) {
//            mOriginalHeight = view.getHeight();
//        }
//        ValueAnimator va;
//        if (!mIsViewExpanded) {
//            mIsViewExpanded = true;
//            va = ValueAnimator.ofInt(0, 500);
//        } else {
//            mIsViewExpanded = false;
//            va = ValueAnimator.ofInt(500, 0);
//        }
//        Interpolator customInterpolator = PathInterpolatorCompat.create(0.33f,1f,0.68f,1f);
//        va.setInterpolator(customInterpolator);
//        va.setDuration(1000);
//        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            public void onAnimationUpdate(ValueAnimator animation) {
////                Integer value = (Integer) animation.getAnimatedValue();
////                view.getLayoutParams().height = value.intValue();
////                view.requestLayout();
////                itemView.invalidate();
//
//                rcv_list_parents.getLayoutParams().height = (int) animation.getAnimatedValue();
//                rcv_list_parents.requestLayout();
//                // imageView가 실제로 사라지게하는 부분
//                rcv_list_parents.setVisibility(mIsViewExpanded ? View.VISIBLE : View.GONE);
////                Utils_Anim.AlphaAnimCusEase(rcv_list, 0, 0, 0, Utils_Anim.interpolator_easeOut );
//
//                itemView.invalidate();
//            }
//        });
//        va.start();

    }

    public void setOnViewHolderItemClickListener(OnViewHolderItemClickListener onViewHolderItemClickListener) {
        this.onViewHolderItemClickListener = onViewHolderItemClickListener;
    }
}