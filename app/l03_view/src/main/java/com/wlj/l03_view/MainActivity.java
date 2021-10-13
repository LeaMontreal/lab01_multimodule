package com.wlj.l03_view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_test_simple;
    private Button btn_test_menu;
    private Button btn_test_processBar;
    private Button btn_test_dialogue;
    private Button btn_test_listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_test_simple = (Button) findViewById(R.id.btn_test_simple);
        btn_test_menu = (Button) findViewById(R.id.btn_test_menu);
        btn_test_processBar = (Button) findViewById(R.id.btn_test_processBar);
        btn_test_dialogue = (Button) findViewById(R.id.btn_test_dialogue);
        btn_test_listview = (Button) findViewById(R.id.btn_test_listview);

        btn_test_simple.setOnClickListener(this);
        btn_test_menu.setOnClickListener(this);
        btn_test_processBar.setOnClickListener(this);
        btn_test_dialogue.setOnClickListener(this);
        btn_test_listview.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.btn_test_simple:
                intent = new Intent(this, TestSimpleActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_test_menu:
//                Toast.makeText(this, "test_menu In developing...", Toast.LENGTH_LONG).show();
                intent = new Intent(this, TestMenuActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_test_processBar:
//                Toast.makeText(this, "test_processBar In developing...", Toast.LENGTH_LONG).show();
                intent = new Intent(this, TestProgressBarActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_test_dialogue:
                //Toast.makeText(this, "test_dialogue In developing...", Toast.LENGTH_LONG).show();
                intent = new Intent(this, TestDialogActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_test_listview:
                startActivity(new Intent(this, TestListviewActivity.class));
                break;
        }
    }
}