package com.wlj.l03_view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TestDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_dialog);
    }

    /*
    * 显示带确认和取消按钮的Alert Dialog
    * */
    public void showAlertDialogNormal(View view){

        new AlertDialog.Builder(this)
                .setTitle("数据删除")
                .setMessage("你确定要删除数据吗？")
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(TestDialogActivity.this, "删除数据", Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(TestDialogActivity.this, "取消删除数据", Toast.LENGTH_LONG).show();
                    }
                })
                .show();
    }

    /*
     * 显示带单选项的Alert Dialog
     * */
    public void showAlertDialogSingleOption(View view){
        // 注意这里final的用法
        // 是为了在showAlertDialogSingleOption()方法中定义的局部变量items能在新建的OnClickListener的子类的onClick()方法中继续使用
        final String[] items = {"Red", "Yellow", "Blue", "Gray"};
        new AlertDialog.Builder(this)
                .setTitle("选择背景颜色")
                .setSingleChoiceItems(items, 2, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 提示选择生效
                        Toast.makeText(TestDialogActivity.this, "Your background color will be " + items[which],
                                Toast.LENGTH_SHORT).show();

                        // 移除对话框
                        // 注意这里传入的dialog就是当前的AlertDialog对象，但是形参声明为DialogInterface类型，这是多态的应用
                        dialog.dismiss();
                    }
                })
                .show();
    }

    /*
     * 显示自定义的Alert Dialog
     * */
    public void showAlertDialogDefine(View view){
        // 动态加载布局文件，得到对应的View对象
        // 用View的静态方法inflate()创建View对象
        View dialogView = View.inflate(this, R.layout.dialog_define, null);

        // 获得View对象的各子View对象
        final EditText et_dialog_user = dialogView.findViewById(R.id.et_dialog_user);
        final EditText et_dialog_pwd = dialogView.findViewById(R.id.et_dialog_pwd);

        new AlertDialog.Builder(this)
                .setView(dialogView)
                .setNegativeButton("取消", null)
                .setPositiveButton("提交", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 读取用户输入（用户名和密码）
                        Toast.makeText(TestDialogActivity.this,
                                et_dialog_user.getText().toString() + ":" + et_dialog_pwd.getText().toString(),
                                Toast.LENGTH_LONG).show();
                    }
                })
                .show();

    }


    public void showProcessDialogCircle(View view){

    }

    public void showProcessDialogHorizontal(View view){

    }

    public void showDatePickerDialog(View view){

    }

    public void showTimePickerDialog(View view){

    }

}