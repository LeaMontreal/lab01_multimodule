package com.wlj.app04_blockedlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btn_add_blocked_number;
    private ListView lv_blocked_list;
    private List<BlockedNumber> data;
    private BlockedNumberDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化ListView对象，此时是空的，没有关联数据
        ListView lv_blocked_list = findViewById(R.id.lv_blocked_list);


        // 准备数据
        dao = new BlockedNumberDAO(this);
        // 通过BlockedNumberDAO对象(dao)获得所有存在数据库中的数据
        data = dao.getAll();

        BlockedNumberAdapter adapter = new BlockedNumberAdapter();
        // 为ListView对象设置对应的BlockedNumberAdapter对象（adapter）
        lv_blocked_list.setAdapter(adapter);
    }

    /*
    * Button btn_add_blocked_number的点击响应函数
    * */
    public void addBlockedNumber(View view){

    }

    /*
    * 为了显示ListView，自定义类BlockedNumberAdapter，继承于BaseAdapter
    * */
    class BlockedNumberAdapter extends BaseAdapter{

        private BlockedNumberAdapter(){
            Log.i("TAG", "BlockedNumberAdapter BlockedNumberAdapter()");
        }

        /*
         * 获取集合数据的总条数
         * */
        @Override
        public int getCount() {
            return data.size();
        }

        /*
         * 获取指定下标对应的数据对象
         * */
        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        /*
        * 获取指定下标对应的数据对象的id，没什么用处
        * */
        @Override
        public long getItemId(int position) {
            return data.get(position).getId();
        }

        /*
        * 最重要的方法
        * 获取指定下标对应的View对象
        * position: 下标
        * parent: ListView对象
        * */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            Log.i("TAG", "BlockedNumberAdapter getView()");

            // 根据每个item的layout资源号创建对应的View对象
            // 这个item对应的View对象是可复用的，所以叫convertView
            if(convertView == null){
                // 使用自定义布局
                // convertView = View.inflate(MainActivity.this, R.layout.item_blocked_number, null);
                // 只有字符串的item的布局可以不用自己定义，系统有android.R.layout.simple_list_item_1
                convertView = View.inflate(MainActivity.this, android.R.layout.simple_list_item_1, null);
            }

            // 在view中填入数据
            BlockedNumber blockedNumber = data.get(position);
            // 使用自定义布局时
            //TextView tv_number = convertView.findViewById(R.id.tv_number);
            // 使用系统自带的item布局时对应的TextView
            TextView tv_number = convertView.findViewById(android.R.id.text1);
            tv_number.setText(blockedNumber.getNumber());

            return convertView;
        }
    }
}