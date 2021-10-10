package com.wlj.l04_datastorage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SharedpreferenceActivity extends AppCompatActivity {

    private EditText et_input_key;
    private EditText et_input_value;
    private Button btn_read;
    private Button btn_save;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharedpreference);

        et_input_key = (EditText) findViewById(R.id.et_input_key);
        et_input_value = (EditText) findViewById(R.id.et_input_value);
        btn_read = (Button) findViewById(R.id.btn_read);
        btn_save = (Button) findViewById(R.id.btn_save);

        // 1.获取对应SharedPreference file的对象
        // name就是要保存的文件名，mode都填Context.MODE_PRIVATE
        sharedPreferences = getSharedPreferences("myprojectPrivateData", Context.MODE_PRIVATE);
    }

    public void spSave(View view){

        // 2.获取Editor对象
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // 3.准备输入的key,value
        String key = et_input_key.getText().toString();
        String value = et_input_value.getText().toString();

        // 4.存入SharedPreference文件对象
        // Editor的putString()方法仍然返回Editor类型，所以可以使用方法链，继续调用commit()方法
        editor.putString(key, value).commit();

        // 文件myprojectPrivateData.xml的路径为手机上：data\data\com.wlj.l04_datastorage\shared_prefs
        Toast.makeText(this, "Saved shared preference", Toast.LENGTH_LONG);
    }

    public void spRead(View view){
        // 1.读取输入的key
        String key = et_input_key.getText().toString();

        // 2.SharedPreference对象的getXxxx()方法读取key对应的value值
        String value = sharedPreferences.getString(key, null);
        // 3.显示出读到的结果
        if(null == value){
            Toast.makeText(this, "没找到对应的value", Toast.LENGTH_LONG).show();
        }else{
            et_input_value.setText(value);
        }

    }

}