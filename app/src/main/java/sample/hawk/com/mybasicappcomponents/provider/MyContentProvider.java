package sample.hawk.com.mybasicappcomponents.provider;

import java.util.HashMap;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by Hawk_Wei on 2016/3/16.
 */
public class MyContentProvider extends ContentProvider { // Hawk: UI thread, however the ContentProvider class doesn't support UI component.
    private static final String TAG = "[MyContentProvider]";
    DBHelper dbHelper;
    private SQLiteDatabase database;

    @Override
    public boolean onCreate() { // Hawk: UI thread, however the receiver class doesn't support UI component
        // TODO Auto-generated method stub
        SMLog.i();
        Context context = getContext();
        dbHelper = new DBHelper(context);
        // permissions to be writable
        database = dbHelper.getWritableDatabase();

        if(database == null)
            return false;
        else
            return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO Auto-generated method stub
        SMLog.i();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        // the TABLE_NAME to query on
        queryBuilder.setTables(TABLE_NAME);
        SMLog.i("[Hawk]",uri.toString());
        switch (uriMatcher.match(uri)) {
            // maps all database column names
            case FRIENDS:
                queryBuilder.setProjectionMap(BirthMap);
                break;
            case FRIENDS_ID:
                queryBuilder.appendWhere( columnConst.ID + "=" + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        if (sortOrder == null || sortOrder == ""){
            // No sorting-> sort on names by default
            sortOrder = columnConst.NAME;
        }
        Cursor cursor = queryBuilder.query(database, projection, selection,
                selectionArgs, null, null, sortOrder);
        /**
         * register to watch a content URI for changes
         */
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO Auto-generated method stub
        SMLog.i();

        long row = database.insert(TABLE_NAME, null, values);

        // If record is added successfully
        if(row > 0) {
            Uri newUri = ContentUris.withAppendedId(CONTENT_URI, row);
            SMLog.i("[Hawk]",newUri.toString());
            getContext().getContentResolver().notifyChange(newUri, null);  // Hawk: inform UI thread
            return newUri;
        }
        throw new SQLException("Fail to add a new record into " + uri);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO Auto-generated method stub
        SMLog.i();
        int count = 0;

        switch (uriMatcher.match(uri)){
            case FRIENDS:
                count = database.update(TABLE_NAME, values, selection, selectionArgs);
                break;
            case FRIENDS_ID:
                count = database.update(TABLE_NAME, values, columnConst.ID +
                        " = " + uri.getLastPathSegment() +
                        (!TextUtils.isEmpty(selection) ? " AND (" +
                                selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI " + uri );
        }
        getContext().getContentResolver().notifyChange(uri, null); // Hawk: inform UI thread
        return count;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // TODO Auto-generated method stub
        SMLog.i();
        int count = 0;

        switch (uriMatcher.match(uri)){
            case FRIENDS:
                // delete all the records of the table
                count = database.delete(TABLE_NAME, selection, selectionArgs);
                break;
            case FRIENDS_ID:
                String id = uri.getLastPathSegment();	//gets the id
                count = database.delete( TABLE_NAME, columnConst.ID +  " = " + id +
                        (!TextUtils.isEmpty(selection) ? " AND (" +
                                selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null); // Hawk: inform UI thread
        return count;


    }

    @Override
    public String getType(Uri uri) {
        // TODO Auto-generated method stub
        SMLog.i();
        SMLog.i("[Hawk]",uri.toString());
        switch (uriMatcher.match(uri)){
            case FRIENDS:     // Get all friend-birthday records
                return "vnd.android.cursor.dir/vnd."+"sample.hawk.com.mybasicappcomponents.provider.Birthday"+".friends";
            case FRIENDS_ID:  // Get a particular friend
                return "vnd.android.cursor.item/vnd."+"sample.hawk.com.mybasicappcomponents.provider.Birthday"+".friends";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    // For DBHelper class ------------------------------------------------------------------------------------

    // fields for my content provider
    static final String PROVIDER_NAME = "sample.hawk.com.mybasicappcomponents.provider.Birthday";
    static final String URL = "content://" + PROVIDER_NAME + "/friends";
    public static final Uri CONTENT_URI = Uri.parse(URL);

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

    // integer values used in content URI
    static final int FRIENDS = 1;
    static final int FRIENDS_ID = 2;

    // projection map for a query
    private static HashMap<String, String> BirthMap;

    // maps content URI "patterns" to the integer values that were set above
    static final UriMatcher uriMatcher;
    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "friends", FRIENDS);
        uriMatcher.addURI(PROVIDER_NAME, "friends/#", FRIENDS_ID);
    }

    // database declarations
    static final String DATABASE_NAME = "BirthdayReminder";
    static final String TABLE_NAME = "birthTable";
    static final int DATABASE_VERSION = 1;
    static final String CREATE_TABLE =
            " CREATE TABLE " + TABLE_NAME +
                    " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " name TEXT NOT NULL, " +
                    " birthday TEXT NOT NULL);";

    // class that creates and manages the provider's database
    private static class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context) {
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
            SMLog.w(DBHelper.class.getName(),
                    "Upgrading database from version " + oldVersion + " to "
                            + newVersion + ". Old data will be destroyed");
            db.execSQL("DROP TABLE IF EXISTS " +  TABLE_NAME);
            onCreate(db);
        }


    }


    @Override
    public Bundle call(String method, String arg, Bundle extras) {
        SMLog.i("MyBundleContentProvider call() +++ " + method);
        Bundle bundle=new Bundle();
        bundle.putString("returnCall", "call被执行了");
        SMLog.i("MyBundleContentProvider call() ---");
        return bundle;
    }


}