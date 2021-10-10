package com.wlj.l02_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class GreetActivity extends AppCompatActivity {

    private Button btn_listen_method1;

    // 重写构造器，protected权限合适吗？这是我自己造的自己包内用的，protected够了
    // 调试发现需要public，构造器是系统调用的（我没有自己创建对象）
    // 构造器也没有@Override的注解
    public GreetActivity(){
        Log.e("TAG", "GreetActivity create");
    }
    @Override
    protected void onStart() {
        Log.e("TAG", "GreetActivity onStart()");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.e("TAG", "GreetActivity onResume");
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 增加Log观察Activity的生命周期
        Log.e("TAG", "GreetActivity onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greet);

        btn_listen_method1 = (Button) findViewById(R.id.btn_listen_method1);
        // 设置监听，方式一
        btn_listen_method1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Activity就是Context类的实现类，可以作为Toast.makeText的第一个参数
                // 如果Toast.makeText的第一个参数直接用this，那就是new生成的那个匿名对象，GreetActivity.this表示调用这个匿名对象的外部类的对象
                // 参数2：这就是在java中使用Res中定义的字符串的方式
                Toast.makeText(GreetActivity.this, R.string.greeting_msg, Toast.LENGTH_LONG).show();
                btn_listen_method1.setText(R.string.label_btn_common_onclick);
            }
        });
    }
}