package com.wlj.app04_blockedlist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    // 重写带参的构造器，创建数据库
    //      2. 修改构造器参数列表，只留下context，其它都是固定的了
    public DBHelper(@Nullable Context context) {

        // 1. 调用父类的构造器创建数据库，提供数据库名和版本号
        super(context, "iCafe.db", null, 1);
    }

    // 重写方法，建表
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.i("TAG", "DBHelper onCreate()");

        // 建表blocked_number
        sqLiteDatabase.execSQL("create table blocked_number(_id integer primary key autoincrement, number varchar)");
    }

    // 重写方法
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
