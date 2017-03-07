package sample.hawk.com.mybasicappcomponents.provider.DB;

/**
 * Created by ha271 on 2017/3/7.
 */

// For DBHelper class ------------------------------------------------------------------------------------

// fields for my content provider

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;


// class that creates and manages the provider's database
public class DB_Manager_Helper extends SQLiteOpenHelper {

    // column name for the database
    public static class columnConst{
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String BIRTHDAY = "birthday";
    }
    // Excel-like table
    //     |   A    |    B    |     C    |
    //------------------------------------
    //  1  |   id1  |   name1 | birthday1|
    //------------------------------------
    //  2  |   id2  |   name2 | birthday2|
    //------------------------------------
    //  3  |   id3  |   name3 | birthday3|
    //------------------------------------
    //  4  |   id4  |   name4 | birthday4|
    //------------------------------------

    // database declarations
    static final String DATABASE_NAME = "BirthdayReminder";
    static final String TABLE_NAME = "birthTable";
    static final int DATABASE_VERSION = 1;
    static final String CREATE_TABLE =
            " CREATE TABLE " + TABLE_NAME +
                    " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " name TEXT NOT NULL, " +
                    " birthday TEXT NOT NULL);";

    public DB_Manager_Helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
        SMLog.i();
    }
    // Initialize the Database
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        SMLog.i();
        db.execSQL(CREATE_TABLE); // SQL is creating an new empty table!

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        SMLog.i();
        SMLog.w("Upgrading database from version " + oldVersion + " to "
                        + newVersion + ". Old data will be destroyed");
        db.execSQL("DROP TABLE IF EXISTS " +  TABLE_NAME);
        onCreate(db);
    }

    // Reference from https://github.com/sanathp/DatabaseManager_For_Android/blob/master/helperFunction.txt
    public ArrayList<Cursor> getData(String Query){
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[] { "mesage" };
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2= new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);
        try{
            String maxQuery = Query ;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);
            //add value to cursor2
            Cursor2.addRow(new Object[] { "Success" });
            alc.set(1,Cursor2);
            if (null != c && c.getCount() > 0) {
                alc.set(0,c);
                c.moveToFirst();
                return alc ;
            }
            return alc;
        } catch(SQLException sqlEx){
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+sqlEx.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        } catch(Exception ex){
            Log.d("printing exception", ex.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+ex.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        }
    }

}
