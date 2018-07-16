package com.xq.myprogressview.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;

/**
 * Created by lenovo on 2018/7/16.
 */

public class MyLoadingView extends View {

    private static final int DEFAULT_WIDTH = 600;
    private static final int DEFAULT_HEIGHT = 300;

    /**
     * 当前进度
     */
    private int mProgress = 0;
    /**
     * 最大进度
     */
    private static final int maxProgress = 100;
    private static final int minProgress = 0;
    private RectF mRectF;
    private Paint circlePaint;

    public MyLoadingView(Context context) {
        super(context);
        init();
    }

    public MyLoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyLoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        circlePaint = new Paint();
        circlePaint.setColor(Color.RED);
        circlePaint.setStrokeWidth(5);
        circlePaint.setAntiAlias(true);
        circlePaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getProperSize(DEFAULT_WIDTH, widthMeasureSpec);
        int height = getProperSize(DEFAULT_HEIGHT, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    private int getProperSize(int defaultSize, int measureSpec) {
        int result;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            result = defaultSize;
            if (mode == MeasureSpec.AT_MOST) {
                result = Math.min(result, size);
            }
        }

        return result;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        System.out.println("w=======================" + w);
        System.out.println("h=======================" + h);
        mRectF = new RectF();
        mRectF.left = 20;
        mRectF.top = 20;
        mRectF.right = w - 20;
        mRectF.bottom = h - 20;

    }

    /**
     * 起始角度
     */
    private static final float startAngle = -90;

    @Override
    protected void onDraw(Canvas canvas) {
        float precent = 1.0f * mProgress / maxProgress;//当前完成百分比
        //mRectF是代表整个view的范围
        canvas.drawArc(mRectF, startAngle - 270 * precent, -(60 + precent * 300), false, circlePaint);
    }

    public void setProgress(int progress) {
        if (progress < minProgress) {
            this.mProgress = 0;
        }
        if (progress > maxProgress) {
            this.mProgress = 100;
        }

        this.mProgress = progress;
        ValueAnimator progressAnimator = ValueAnimator.ofInt(0, mProgress);
        progressAnimator.setDuration(5000);
        progressAnimator.setRepeatCount(ValueAnimator.INFINITE);
        progressAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        progressAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mProgress = (int) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        progressAnimator.start();
    }
}
