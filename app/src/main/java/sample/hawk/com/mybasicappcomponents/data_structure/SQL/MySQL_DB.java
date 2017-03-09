package sample.hawk.com.mybasicappcomponents.data_structure.SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

// Excel-like table
//     |    A   |     B   |   C    |
//----------------------------------
//  1  |   id1  |   name1 | price1 |
//----------------------------------
//  2  |   id2  |   name2 | price2 |
//----------------------------------
//  3  |   id3  |   name3 | price3 |
//----------------------------------
//  4  |   id4  |   name4 | price4 |
//----------------------------------
public class MySQL_DB {
    private Context mContext = null;
    public SQLiteDatabase db = null;
    public final static String DATABASE_NAME = "MySQL_DB.db";
    public final static String TABLE_NAME = "table01";
    private final static String _ID = "_id";
    private final static String NAME = "name";
    private final static String PRICE = "price";
    private final static String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME +
            " (" + _ID   + " INTEGER PRIMARY KEY,"
                 + NAME  + " TEXT,"
                 + PRICE + " TEXT)";

    public MySQL_DB(Context ctx) {
        this.mContext = ctx;
    }
    //Create Or Open Table
    public void open() throws SQLException {
        db = mContext.openOrCreateDatabase(DATABASE_NAME, 0, null);
        try {
            db.execSQL(CREATE_TABLE);
        } catch (Exception e) {
        }
    }
    //關閉DB
    public void close() throws SQLException {
        db.close();
    }
    //查詢全部
    public Cursor getALL() {
        Cursor c = db.query(TABLE_NAME, new String[] { _ID, NAME, PRICE }, null, null, null, null, null);
        while (c.moveToNext()){
            SMLog.i("[GETALL]  "+ c.getString(0) +"  |  "+c.getString(1)+"  |  "+c.getString(2));
        }
        return c;
    }
    //單筆查詢
    public Cursor getsearchid(long id) throws SQLException {
        Cursor c = db.query(TABLE_NAME, new String[] { _ID, NAME, PRICE }, _ID + "=" + id, null, null, null, null, null);
        if (c != null && c.getCount() > 0) {
            c.moveToNext();
            SMLog.i("[getsearchid]  "+ c.getString(0) +"  |  "+c.getString(1)+"  |  "+c.getString(2));
        }
        return c;
    }
    //回傳long Type用來判斷新增是否成功和增加的筆數
    public long add(String name, String price) {
        ContentValues args = new ContentValues();
        args.put(NAME, name);
        args.put(PRICE, price);
        SMLog.i("[ADD] name= "+ name + " price= "+ price);
        return db.insert(TABLE_NAME, null, args);
    }
    public boolean delete(long id) {
        SMLog.i("[DEL] ID= "+ id);
        return db.delete(TABLE_NAME, _ID + "=" + id, null) > 0;
    }
    //boolean回傳1代表更新成功
    public boolean edit(long id, String name, String price) {
        ContentValues args = new ContentValues();
        args.put(NAME, name);
        args.put(PRICE, price);
        SMLog.i("[UPDATE] "+ id + "  |  " + name + "  |  " + price);
        return db.update(TABLE_NAME, args, _ID + "=" + id, null) > 0;
   }
}