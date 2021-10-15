package com.wlj.app04_blockedlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btn_add_blocked_number;
    private ListView lv_blocked_list;
    BlockedNumberAdapter adapter;
    private List<BlockedNumber> data;
    private BlockedNumberDAO dao;

    // 为了记录点击更新或删除的位置
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        // 增加log信息查MyAdapter.getView()为什么被调用了3次
//        Log.i("TAG", "MainActivity onCreate()");

        // 初始化ListView对象，此时是空的，没有关联数据
        ListView lv_blocked_list = findViewById(R.id.lv_blocked_list);


        // 准备数据
        dao = new BlockedNumberDAO(this);
        // 通过BlockedNumberDAO对象(dao)获得所有存在数据库中的数据
        data = dao.getAll();

        adapter = new BlockedNumberAdapter();
        // 为ListView对象设置对应的BlockedNumberAdapter对象（adapter）
        lv_blocked_list.setAdapter(adapter);

        // 给ListView对象设置创建上下文菜单的监听
        lv_blocked_list.setOnCreateContextMenuListener(this);
    }

    /*
    * 创建上下文菜单时的回调方法
    * v：表示创建这个上下文菜单的View对象，当前是lv_blocked_list
    * menuInfo：当前表示ListView的某个item对象
    * */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        // 添加两个item
        menu.add(0, 11, 1, "Update");
        menu.add(0, 12, 2, "Delete");

        // 获得被点中的ListView中Item的位置
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        position = info.position;
    }

    /*
    * ContextMenu菜单项被点中时的回调方法
    * */
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        final BlockedNumber blockedNumber = data.get(position);

        switch(item.getItemId()){
            case 11:    // update
                showUpdateDialog(blockedNumber);
                break;
            case 12:    // delete
                // 更新内存数据List
                data.remove(position);

                // 更新数据库
                dao.deleteById(blockedNumber.getId());

                // 更新UI
                adapter.notifyDataSetChanged();
                break;
            default:
                break;
        }

        return super.onContextItemSelected(item);
    }

    private void showUpdateDialog(final BlockedNumber blockedNumber) {
        EditText et_changed_number = new EditText(this);
        et_changed_number.setHint(blockedNumber.getNumber());

        new AlertDialog.Builder(this)
                .setTitle("更新黑名单号码")
                .setView(et_changed_number)
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 更新内存数据：List
                        String newNumber = et_changed_number.getText().toString();
                        blockedNumber.setNumber(newNumber);

                        // 更新数据库
                        dao.update(blockedNumber);

                        // 更新UI：通知更新ListView
                        adapter.notifyDataSetChanged();

                    }
                })
                .show();


    }

    //    @Override
//    protected void onStart() {
//        super.onStart();
//
//        // 增加log信息查MyAdapter.getView()为什么被调用了3次
//        Log.i("TAG", "MainActivity onStart()");
//
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        // 增加log信息查MyAdapter.getView()为什么被调用了3次
//        Log.i("TAG", "MainActivity onResume()");
//
//    }

    /*
     * Button btn_add_blocked_number的点击响应函数
     * */
    public void addBlockedNumber(View view) {
        EditText et_add_number = new EditText(this);
        // 1、弹出对话框
        new AlertDialog.Builder(this)
                .setTitle("添加黑名单号码")
                .setView(et_add_number)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 增加黑名单号码的处理
                        //Log.i("TAG", "it's in developing...");

                        // 2、更新数据库：新增数据存入
                        String number = et_add_number.getText().toString();
                        BlockedNumber blockedNumber = new BlockedNumber(-1, number);
                        dao.add(blockedNumber); // 注意，调用add()前后blockedNumber的_id属性值变了

                        // 3、更新内存：新增数据存入List
                        // data.add(blockedNumber);
                        //      每次新插入都插入到位置0，这样就是倒序的效果
                        data.add(0, blockedNumber);

                        // 4、更新界面：刷新ListView显示
                        adapter.notifyDataSetChanged();

                        if(Constant.LOG_INFO){
                            // 获取当前时间，todo 这两个时间并不一样啊
                            Log.i("TAG", "Current time by System: " + System.currentTimeMillis());
                            Log.i("TAG", "Current time by SystemClock: " + SystemClock.uptimeMillis());
                        }
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();


    }

    /*
     * 为了显示ListView，自定义类BlockedNumberAdapter，继承于BaseAdapter
     * */
    class BlockedNumberAdapter extends BaseAdapter {

        private BlockedNumberAdapter() {
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

            Log.i("TAG", "BlockedNumberAdapter getView() position=" + position);

            // 根据每个item的layout资源号创建对应的View对象
            // 这个item对应的View对象是可复用的，所以叫convertView
            if (convertView == null) {
                // 使用自定义布局
                // convertView = View.inflate(MainActivity.this, R.layout.item_blocked_number, null);
                // 只有字符串的item的布局可以不用自己定义，系统有android.R.layout.simple_list_item_1
                convertView = View.inflate(MainActivity.this, android.R.layout.simple_list_item_1, null);
                Log.i("TAG", "View.inflate()");
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