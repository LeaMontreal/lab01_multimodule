package com.wlj.l02_activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AskquestionActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_user_msg;
    private Button btn_no_question;
    private Button btn_send_question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_askquestion);

        // 之前忘了初始化et_user_msg
        et_user_msg = findViewById(R.id.et_user_msg);
        btn_no_question = (Button) findViewById(R.id.btn_no_question);
        btn_send_question = (Button) findViewById(R.id.btn_send_question);

        // 设置监听，方式二
        btn_no_question.setOnClickListener(this);
        btn_send_question.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == btn_no_question){    // 不带回调启动Activity
//            Toast.makeText(this, "Thanks for your business", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, ReplyActivity.class);
            // 取出EditText中的信息
            String msg = et_user_msg.getText().toString();
            // 给intent带上信息
            intent.putExtra("MESSAGE", msg);
            // 启动Activity
            startActivity(intent);

        }else if(view == btn_send_question){    // 带回调函数的启动Activity方式
//            Toast.makeText(this, "We will deal with your request ASAP", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, ReplyActivity.class);
            String msg = et_user_msg.getText().toString();
            intent.putExtra("MESSAGE", msg);

            // 带回调函数的启动Activity方式
            int requestCode = 2;
            // todo startActivityForResult()的替代方式是什么？
            startActivityForResult(intent, requestCode);

        }
    }

    // 回调函数处理ReplyActivity返回的结果
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == 2 && resultCode == 3){
            // 1.从intent中取出返回结果
            String result = data.getStringExtra("RESULT");

            // 2.处理返回结果
            et_user_msg.setText(result);
        }

        //父类的onActivityResult()没有完成什么有用功能，可以删除
        // todo 在我的版本中不能删除了，为什么？放在哪个位置合适呢？
        super.onActivityResult(requestCode, resultCode, data);
    }
}