package sample.hawk.com.mybasicappcomponents;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by Hawk_Wei on 2016/3/16.
 */
public class MyContentProvider extends ContentProvider { // NOT UI thread
    final String TAG = "MyContentProvider";

    @Override
    public boolean onCreate() {
        SMLog.i();
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SMLog.i();
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        SMLog.i();
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SMLog.i();
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SMLog.i();
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SMLog.i();
        return 0;
    }
}
