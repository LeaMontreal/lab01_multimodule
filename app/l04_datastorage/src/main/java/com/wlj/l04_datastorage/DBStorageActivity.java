package com.wlj.l04_datastorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DBStorageActivity extends AppCompatActivity {
    private Button btn_create_db;
    private Button btn_update_db;
    private Button btn_insert;
    private Button btn_delete;
    private Button btn_update;
    private Button btn_query;
    private Button btn_transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbstorage);

        btn_create_db = findViewById(R.id.btn_create_db);
        btn_update_db = findViewById(R.id.btn_update_db);
        btn_insert = findViewById(R.id.btn_insert);
        btn_delete = findViewById(R.id.btn_delete);
        btn_update = findViewById(R.id.btn_update);
        btn_query = findViewById(R.id.btn_query);
        btn_transaction = findViewById(R.id.btn_transaction);
    }

    /*
    *  创建数据库，创建表
    * */
    public void createDB(View view){
        DBHelper dbHelper = new DBHelper(this, 1);
        SQLiteDatabase readableDatabase = dbHelper.getReadableDatabase();

        Log.e("TAG", "create DB...");
        // TODO 我认为把建表的工作放到这里来做更合适一些，对照一下黑名单练习中的实现方式
    }

    /*
    * 更新数据库版本
    * */
    public void updateDB(View view){
        DBHelper dbHelper = new DBHelper(this, 2);
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        Toast.makeText(this, "update DB version...", Toast.LENGTH_LONG).show();
    }

    public void insertRecord(View view){
        DBHelper dbHelper = new DBHelper(this, 2);
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        // insert into person(name, age) values('Cal', 23);
        ContentValues values = new ContentValues();
        values.put("name", "Cal");
        values.put("age", 23);
        long id = database.insert("person", null, values);

        database.close();

        Toast.makeText(this, "insert record, id="+id, Toast.LENGTH_LONG).show();
    }

    public void deleteRecord(View view){
        DBHelper dbHelper = new DBHelper(this, 2);
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        // sql: delete from person where _id=4;
        int count = database.delete("person", "_id=?", new String[]{"4"});

        database.close();

        Toast.makeText(this, "deleted "+count+" record", Toast.LENGTH_LONG).show();
    }

    public void updateRecord(View view){

    }

    public void queryRecord(View view){

    }

    public void testTransaction(View view){

    }
}