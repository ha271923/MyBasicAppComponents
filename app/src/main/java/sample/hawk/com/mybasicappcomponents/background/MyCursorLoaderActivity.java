package sample.hawk.com.mybasicappcomponents.background;

import android.Manifest;
import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.PermissionUtil;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/2/15.
 */

public class MyCursorLoaderActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor>, TextWatcher {
    private EditText editText = null;
    private ListView listView = null;
    private SimpleCursorAdapter adapter = null;
    private final int CURSOR_LOADER_ID = 1;

    public static final int READ_CONTACT_REQUEST_CODE = 7890;
    public void getPermission(String PERMISSION){
        if (!PermissionUtil.hasSelfPermission(this, PERMISSION)){
            if (shouldShowRequestPermissionRationale(PERMISSION)) {
            }
            requestPermissions(new String[]{PERMISSION}, READ_CONTACT_REQUEST_CODE);
        }
        else{
            initMyLoader();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == READ_CONTACT_REQUEST_CODE) {
            // We have requested multiple permissions, so all of them need to be checked.
            if (PermissionUtil.verifyPermissions(grantResults)) {
                SMLog.i( permissions[0] + " has been granted.");
                onNewIntent(getIntent()); // Got the permissions from the dialog
                Toast.makeText(this, "ALLOW:" + permissions[0], Toast.LENGTH_SHORT).show();
                initMyLoader();
            } else {
                SMLog.i( permissions[0] + " were NOT granted.");
                Toast.makeText(this, "DENY:" + permissions[0], Toast.LENGTH_LONG).show();
                //Utils.showGuideHome(getContext());    //
                finish();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myloader_activity);
        //绑定编辑框的文本变化事件
        editText = (EditText)findViewById(R.id.editText);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return false;
            }
        });
        editText.addTextChangedListener(this);

        listView = (ListView)findViewById(R.id.contactlist);
        adapter = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_list_item_2,
                null,
                new String[]{ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts.CONTACT_STATUS},
                new int[]{android.R.id.text1, android.R.id.text2},
                0);
        listView.setAdapter(adapter);

    }

    void initMyLoader(){
        //查询全部联系人
        Bundle args = new Bundle();
        args.putString("filter", null);
        LoaderManager lm = getLoaderManager();
        SMLog.i("LoaderManager.initLoader()");
        lm.initLoader(CURSOR_LOADER_ID, args, this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        SMLog.i();
        getPermission(Manifest.permission.READ_CONTACTS);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        SMLog.i();
        String filter = editText.getText().toString();

        LoaderManager lm = getLoaderManager();
        Bundle args = new Bundle();
        if(filter.equals("")){
            lm.restartLoader(CURSOR_LOADER_ID, null, this);
        }
        else {
            args.putString("filter", filter);
            lm.restartLoader(CURSOR_LOADER_ID, args, this);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        SMLog.i();
        Uri uri;
        String filter = args != null ? args.getString("filter") : null;
        SMLog.i("    querying SQL db ++++ TID="+Thread.currentThread().getId());
        if(filter != null){
            //根据用户指定的filter过滤显示
            uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_FILTER_URI, Uri.encode(filter));
        }else{
            uri = ContactsContract.Contacts.CONTENT_URI; //显示全部
        }
        String[] projection = new String[]{
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts.CONTACT_STATUS
        };
        String selection = "((" + ContactsContract.Contacts.DISPLAY_NAME + " NOTNULL) AND "+
                "(" + ContactsContract.Contacts.HAS_PHONE_NUMBER + " =1) AND "+
                "(" + ContactsContract.Contacts.DISPLAY_NAME + " != ''))";
        String sortOrder = ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC";
        CursorLoader cursorLoader = new CursorLoader(this, uri, projection, selection, null, sortOrder);
        SMLog.i("    querying SQL db --- TID="+Thread.currentThread().getId());
        return cursorLoader; // return value is Loader<Cursor>, Not Cursor!!!! CursorLoader extends AsyncTaskLoader<Cursor> extends Loader<Cursor>
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        SMLog.i();
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        SMLog.i();
        adapter.swapCursor(null);
    }
}