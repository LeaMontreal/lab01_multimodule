package com.wlj.l03_view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;

public class TestProgressBarActivity extends AppCompatActivity {

    private LinearLayout ll_progress_layout;
    private ProgressBar pbar_loading;
    private SeekBar sbar_loading;

    private SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {    // 拖动滑杆
            Log.i("TAG", "onProgressChanged()");

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {     // 按下滑杆
            Log.i("TAG", "onStartTrackingTouch()");
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {      // 离开滑杆
            Log.i("TAG", "onStopTrackingTouch()");

            // 同步滑杆的进度到上面的水平progress bar
            //      这里seekBar和sbar_loading指向同样的SeekBar对象，两个都可以使用
            int progress = seekBar.getProgress();
            pbar_loading.setProgress(progress);

            // 进度到最大值时，让最上面的圆形progress bar消失
            if(progress == seekBar.getMax()){
                //ll_progress_layout.setVisibility(View.INVISIBLE);     // 不可见，但占空间
                ll_progress_layout.setVisibility(View.GONE);    // 不可见，且不占空间
            }else{
                ll_progress_layout.setVisibility(View.VISIBLE);
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_progress_bar);

        ll_progress_layout = findViewById(R.id.ll_progress_layout);
        pbar_loading = findViewById(R.id.pbar_loading);
        sbar_loading = findViewById(R.id.sbar_loading);

        // 因为SeekBar的监听器需要override的方法比较多，就采用定义一个成员变量的方式来实现，以便代码可读性更好
        sbar_loading.setOnSeekBarChangeListener(onSeekBarChangeListener);
    }

}