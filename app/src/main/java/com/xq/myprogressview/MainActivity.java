package com.xq.myprogressview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xq.myprogressview.view.MyDynamicProgressView;
import com.xq.myprogressview.view.MyStaticProgressView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MyDynamicProgressView myProgressView1 = ((MyDynamicProgressView) this.findViewById(R.id.myview1));
        myProgressView1.setDynamicPercent(80);

        MyStaticProgressView myProgressView2 = ((MyStaticProgressView) this.findViewById(R.id.myview2));
        myProgressView2.setStaticProgress(80);
    }
}
