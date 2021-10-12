package com.wlj.l03_view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class TestMenuActivity extends AppCompatActivity {

    // 长按此Button弹出ContextMenu
    private Button btn_menu_contextmenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_menu);

        btn_menu_contextmenu = findViewById(R.id.btn_menu_contextmenu);
        // 设置创建上下文菜单的监听
        btn_menu_contextmenu.setOnCreateContextMenuListener(this);
    }

    /*
    * ContextMenu：创建上下文菜单时的回调方法，在其中实现代码添加MenuItem
    * */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        // 增加MenuItem时的itemid只要和后面的onContextItemSelected()处理时的itemid保持一致即可，不需要连续
        menu.add(0, 1, 0, "item_1");
        menu.add(0, 4, 0, "item_2");
        menu.add(0, 5, 0, "item_3");
    }

    /*
    * 单击按钮时提示用户，应该长按来显示Context Menu
    * */
    public void promptButtonUse(View view){
        Toast.makeText(this, "长按此按钮显示Context Menu", Toast.LENGTH_LONG).show();
    }

    /*
    * ContextMenu：当某个Item被选中时的回调方法，实现每个Item的业务功能
    * */
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case 1:
                Toast.makeText(this,"ContextMenu在这里实现item_1的功能...", Toast.LENGTH_LONG).show();
                break;
            case 4:
                Toast.makeText(this,"ContextMenu在这里实现item_2的功能...", Toast.LENGTH_LONG).show();
                break;
            case 5:
                Toast.makeText(this,"ContextMenu在这里实现item_3的功能...", Toast.LENGTH_LONG).show();
                break;
        }

        return super.onContextItemSelected(item);

    }

    // 1. 创建OptionMenu item方式一：直接编码实现
    // 1.1 重写onCreateOptionsMenu
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        menu.add(Menu.NONE, 1, Menu.NONE, "增加");
//        menu.add(Menu.NONE, 2, Menu.NONE, "删除");
//
//        return super.onCreateOptionsMenu(menu);
//    }

    // 2. 创建OptionsMenu item方式二：
    //      2.1 在res中创建菜单资源文件xxx_menu.xml
    //      2.2 获取菜单加载器，并加载菜单文件
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 2.2.1 获取菜单加载器
        MenuInflater inflater = getMenuInflater();
        // 2.2.2 加载菜单文件
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }
    // 1.2 对MenuItem的响应处理方法onOptionsItemSelected()
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
//            case 1:
//                Toast.makeText(this,"方式一：在这里实现增加的功能...", Toast.LENGTH_LONG).show();
//                break;
//            case 2:
//                Toast.makeText(this,"方式一：在这里实现删除的功能...", Toast.LENGTH_LONG).show();
//                break;

            // 2.3 对MenuItem的响应处理，使用res中定义的id
            case R.id.optionsmenu_add:
                Toast.makeText(this,"方式二：在这里实现增加的功能...", Toast.LENGTH_LONG).show();
                break;
            case R.id.optionsmenu_delete:
                Toast.makeText(this,"方式二：在这里实现删除的功能...", Toast.LENGTH_LONG).show();
                break;

        }

        return super.onOptionsItemSelected(item);
    }


}