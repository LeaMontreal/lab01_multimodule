package com.wlj.l03_view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

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

    /*
    * 显示圆形进度对话框
    * */
    public void showProgressDialogCircle(View view){    // 回调方法，在主线程执行
        // 注意这里dialog要加final
        final ProgressDialog dialog = ProgressDialog.show(this, "数据加载", "数据加载中...");

        // 模拟一段长时间的操作，然后移除对话框
        //      长时间的操作要在分线程执行
        new Thread(new Runnable() {     // 这个新建的Runnable接口在%分线程%执行
            @Override
            public void run() {
                for(int i = 0; i < 20; i++){
                    try {
                        // 常用的模拟操作方法，让线程休眠Thread.sleep()
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // 移除对话框
                dialog.dismiss();

                // 如果想要增加“加载完成”的提示信息，需要在主线程完成
                // 不能在分线程直接更新UI
                runOnUiThread(new Runnable() {  // 这个新建的Runnable接口在%主线程%执行
                    @Override
                    public void run() {
                        Toast.makeText(TestDialogActivity.this, "加载完成!!!", Toast.LENGTH_LONG).show();
                    }
                });
            }
        }).start();

    }

    /*
    * 显示水平进度对话框
    * */
    public void showProgressDialogHorizontal(View view){
        // 创建对象
        final ProgressDialog dialog = new ProgressDialog(this);
        // 设置水平样式
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        // 显示变化的进度
        new Thread(){
            @Override
            public void run() {
                int count = 20;

                // 设置最大进度，这样后面setProgress()就可以简单用当前进度+1了
                dialog.setMax(count);

                for (int i = 0; i < count; i++) {
                    try {
                        // 常用的模拟操作方法，让线程休眠Thread.sleep()
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    dialog.setProgress(dialog.getProgress() + 1);
                }

                // 移除对话框
                dialog.dismiss();

            }
        }.start();
    }

    /*
    * 显示日期选择对话框
    * */
    public void showDatePickerDialog(View view){
        // 取得当前日期的方式
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        // 要注意Calendar对象返回的month是不是差1，我在两个模拟器上试的结果都是不需要加1
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        Log.i("TAG", "Today: " + year + "-" + month + "-" + dayOfMonth);

        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                // 实际开发中在这里要处理用户的日期选择操作，这里简单用输出信息代替了
                Log.i("TAG", "Set Date: " + year + "-" + month + "-" + dayOfMonth);
            }
        }, year,month,dayOfMonth);

        dialog.show();
    }

    /*
     * 显示时间选择对话框
     * */
    public void showTimePickerDialog(View view){
        // 获得当前时间，作为TimePickerDialog的初始显示
        Calendar calendar = Calendar.getInstance();

        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        Log.i("TAG", "Now: " + hourOfDay + ":" + minute);

        TimePickerDialog dialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                // 对时间选择的处理，这里简单用Log信息代替
                Log.i("TAG", "Set Time: " + hourOfDay + ":" + minute);
            }
        }, hourOfDay, minute, true);

        dialog.show();

    }

}