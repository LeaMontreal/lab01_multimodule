package com.wlj.l03_view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class TestSimpleActivity extends AppCompatActivity {
    private TextView tv_simple_message;
    private ImageView iv_simple;
    private boolean starIsOn=true;

    private CheckBox checkBox_basket;
    private CheckBox checkBox_football;
    private CheckBox checkBox_pingpong;
    private Button btn_for_checkbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_simple);

        tv_simple_message = (TextView) findViewById(R.id.tv_simple_message);
        tv_simple_message.setText(R.string.text_simple_message_changed);

        iv_simple = findViewById(R.id.iv_simple);
        iv_simple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (starIsOn){
                    iv_simple.setImageResource(android.R.drawable.btn_star_big_off);

                }else{
                    iv_simple.setImageResource(android.R.drawable.btn_star_big_on);
                }
                starIsOn = !starIsOn;
            }
        });

        checkBox_basket = findViewById(R.id.checkBox_basket);
        checkBox_football = findViewById(R.id.checkBox_football);
        checkBox_pingpong = findViewById(R.id.checkBox_pingpong);
        btn_for_checkbox = findViewById(R.id.btn_for_checkbox);

    }

    public void submitHobbyChoice(View view){
        StringBuffer buffer = new StringBuffer();

        if(checkBox_basket.isChecked()){
            // Caution:
//            buffer.append(R.string.label_checkBox_basket).append(" ");
            buffer.append(checkBox_basket.getText().toString()).append(" ");
        }
        if(checkBox_football.isChecked()){
//            buffer.append(R.string.label_checkBox_football).append(" ");
            buffer.append(checkBox_football.getText()).append(" ");
        }
        if(checkBox_pingpong.isChecked()){
//            buffer.append(R.string.label_checkBox_pingpong);
            buffer.append(checkBox_pingpong.getText().toString());
        }

        Toast.makeText(this, buffer, Toast.LENGTH_LONG).show();
        tv_simple_message.setText(R.string.label_checkBox_basket);
    }
}