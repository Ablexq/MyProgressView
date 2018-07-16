package com.xq.myprogressview;

import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ProgressBar;

import com.xq.myprogressview.view.MyDynamicProgressView;
import com.xq.myprogressview.view.MyLoadingView;
import com.xq.myprogressview.view.MyStaticProgressView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MyDynamicProgressView myProgressView1 = ((MyDynamicProgressView) this.findViewById(R.id.myview1));
        myProgressView1.setDynamicPercent(1);

        MyStaticProgressView myProgressView2 = ((MyStaticProgressView) this.findViewById(R.id.myview2));
        myProgressView2.setStaticProgress(1);


        final ProgressBar progressBar = (ProgressBar) this.findViewById(R.id.myview7);

        ValueAnimator progressAnimator = ValueAnimator.ofInt(0, 100);
        progressAnimator.setDuration(5000);
        progressAnimator.setInterpolator(new LinearInterpolator());
        progressAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int animatedValue = (int) valueAnimator.getAnimatedValue();

                progressBar.setProgress(animatedValue);
            }
        });
        progressAnimator.start();

        final MyLoadingView myLoadingView = (MyLoadingView) this.findViewById(R.id.myloadingview);
        myLoadingView.setProgress(100);
        myLoadingView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myLoadingView.setProgress(100);
            }
        });

    }
}
