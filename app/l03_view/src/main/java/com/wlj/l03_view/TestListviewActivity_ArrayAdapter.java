package com.wlj.l03_view;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class TestListviewActivity_ArrayAdapter extends AppCompatActivity {

    ListView lv_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_listview);

        lv_main = findViewById(R.id.lv_food);

        // 显示ListView常规三步
        // 1. 准备集合数据
        Log.e("TAG", "prepare data");
        String[] data = {"01_dorayaki", "02_waffle", "03_pinenut", "04_preservedegg", "05_chickenwings",
                           "06_drumsticks", "07_scallopinshell", "08_cashew", "09_kelp", "10_pistachio",
                            "11_walnut", "12_chinesechestnut", "13_reddates", "14_shrimp", "15_saury", "16_peanut", "17_sandwich"};

        // 2. 准备XxxxAdapter对象
        //     方式一：ArrayAdapter
        Log.i("TAG", "prepare ArrayAdapter");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item_simple_string, data);

        // 3. 调用setAdapter(adapter)显示
        Log.w("TAG", "setAdapter()");
        lv_main.setAdapter(adapter);
    }


}