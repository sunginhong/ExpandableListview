package com.example.expandablelistview;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.Property;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.RotateAnimation;

import androidx.core.view.animation.PathInterpolatorCompat;

public class Utils_Anim {
    protected Context context;

    public static Interpolator interpolator_easeInOut = PathInterpolatorCompat.create(0.65f, 0f, 0.35f, 1f);
    public static Interpolator interpolator_easeOut = PathInterpolatorCompat.create(0.33f, 1f, 0.68f, 1f);
    public static Interpolator interpolator_easeIn = PathInterpolatorCompat.create(0.32f, 0f, 0.67f, 0f);
    public static Interpolator interpolator_spring = PathInterpolatorCompat.create(0.34f, 1.56f, 0.64f, 1f);
    public static Interpolator interpolator_bounce = PathInterpolatorCompat.create(0.34f, 1.56f, 0.64f, 1f);
    public static Interpolator interpolator_bounce2 = PathInterpolatorCompat.create(0.34f, 1.46f, 0.54f, 1f);

    public Utils_Anim(){
    }

    public static void AlphaAnimCusEase(View view, float startAlpha, float endAlpha, int duration, Interpolator interpolator) {
        Animation anim = new AlphaAnimation( startAlpha, endAlpha );
        anim.setFillAfter(true);
        anim.setInterpolator(interpolator);
        anim.setDuration(duration);
        view.startAnimation(anim);
    }

    public static void RotateAnim(View view, float startRotate, float endRotate, int duration, Interpolator interpolator) {
        RotateAnimation anim = new RotateAnimation(startRotate, endRotate, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setFillAfter(true);
        anim.setInterpolator(interpolator);
        anim.setDuration(duration);
        view.startAnimation(anim);
        view.setRotation(endRotate);
    }
}