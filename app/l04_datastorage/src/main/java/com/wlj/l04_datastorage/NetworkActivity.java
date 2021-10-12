package com.wlj.l04_datastorage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class NetworkActivity extends AppCompatActivity {

    private TextView et_network_url;
    private TextView et_network_url_param;
    private TextView et_network_response;
    private Button btn_network_get;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);

        et_network_url = findViewById(R.id.et_network_url);
        et_network_url_param = findViewById(R.id.et_network_url_param);
        et_network_response = findViewById(R.id.et_network_response);
        btn_network_get = findViewById(R.id.btn_network_get);

        // 写个默认值，避免测试时手动输入
        et_network_url.setText("http://192.168.2.14:8080/mytest/index.jsp");
//        et_network_url.setText("http://192.168.2.14:8080/_01simpleJSP/index.jsp");
        et_network_url_param.setText("?name=Tom&age=18");

    }

    /*
    * 使用HttpURLConnection方式提交GET请求
    * */
    public void testNetworkGetByHttpURLConnection(View view){
        // 1. 显示progress dialog
        ProgressDialog dialog = ProgressDialog.show(this, null,"正在请求中...");

        // 2. 启动分线程，采用方式一，Thread的匿名子类
        new Thread(){
            // 重写run()
            public void run(){
                // 3. 在分线程中建立连接，接收响应
                // 3.1 得到path，并带上参数
                String path = et_network_url.getText().toString() + et_network_url_param.getText().toString();

                try {
                    // 3.2 创建URL对象
                    URL url = new URL(path);
                    // 3.3 openConnection()，得到HttpURLConnection对象
                    // 默认返回URLConnection没有setRequestMethod()方法，所以需要强转为HttpURLConnection类
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    // 3.4 setRequestMethod(), setConnectionTimeout, setReadTimeout()
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(6000);

                    // 3.5 connect()
                    connection.connect();

                    // 3.6 getResponseCode()
                    int responseCode = connection.getResponseCode();
                    if(200 == responseCode){
                        // 3.7 getInputStream(), read(buffer)
                        // 3.8 new ByteArrayOutputStream(), write(buffer)
                        InputStream inputStream = connection.getInputStream();
                        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                        byte[] buffer = new byte[1024];
                        int len = -1;
                        while((len = inputStream.read(buffer)) != -1){
                            outputStream.write(buffer, 0, len);
                        }
                        String result = outputStream.toString();

                        outputStream.close();
                        inputStream.close();

                        // 4. 在主线程中显示结果，并移除进度显示
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                et_network_response.setText(result);
                                dialog.dismiss();
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    dialog.dismiss();
                }finally {
                    // 在这里关闭资源
                }

            }

        }.start();

    }

    /*
     * 使用HttpURLConnection方式提交POST请求
     * */
    public void testNetworkPostByHttpURLConnection(View view){
        // 1. 显示进度对话框
        // todo 这个带参数的show()方法已经不推荐使用了，看看替代方法
        ProgressDialog dialog = ProgressDialog.show(this, null, "正在请求中。。。");

        // 2. 启动分线程
        // 创建线程的第二种方式，实现Runnable接口
        new Thread(new Runnable() {
            // 3. 在分线程发送网络请求，得到网络响应
            @Override
            public void run() {
                // 1) 得到path，POST请求是不带参数的
                String path = et_network_url.getText().toString();
                OutputStream outputStream=null;
                InputStream inputStream=null;
                ByteArrayOutputStream outputStreamResponse=null;

                try {
                    // 2) 创建URL对象
                    URL url = new URL(path);
                    // 3) openConnection，得到HttpURLConnection对象
                    HttpURLConnection connection = (HttpURLConnection)url.openConnection();

                    // 4) 设置连接的参数
                    connection.setRequestMethod("POST");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);

                    // 5) 连接
                    connection.connect();

                    // 6) 发请求
                    //      获取输出流，写请求体: name=Bob&age=25
                    outputStream = connection.getOutputStream();
                    //String data = "name=Bob&age=25";
                    String data = et_network_url_param.getText().toString();
                    outputStream.write(data.getBytes("utf-8")); // String类型转为byte数组，即：byte[]
                    // todo 下面这段自动生成的代码看一下什么意思，和字符集有关
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                        outputStream.write(data.getBytes(StandardCharsets.UTF_8));
//                    }

                    //      得到响应码，如果是200则读取响应体
                    int responseCode = connection.getResponseCode();
                    if(200 == responseCode){
                        inputStream = connection.getInputStream();
                        outputStreamResponse = new ByteArrayOutputStream();
                        byte[] buffer = new byte[1024];
                        int len = -1;
                        while((len = inputStream.read(buffer)) != -1){
                            // 边读边写
                            outputStreamResponse.write(buffer, 0, len);
                        }

                        String result = outputStreamResponse.toString();

                        // 在finally()中统一关闭资源
//                        outputStreamResponse.close();
//                        inputStream.close();
//                        outputStream.close();

                        // 4. 在主线程显示响应结果，并移除进度对话框
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                et_network_response.setText(result);
                                //dialog.dismiss();
                            }
                        });
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if(outputStreamResponse != null){
                        try {
                            outputStreamResponse.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if(inputStream != null){
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if(outputStream != null){
                        try {
                            outputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    // 移除进度对话框
                    dialog.dismiss();
                }
            }
        }).start();

    }

    /*
     * 使用HttpClient方式提交GET请求
     * */
    public void testNetworkGetByHttpClient(View view){
        Toast.makeText(this, "DefaultHttpClient is deprecated and removed from API level 23",
                Toast.LENGTH_LONG).show();

        // 从Android 6.0 (API level 23)开始，已经移除了DefaultHttpClient()，不再处理这种方式
        //new DefaultHttpClient();
    }

    /*
     * 使用HttpClient方式提交POST请求
     * */
    public void testNetworkPostByHttpClient(View view){
        Toast.makeText(this, "DefaultHttpClient is deprecated and removed from API level 23",
                Toast.LENGTH_LONG).show();

    }

    /*
     * 使用Volley方式提交GET请求
     * */
    public void testNetworkGetByVolley(View view){}

    /*
     * 使用Volley方式提交POST请求
     * */
    public void testNetworkPostByVolley(View view){}

}