package com.wlj.l03_view;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TestListviewActivity_SimpleAdapter extends AppCompatActivity {

    ListView lv_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_listview);

        lv_main = findViewById(R.id.lv_food);

        // 显示ListView常规三步
        // 1. 准备集合数据
        // SimpleAdapter对应的集合数据是List，每个元素是Map
        // 注意泛型的写法
        HashMap<String, Object> map;
        ArrayList<Map<String, Object>> data = new ArrayList<>();
        map = new HashMap<>();
        map.put("icon", R.drawable.dorayaki_01);
        map.put("name", "dorayaki-------01");
        map.put("detail", "This is a dorayaki...");
        data.add(map);

        // 注意，每个item都需要new一个新对象，因为需要一块新的内存空间来存储这些数据，
        // 后面再调用List的add()方法时只是把元素挂上去了，并没有申请新的内存空间来存储这个新元素
        map = new HashMap<>();
        map.put("icon", R.drawable.waffle_02);
        map.put("name", "waffle-------01");
        map.put("detail", "This is a waffle...");
        data.add(map);

        map = new HashMap<>();
        map.put("icon", R.drawable.pinenut_03);
        map.put("name", "pinenut-------01");
        map.put("detail", "This is a pinenut...");
        data.add(map);

        map = new HashMap<>();
        map.put("icon", R.drawable.preservedegg_04);
        map.put("name", "preservedegg-------01");
        map.put("detail", "This is a preservedegg...");
        data.add(map);

        map = new HashMap<>();
        map.put("icon", R.drawable.chickenwings_05);
        map.put("name", "chickenwings-------01");
        map.put("detail", "This is a chickenwings...");
        data.add(map);

        // from是一个map的所有key组成的String[]
        String[] from = {"icon", "name", "detail"};

        // to是一个int[]，每个元素就是item布局中的一个View
        int[] to = {R.id.iv_food_icon, R.id.tv_food_name, R.id.tv_food_detail};

        // 2. 准备XxxxAdapter对象
        //     方式二：SimpleAdapter
        SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.item_food, from, to);

        // 3. 调用setAdapter(adapter)显示
        lv_main.setAdapter(adapter);
    }


}