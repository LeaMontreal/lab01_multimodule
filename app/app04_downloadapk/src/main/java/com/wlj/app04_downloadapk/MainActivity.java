package com.wlj.app04_downloadapk;

import static android.app.ProgressDialog.STYLE_HORIZONTAL;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    private Button btn_download_install;
    private File apkFile;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_download_install = findViewById(R.id.btn_download_install);
    }

    public void dowloadAndInstallApk(View view){
        // 1. 主线程，显示提示视图ProgressDialog
        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(STYLE_HORIZONTAL);
        dialog.setTitle("下载apk...");
        dialog.show();

        // 2. 启动分线程，请求下载apk文件，存入到外部存储空间中，下载过程中显示进度
        // 下载未完成时，主线程也是运行中的，只是没有执行安装apk和移除提示的功能
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                InputStream inputStream = null;
                FileOutputStream outputStream = null;

                // 创建File对象用于存储下载的apk文件
                // getExternalFilesDir()获得外部存储空间的根目录，我们把下载下来的apk文件放在这里
                // type – null for the root of the files directory
                String apkFilePath = getExternalFilesDir(null).getPath();
                //Log.i("TAG", "apk file: "+ apkFilePath+"/update.apk");
                apkFile = new File(getExternalFilesDir(null), "update.apk");

                // 网络请求
                String path = "http://192.168.2.14:8080/mytest/blockedlist.apk";
                try {
                    URL url = new URL(path);
                    connection = (HttpURLConnection) url.openConnection();

                    // connection.setRequestMethod("GET");  // 默认方式就是"GET"，可以省略
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(10000);

                    connection.connect();

                    int responseCode = connection.getResponseCode();
                    if(responseCode == 200){
                        // 要下载的文件大小可以由HttpURLConnection对象得到
                        dialog.setMax(connection.getContentLength());

                        inputStream = connection.getInputStream();
                        outputStream = new FileOutputStream(apkFile);
                        byte[] buffer = new byte[1024];
                        int len = -1;
                        while( (len = inputStream.read(buffer)) != -1){
                            outputStream.write(buffer, 0, len);

                            // 下载过程中显示进度
                            dialog.incrementProgressBy(len);

                            // 模拟网速慢
                            SystemClock.sleep(10);
                        }

                        outputStream.close();
                        inputStream.close();
                    }

                    connection.disconnect();

                    // 3. 主线程，移除提示视图，安装apk
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            dialog.dismiss();
                            installApk();
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if(outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if(inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if(connection != null)
                        connection.disconnect();
                }
            }
        }).start();


    }

    /*
    * 安装apk
    * */
    private void installApk(){
        //Log.i("TAG", "installApk()待实现");
        Intent intent = new Intent("android.intent.action.INSTALL_PACKAGE");

        // 在Intent对象中携带信息：要安装的文件，文件类型
        //setDataAndType(data, type)
        //        data – The Uri of the data this intent is now targeting.
        //        type – The MIME type of the data being handled by this intent.
        // 从File对象获得Uri对象
        intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");

        // 启动系统的安装Activity
        startActivity(intent);
    }
}