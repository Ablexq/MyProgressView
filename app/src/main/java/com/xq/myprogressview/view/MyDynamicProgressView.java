package com.xq.myprogressview.view;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;

public class MyDynamicProgressView extends View {
    private Paint progressPaint;
    private static final int DEFAULT_WIDTH = 600;
    private static final int DEFAULT_HEIGHT = 300;
    private Paint bgPaint;
    private float currentProgress = 0f;
    private RectF rectFBg;
    private int mWidth;
    private int mHeight;

    public MyDynamicProgressView(Context context) {
        super(context);
        init();
    }

    public MyDynamicProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyDynamicProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        progressPaint = new Paint();
        progressPaint.setStrokeCap(Paint.Cap.ROUND);
        progressPaint.setAntiAlias(true);
        progressPaint.setStrokeWidth(40);
        progressPaint.setColor(Color.RED);
        progressPaint.setStyle(Paint.Style.FILL);

        bgPaint = new Paint();
        bgPaint.setStrokeCap(Paint.Cap.ROUND);
        bgPaint.setAntiAlias(true);
        bgPaint.setColor(Color.GRAY);
        bgPaint.setStrokeWidth(40);
        bgPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getProperSize(DEFAULT_WIDTH, widthMeasureSpec);
        int height = getProperSize(DEFAULT_HEIGHT, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = w;
        mHeight = h;
        rectFBg = new RectF(0, 0, w, h);
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

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {

        //绘制背景
        canvas.drawRoundRect(rectFBg, mHeight / 2, mHeight / 2, bgPaint);

        //设置渐变
        LinearGradient linearGradient = new LinearGradient(0, getHeight() / 2,//起点
                currentProgress, getHeight() / 2,//终点
                new int[]{Color.YELLOW, Color.BLUE, Color.RED},
                null,
                Shader.TileMode.CLAMP);
        progressPaint.setShader(linearGradient);

        //绘制真实进度
        canvas.drawRoundRect(new RectF(0, 0, currentProgress, mHeight), mHeight / 2, mHeight / 2, progressPaint);
    }

    public void setDynamicPercent(float mPercent) {
        ValueAnimator progressAnimator = ValueAnimator.ofFloat(0, mPercent);
        progressAnimator.setDuration(2000);
        progressAnimator.setInterpolator(new BounceInterpolator());
        progressAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float value = (float) valueAnimator.getAnimatedValue();
                currentProgress = value * mWidth / 100;
                invalidate();
            }
        });
        progressAnimator.start();
    }


}
