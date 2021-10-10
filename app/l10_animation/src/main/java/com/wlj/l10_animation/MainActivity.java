package com.wlj.l10_animation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btn_code_scale;
    private Button btn_xml_scale;
    private ImageView iv_animation;
    private TextView tv_animation_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("TAG", "onCreate()");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_code_scale = findViewById(R.id.btn_code_scale);
        btn_xml_scale = findViewById(R.id.btn_xml_scale);
        iv_animation = findViewById(R.id.iv_animation);
        tv_animation_info = findViewById(R.id.tv_animation_info);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("TAG", "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("TAG", "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("TAG", "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("TAG", "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("TAG", "onDestroy()");
    }

    /*
    * 1.1 编码实现 缩放动画
    * 宽度从0.5到1.5，高度从0到1；缩放的中心点为顶部中心点；延迟1s开始，持续2s；最终还原；
    * */
    public void codeAnimationScale(View view){
        tv_animation_info.setText("编码实现 缩放动画 \n 宽度从0.5到1.5，高度从0到1；缩放的中心点为顶部中心点；\n 延迟1s开始，持续2s；最终还原；");
        // 1) 创建动画对象
        // public ScaleAnimation(float fromX, float toX,
        //                      float fromY, float toY,
        //                      int pivotXType,     缩放中心点pivot的坐标类型
        //                      float pivotXValue,  pivot的X值
        //                      int pivotYType,
        //                      float pivotYValue)
        // 采用绝对值类型的中心点坐标，X和Y都是绝对的pixel值 （The specified dimension is an absolute number of pixels.）
//        Animation animation = new ScaleAnimation(0.5f, 1.5f, 0, 1,
//                Animation.ABSOLUTE, iv_animation.getWidth()/2, Animation.ABSOLUTE, 0);

        // 采用相对本View的中心点坐标，X和Y分别是相对本View的Width和Height
        ScaleAnimation animation = new ScaleAnimation(0.5f, 1.5f, 0, 1,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0);

        // 2) 设置动作特征
        // 延迟1s开始，
        animation.setStartOffset(1000);
        // 持续2s；
        animation.setDuration(2000);
        // 最终还原；
        animation.setFillAfter(true);

        // 3) 启动动画
        iv_animation.startAnimation(animation);
    }

    /*
    * 1.2 xml实现 缩放动画
    * 宽度从0.0到1.5，高度从0到1，延迟1s开始，持续3s，中心点为右下角，最终固定
    * */
    public void xmlAnimationScale(View view){
        tv_animation_info.setText("xml实现 缩放动画 \n 宽度从0.0到1.5，高度从0到1，延迟1s开始，持续3s，中心点为右下角，最终固定");

        // 1)定义动画文件xml
        // 2)加载动画文件，得到动画对象
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale_test);

        // 3)设置动作特征
        animation.setStartOffset(1000);
        animation.setDuration(3000);
        animation.setFillAfter(true);

        // 4)启动动画
        iv_animation.startAnimation(animation);
    }

    /*
     * 2.1 编码实现 旋转动画
     * 以图片中心点为pivot，从-90°到+90°，持续5s
     * */
    public void codeAnimationRotate(View view){
        tv_animation_info.setText("编码实现 旋转动画\n" +
                "以图片中心点为pivot，从-90°到+90°，持续5s");

        // 1) 创建动画对象
        // public RotateAnimation(float fromDegrees,
        //                       float toDegrees,
        //                       int pivotXType,
        //                       float pivotXValue,
        //                       int pivotYType,
        //                       float pivotYValue)
        RotateAnimation animation = new RotateAnimation(-90, 90,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        // 2) 设置动画的动作特征
        animation.setDuration(5000);

        // 3) 启动动画
        iv_animation.startAnimation(animation);
    }

    /*
     * 2.2 xml实现 旋转动画
     * 以图片中心点为pivot，从-90°到+90°，持续5s
     * */
    public void xmlAnimationRotate(View view){
        tv_animation_info.setText("xml实现 旋转动画\n" +
                "以图片下中心点为pivot，从+90°到-90°，持续8s");

        // 1) 定义动画xml文件
        // 2) 加载动画，得到动画对象
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate_test);

        // 3) 设置动画的动作特征
        animation.setDuration(8000);

        // 4) 启动动画
        iv_animation.startAnimation(animation);
    }

    /*
     * 3.1 编码实现 透明度动画
     * 0完全透明 1完全不透明
     * */
    public void codeAnimationAlpha(View view){
        tv_animation_info.setText("编码实现 透明度动画\n" +
                "从完全透明到完全不透明，持续3s");

        // 1)创建动画对象
        AlphaAnimation animation = new AlphaAnimation(0, 1);
        // 2)设置动画行为
        animation.setDuration(3000);
        // 3)启动动画
        iv_animation.startAnimation(animation);
    }

    /*
     * 3.2 xml实现 透明度动画
     * 0完全透明 1完全不透明
     * */
    public void xmlAnimationAlpha(View view){
        tv_animation_info.setText("编码实现 透明度动画\n" +
                "从完全不透明到完全透明，持续5s");

        // 1)加载动画资源文件，获得动画对象
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha_test);
        // 2)设置动画行为
        animation.setDuration(5000);
        animation.setFillAfter(true);   // 结束时停留在最后那个状态（即透明）；默认是结束动画，显示图片
        // 3)启动动画
        iv_animation.startAnimation(animation);

    }

    /*
     * 4.1 编码实现 平移动画
     *
     * */
    public void codeAnimationTranslate(View view){}

    /*
     * 4.2 xml实现 平移动画
     *
     * */
    public void xmlAnimationTranslate(View view){}

    /*
     * 5.1 编码实现 复合动画
     *
     * */
    public void codeAnimationSet(View view){}

    /*
     * 4.2 xml实现 复合动画
     *
     * */
    public void xmlAnimationSet(View view){}


}