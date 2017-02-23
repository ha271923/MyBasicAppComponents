package sample.hawk.com.mybasicappcomponents.ad.MyAdAdapter;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Trace;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;

import java.util.ArrayList;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

import static sample.hawk.com.mybasicappcomponents.ad.MyAdAdapter.Constants.ALL_ITEMS;
import static sample.hawk.com.mybasicappcomponents.utils.Util.isUiThread;
/**
 * Version1: Verify the data Loader using Contact data.
 *
 * */
public class MyListAd_Loader {
    private Context mActivity;
    ArrayList<MyListAd_Adapter.Contact> mContacts;
    private IMyListAd_CallBack mMyListAd_Adapter_Listener = null;

    public MyListAd_Loader(Context context) {
        mActivity = context;
    }

    public void setClassListener(@Nullable final IMyListAd_CallBack listener) {
        SMLog.i();
        mMyListAd_Adapter_Listener = listener;
    }

    public ArrayList<MyListAd_Adapter.Contact> getAllContacts(){
        mContacts = PhoneBookToArrayList();
        mMyListAd_Adapter_Listener.onAdLoaded(ALL_ITEMS);
        return mContacts;
    }

    ArrayList<MyListAd_Adapter.Contact> PhoneBookToArrayList(){
        Trace.beginSection("PhoneBookToArrayList");
        boolean isUI = isUiThread();
        SMLog.i("UI thread = "+isUI);
        ArrayList<MyListAd_Adapter.Contact> AllContacts = null;
        ContentResolver cr = mActivity.getContentResolver(); //Activity/Application android.content.Context
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        if(cursor.moveToFirst()) {
            AllContacts = new ArrayList<MyListAd_Adapter.Contact>();
            do {
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                if(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?",new String[]{ id }, null);
                    while (pCur.moveToNext()) {
                        MyListAd_Adapter.Contact contact = new MyListAd_Adapter.Contact();
                        contact.Phone = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        contact.Name = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                        AllContacts.add(contact);
                        break;
                    }
                    pCur.close();
                }
            } while (cursor.moveToNext()) ;
        }
        Trace.endSection();
        return AllContacts;
    }


}
