package com.wlj.l04_datastorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class InnerfileActivity extends AppCompatActivity {

    private Button btn_inner_save;
    private Button btn_inner_read;
    private ImageView iv_inner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_innerfile);

        btn_inner_save = (Button) findViewById(R.id.btn_inner_save);
        btn_inner_read = (Button) findViewById(R.id.btn_inner_read);
        iv_inner = (ImageView) findViewById(R.id.image_inner);
    }

    /*
    * 从assets目录下读取文件，写入手机的内部files存储中
    * */
    public void innerfileSave(View view) throws IOException {
        // 获取AssetManager
        AssetManager assets = getAssets();
        // 读取文件，获取InputStream
        // 注意，要保证文件在这个assets目录下：<project name>\app\src\main\assets
        InputStream inputStream = assets.open("spongebob.png");
        // 获取FileOutputStream
        // 文件的路径在手机的这里：/data/data/<package name>/files/
        FileOutputStream fileOutputStream = openFileOutput("spongebob.png", Context.MODE_PRIVATE);

        // 边读边写
        byte[] buffer = new byte[1024];
        int len = -1;
        while((len = inputStream.read(buffer)) != -1){
            fileOutputStream.write(buffer, 0, len);
        }

        // 关闭流
        fileOutputStream.close();
        inputStream.close();

        // 提示
        Toast.makeText(this, "文件保存完成", Toast.LENGTH_LONG).show();
    }

    public void innerfileRead(View view){
        String dirpath = getFilesDir().getAbsolutePath();
        String imagepath = dirpath + "/spongebob.png";

        // 根据文件名创建Bitmap对象
        Bitmap image = BitmapFactory.decodeFile(imagepath);

        iv_inner.setImageBitmap(image);
    }

}