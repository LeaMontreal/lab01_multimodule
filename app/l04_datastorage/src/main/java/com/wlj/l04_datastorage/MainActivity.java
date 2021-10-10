package com.wlj.l04_datastorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Button btn_sp;
    private Button btn_innerfile;
    private Button btn_externalfile;
    private Button btn_database;
    private Button btn_network;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_sp = (Button)findViewById(R.id.btn_sp);
        btn_innerfile = (Button)findViewById(R.id.btn_innerfile);
        btn_externalfile = (Button)findViewById(R.id.btn_externalfile);
        btn_database = (Button)findViewById(R.id.btn_database);
        btn_network = (Button)findViewById(R.id.btn_network);


    }

    public void sharedpreferenceStorage(View view){
        Intent intent = new Intent(this, SharedpreferenceActivity.class);
        startActivity(intent);
    }

    public void innerfileStorage(View view){
        Intent intent = new Intent(this, InnerfileActivity.class);
        startActivity(intent);
    }

    public void externalfileStorage(View view){
//        Toast.makeText(this,"externalfileStorage start...", Toast.LENGTH_LONG).show();
//        Log.e("TAG", "externalfileStorage start...");
        Intent intent = new Intent(this, ExternalActivity.class);
        startActivity(intent);

    }
    public void databaseStorage(View view){
        //Toast.makeText(this,"databaseStorage start...", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, DBStorageActivity.class);
        startActivity(intent);

    }
    public void networkStorage(View view){
//        Toast.makeText(this,"networkStorage start...", Toast.LENGTH_LONG).show();
        startActivity(new Intent(this, NetworkActivity.class));
    }
}