package com.wlj.l04_datastorage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    // 重写构造器
    // 在构造器中建数据库
    public DBHelper(@Nullable Context context, int version) {
        super(context, "lea.db", null, 1);
    }

    // 重写onCreate()
    // SQLiteDatabase完成JDBC中Connection和PreparedStatement的功能
    // 在此方法中可以做两件事：1. 建表；2. 插入一些初始数据；
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // 观察此回调函数什么时候会被调用
        Log.e("TAG", "DBHelper onCreate() executed...");

        // 建表
        String sql = "create table person(_id integer primary key autoincrement, name varchar, age int)";
        sqLiteDatabase.execSQL(sql);

        // 插入一些初始数据；
        sqLiteDatabase.execSQL("insert into person(name,age) values('Tom',18)");
        sqLiteDatabase.execSQL("insert into person(name,age) values('Nancy',20)");
        sqLiteDatabase.execSQL("insert into person(name,age) values('Bob',25)");
    }

    // 重写onUpgrade()
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // 观察此回调函数什么时候会被调用
        Log.e("TAG", "DBHelper onUpgrade() executed...");

        // 实际上什么也没做？？？
    }
}
