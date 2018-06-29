

自定义view用drawline会受到画笔的粗细很大影响，

所以使用drawroundrect来简单绘制

感谢：
[Android 自定义水平进度条的圆角进度](https://blog.csdn.net/lv_fq/article/details/51762209)

```
<?xml version="1.0" encoding="utf-8"?>
<layer-list xmlns:android="http://schemas.android.com/apk/res/android">

    <item
        android:id="@android:id/background"
        android:drawable="@drawable/shape_progressbar_bg00" />

    <item android:id="@android:id/progress">

        <scale
            android:drawable="@drawable/shape_progressbar_progress00"
            android:scaleWidth="100%" />

    </item>

</layer-list>
```
android:id/progress的drawable 

圆角决定起点形状
 
clip/scale决定终点形状 

为clip 时 终点平头 ， 

为scale 时 终点圆头 