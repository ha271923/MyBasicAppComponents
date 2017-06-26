package sample.hawk.com.mybasicappcomponents.provider.SQL_provider;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.widget.ListView;

import sample.hawk.com.mybasicappcomponents.R;

public class SQL_provider_Loader_Activity extends FragmentActivity implements LoaderManager.LoaderCallbacks<Cursor>{
	
	/**
     * The authority we use to get to our sample provider.
     */
    public static final String AUTHORITY = "sample.hawk.com.mybasicappcomponents.provider.SQL_provider";
		
    private ListView mainList;
 // This is the Adapter being used to display the list's data.
    private SimpleCursorAdapter mAdapter;
    
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sql_provider_activity);
		mainList = (ListView) findViewById (R.id.main_list);
		
		// Create an empty adapter we will use to display the loaded data.
        mAdapter = new SimpleCursorAdapter(this,
                R.layout.sql_provider_item_list, null,
                new String[] { sample.hawk.com.mybasicappcomponents.provider.SQL_provider.ProductTable.COLUMN_NAME_DATA },
                new int[] { R.id.text_list }, 0);
        mainList.setAdapter(mAdapter);
		
		// 寫入資料
        ContentResolver cr = getContentResolver();
        for (int i=0;i<11;i++){
        	ContentValues values = new ContentValues();
        	values.put(sample.hawk.com.mybasicappcomponents.provider.SQL_provider.ProductTable.COLUMN_NAME_DATA, "cup_"+ Integer.toString(i));
        	cr.insert(sample.hawk.com.mybasicappcomponents.provider.SQL_provider.ProductTable.CONTENT_URI, values);
        }
		
        // Prepare the loader.  Either re-connect with an existing one,
        // or start a new one.
        getSupportLoaderManager().initLoader(0, null, this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sql_provider_menu, menu);
		return true;
	}
	
	static final String[] PROJECTION = new String[] {
        sample.hawk.com.mybasicappcomponents.provider.SQL_provider.ProductTable._ID,
        sample.hawk.com.mybasicappcomponents.provider.SQL_provider.ProductTable.COLUMN_NAME_DATA,
    };
	
	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
		CursorLoader cl = new CursorLoader(this, sample.hawk.com.mybasicappcomponents.provider.SQL_provider.ProductTable.CONTENT_URI,
                PROJECTION, null, null, null);
        return cl;
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
		mAdapter.swapCursor(data);
		
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		mAdapter.swapCursor(null);
		
	}

}
