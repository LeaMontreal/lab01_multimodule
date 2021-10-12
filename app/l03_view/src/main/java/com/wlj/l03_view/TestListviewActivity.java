package com.wlj.l03_view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TestListviewActivity extends AppCompatActivity {

    private ListView lv_main;
    private List<FoodInfo> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_listview);

        lv_main = findViewById(R.id.lv_food);

        // 显示ListView常规三步
        // 1. 准备集合数据
        data = new ArrayList<FoodInfo>();
        data.add(new FoodInfo(R.drawable.dorayaki_01, "dorayaki-------01", "This is a dorayaki..."));
        data.add(new FoodInfo(R.drawable.waffle_02, "waffle-------01", "This is a waffle..."));
        data.add(new FoodInfo(R.drawable.pinenut_03, "pinenut-------01", "This is a pinenut..."));
        data.add(new FoodInfo(R.drawable.preservedegg_04, "preservedegg-------01", "This is a preservedegg..."));
        data.add(new FoodInfo(R.drawable.chickenwings_05, "chickenwings-------01", "This is a chickenwings..."));
        data.add(new FoodInfo(R.drawable.drumsticks_06, "drumsticks-------01", "This is a drumsticks..."));
        data.add(new FoodInfo(R.drawable.scallopinshell_07, "scallopinshell-------01", "This is a scallopinshell..."));
        data.add(new FoodInfo(R.drawable.cashew_08, "cashew-------01", "This is a cashew..."));
        data.add(new FoodInfo(R.drawable.kelp_09, "kelp-------01", "This is a kelp..."));
        data.add(new FoodInfo(R.drawable.pistachio_10, "pistachio-------01", "This is a pistachio..."));

        // 2. 准备XxxxAdapter对象
        //     方式三：BaseAdapter
        MyAdapter adapter = new MyAdapter();

        // 3. 调用setAdapter(adapter)显示
        lv_main.setAdapter(adapter);
    }

    // 把MyAdapter定义为内部类，直接使用集合数据
    public class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return (data.get(position));
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        /*
        * 这是MyAdapter中最重要的方法
        * 用于返回位于position位置item的View对象
        * position: 下标
        * convertView: 可复用的缓存item视图对象，前n+1个为null，n为一屏可以显示的item条数
        * parent: ListView对象
        * */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // 1. View.inflate()静态方法创建一个item的View对象（使用item的布局）
            if(convertView == null){
                Log.i("TAG", "MyAdapter getView()");
                convertView = View.inflate(TestListviewActivity.this, R.layout.item_food, null);
            }

            // 2. 在view中填入数据
            //      2.1 得到要填入的数据，每个item对应的数据就是一个自定义类FoodInfo对象
            FoodInfo foodInfo = data.get(position);
            //      2.2 得到item中各个子View对象
            ImageView imageView = convertView.findViewById(R.id.iv_food_icon);
            TextView textName = convertView.findViewById(R.id.tv_food_name);
            TextView textDetail = convertView.findViewById(R.id.tv_food_detail);

            //      2.3 把数据分别填入子View中
            imageView.setImageResource(foodInfo.getId_icon());
            textName.setText(foodInfo.getName());
            textDetail.setText(foodInfo.getDetail());

            return convertView;
        }
    }


}