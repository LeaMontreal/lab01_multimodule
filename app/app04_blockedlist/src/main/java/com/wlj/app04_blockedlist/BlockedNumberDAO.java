package com.wlj.app04_blockedlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/*
* 对应操作数据库表blocked_number的DAO类
* */
public class BlockedNumberDAO {

    private DBHelper dbHelper;
    public BlockedNumberDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    /*
    * 增加一条记录
    * */
    public void add(BlockedNumber blockedNumber){
        // 1. 得到数据库连接
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        // 2. 执行SQL "insert into blocked_number(number) values("5145823654")"
        // 把实体类对象blockedNumber的数据装入ContentValues（是个实现了Map接口的类？）对象中
        // todo 怎么看ContentValues类实现了Map接口？
        ContentValues values = new ContentValues();
        values.put("number", blockedNumber.getNumber());
        // 插入新增记录，insert()返回新增加记录的id
        long rowId = database.insert("blocked_number", null, values);

        Log.i("TAG", "BlockedNumberDAO add() id=" + rowId);

        // 注意，在add()中就把传入的blockedNumber中的_id改成正确的，
        // 这样在addBlockedNumber()调用add()后，传入的blockedNumber对象的_id属性就已经是正确的了
        blockedNumber.setId((int)rowId);

        // 3. 关闭资源
        database.close();
    }

    /*
     * 根据id删除一条记录
     * */
    public void deleteById(int id){
        // 1. 得到数据库连接
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        // 2. 执行SQL delete from blocked_number where _id=2
            // 注意delete()中whereClause的写法，方式一：使用占位符?
        int deleteCount = database.delete("blocked_number", "_id=?", new String[]{id + ""});
        Log.i("TAG", "BlockedNumberDAO deleteById() deleteCount=" + deleteCount);

        // 3. 关闭资源
        database.close();
    }

    /*
     * 更新一条记录
     * */
    public void update(BlockedNumber blockedNumber){
        // 1. 得到数据库连接
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        // 2. 执行SQL update blocked_number set number=xxx where _id=2
        ContentValues values = new ContentValues();
        values.put("number", blockedNumber.getNumber());
        // 注意whereClause的写法，方式二：不使用占位符
        int count = database.update("blocked_number", values, "_id=" + blockedNumber.getId(), null);
        Log.i("TAG", "BlockedNumberDAO update() updated count=" + count);

        // 3. 关闭资源
        database.close();
    }

    /*
     * 查询所有记录，封装成List<BlockedNumber>
     * */
    public List<BlockedNumber> getAll(){
        // 1、创建一个List对象，用于返回查询到的所有数据
        List<BlockedNumber> list = new ArrayList<>();

        // 2、查询数据库
        //      2.1 获取数据库连接
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        //      2.2 数据库查询
        // todo 每个参数的含义还需要仔细读一下
        // orderBy – How to order the rows, formatted as an SQL ORDER BY clause (excluding the ORDER BY itself).
        // Passing null will use the default sort order, which may be unordered.
        // orderBy "_id desc"表示按_id降序，就是先显示后插入的黑名单
        Cursor cursor = database.query("blocked_number", null, null, null, null, null, "_id desc", null);

        //      2.3 用Cursor对象遍历所有记录行
        while (cursor.moveToNext()){
            // 和数据库中存储的顺序相同
            // 取出id
            int id = cursor.getInt(0);

            // 取出number
            String number = cursor.getString(1);

            // 3、结果加入list中
            list.add(new BlockedNumber(id, number));
        }

        //      2.4 关闭资源
        cursor.close();
        database.close();

        //
        return list;
    }
}
