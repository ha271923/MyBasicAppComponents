package sample.hawk.com.mybasicappcomponents.provider;

import android.content.ContentValues;
import android.net.Uri;
import android.content.ContentProvider;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyContentProvider2 extends ContentProvider {
    private static final String AUTHORITY = "sample.hawk.com.mybasicappcomponents.provider.MyContentProvider2";
    private static final UriMatcher mUriMatcher;
    private static final int URI_TYPE_TABLE1 = 1;
    private static final int URI_TYPE_TABLE2 = 2;
    MyDBHelper mHelper = null;

    static {
        mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mUriMatcher.addURI(AUTHORITY, MyDBHelper._DB_TABLE1, URI_TYPE_TABLE1);
        mUriMatcher.addURI(AUTHORITY, MyDBHelper._DB_TABLE2, URI_TYPE_TABLE2);
    }

    private class MyDBHelper extends SQLiteOpenHelper {
        public static final String _DB_NAME = "MyContentProvider2.db";
        public static final String _DB_TABLE1 = "MyTable1";
        public static final String _DB_TABLE2 = "MyTable2";
        public static final int _DB_VERSION = 1;

        public MyDBHelper(Context context) {
            super(context, _DB_NAME, null, _DB_VERSION);
        }

        public MyDBHelper(Context context, String name, CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + _DB_TABLE1 + " ( _ID INTEGER PRIMARY KEY, _DATE DATETIME NULL )");
            db.execSQL("CREATE TABLE " + _DB_TABLE2 + " ( _ID INTEGER PRIMARY KEY, _DATA VARCHAR(50) NULL )");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + _DB_TABLE1);
            db.execSQL("DROP TABLE IF EXISTS " + _DB_TABLE2);
        }
    }

    private void genTestingData() {
        SQLiteDatabase mDB = mHelper.getWritableDatabase();
        // MyDBHelper._DB_TABLE1
        mDB.execSQL("INSERT INTO " + MyDBHelper._DB_TABLE1 + " (_DATE) VALUES (datetime('now'))");
        // MyDBHelper._DB_TABLE2
        ContentValues values = new ContentValues();
        values.put("_DATA", "Hello World!");
        mDB.insertOrThrow(MyDBHelper._DB_TABLE2, null, values);
        mDB.close();
    }

    @Override
    public boolean onCreate() {
        // TODO Auto-generated method stub
        mHelper = new MyDBHelper(getContext());
        return true;
    }

    @Override
    public String getType(Uri uri) {
        // TODO Auto-generated method stub
        switch (mUriMatcher.match(uri)) {
            case URI_TYPE_TABLE1:
                return "vnd.android.cursor.item/vnd." + AUTHORITY + "." + MyDBHelper._DB_TABLE1; // one row
            case URI_TYPE_TABLE2:
                return "vnd.android.cursor.dir/vnd." + AUTHORITY + "." + MyDBHelper._DB_TABLE2; // multiple rows
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        // TODO Auto-generated method stub
        genTestingData();
        Cursor c = null;
        SQLiteDatabase mDB = null;
        switch (mUriMatcher.match(uri)) {
            case URI_TYPE_TABLE1: // item
                mDB = mHelper.getWritableDatabase();
                c = mDB.rawQuery("SELECT * FROM " + MyDBHelper._DB_TABLE1 + " ORDER BY _ID DESC LIMIT 1", null);
                c.moveToFirst();
                break;
            case URI_TYPE_TABLE2: // dir
                mDB = mHelper.getWritableDatabase();
                c = mDB.rawQuery("SELECT * FROM " + MyDBHelper._DB_TABLE2, null);
                c.moveToFirst();
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        return c;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int delete(Uri arg0, String arg1, String[] arg2) {
        // TODO Auto-generated method stub
        return 0;
    }
}

