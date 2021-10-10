package com.wlj.l02_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ReplyActivity extends AppCompatActivity {

    private EditText et_user_reply;
//    private Button btn_return_noReply;
//    private Button btn_return_withReply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply);

        et_user_reply = findViewById(R.id.et_user_reply);
        // 通过在布局xml文件中加入单击响应函数的方式设置监听，确实不需要再声明和初始化Button对象了
//        btn_return_noReply = (Button) findViewById(R.id.btn_return_noReply);
//        btn_return_withReply = (Button) findViewById(R.id.btn_return_withReply);

        // 获取启动当前界面的Activity带过来的信息
        Intent intent = getIntent();
        String msg = intent.getStringExtra("MESSAGE");

        // 处理intent中的信息，此处显示在EditText View中
        et_user_reply.setText(msg);

        // 设置监听，方式三
        // 在layout.xml中通过android:onClick="return_withReply"加入处理button单击操作的响应函数
    }

    // 在XxxxActivity中声明这个方法，统一都是public void xxx(View view){};
    public void return_noReply(View view) {
        // 直接返回
        finish();
    }

    public void return_withReply(View view) {
        //Toast.makeText(this, "Execute return with reply", Toast.LENGTH_LONG).show();

        // 1.准备返回结果code
        int resultCode = 3;

        // 2.准备带返回结果的intent
        Intent data = new Intent();
        String result = et_user_reply.getText().toString();
        data.putExtra("RESULT", result);

        // 3.设置返回结果
        setResult(resultCode, data);

        // 4.关闭当前Activity
        finish();
    }
}