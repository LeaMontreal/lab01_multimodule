package com.wlj.l02_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_greet;
    private Button btn_ask_question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_greet = (Button) findViewById(R.id.btn_greet);
        btn_ask_question = (Button) findViewById(R.id.btn_ask_question);

        // 设置监听，方式二
        btn_greet.setOnClickListener(this);
        btn_ask_question.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        // view就是发生事件的视图对象
        // 这里只需要用==即可，不需要用equals，因为view和btn_greet都是引用类型，指向相同的地址内容肯定也是相同的
        if(view == btn_greet){
            // 在java中使用Res中定义的字符串的方式
//            Toast.makeText(MainActivity.this, R.string.greeting_msg, Toast.LENGTH_LONG).show();

            // 显式创建Intent对象
            Intent intent = new Intent(this, GreetActivity.class);

            // 启动Activity
            startActivity(intent);

        }else if(view == btn_ask_question){
            // 在java中使用Res中定义的字符串的方式
//            Toast.makeText(MainActivity.this, R.string.greeting_msg, Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, AskquestionActivity.class);
            startActivity(intent);
        }

    }

//    @Override
//    public void onClick(View view) {
//        // 在java中使用Res中定义的字符串的方式
//        Toast.makeText(MainActivity.this, R.string.greeting_msg, Toast.LENGTH_LONG).show();
//        // 通过Intent对象启动GreetActivity
//        // new Intent()
//    }
}