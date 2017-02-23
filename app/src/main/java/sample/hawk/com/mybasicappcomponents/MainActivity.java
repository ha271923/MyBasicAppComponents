/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sample.hawk.com.mybasicappcomponents;

import android.Manifest;
import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sample.hawk.com.mybasicappcomponents.utils.PermissionUtil;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;
import sample.hawk.com.mybasicappcomponents.utils.Util;

public class MainActivity extends ListActivity {
    String mAppVersion;
    private static final int PERMISSIONS_REQUEST_CODE = 1234;
    private static final String[] RequiredPermissions= {Manifest.permission.READ_CONTACTS,Manifest.permission.WRITE_EXTERNAL_STORAGE};

    public void getPermission(String[] permissions){
        if (!PermissionUtil.hasSelfPermission(this, permissions)){
            if (shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS)) {
                Toast.makeText(this, "READ_CONTACTS is required!", Toast.LENGTH_SHORT).show();
            }
            requestPermissions(permissions, PERMISSIONS_REQUEST_CODE);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_CODE) {
            // We have requested multiple permissions, so all of them need to be checked.
            if (PermissionUtil.verifyPermissions(grantResults)) {
                SMLog.i( permissions[0] + " has been granted.");
                onNewIntent(getIntent()); // Got the permissions from the dialog
                Toast.makeText(this, "ALLOW:" + permissions[0], Toast.LENGTH_SHORT).show();
            } else {
                SMLog.i( permissions[0] + " were NOT granted.");
                Toast.makeText(this, "DENY:" + permissions[0], Toast.LENGTH_LONG).show();
                finish();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Util.getAppVersion(mContext, "com.google.android.webview");
        mAppVersion = Util.getAppVersion(this, this.getPackageName());
        SMLog.i("App version: " + mAppVersion);
        Util.appendLog(mAppVersion);

        Intent intent = getIntent();
        String path = intent.getStringExtra("sample.hawk.com.mybasicappcomponents.Path");
        
        if (path == null) {
            path = "";
        }

        setListAdapter(new SimpleAdapter(this, getData(path),
                android.R.layout.simple_list_item_1, new String[] { "title" },
                new int[] { android.R.id.text1 }));
        getListView().setTextFilterEnabled(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getPermission(RequiredPermissions);
    }

    protected List<Map<String, Object>> getData(String prefix) {
        List<Map<String, Object>> myData = new ArrayList<Map<String, Object>>();

        Intent mainIntent = new Intent("android.intent.action.HAWK_UI", null);
        mainIntent.addCategory("android.intent.category.HAWK_CODE");

        PackageManager pm = getPackageManager();
        List<ResolveInfo> list = pm.queryIntentActivities(mainIntent, 0);

        if (null == list)
            return myData;

        String[] prefixPath;
        String prefixWithSlash = prefix;
        
        if (prefix.equals("")) {
            prefixPath = null;
        } else {
            prefixPath = prefix.split("/");
            prefixWithSlash = prefix + "/";
        }
        
        int len = list.size();
        
        Map<String, Boolean> entries = new HashMap<String, Boolean>();

        for (int i = 0; i < len; i++) {
            ResolveInfo info = list.get(i);
            CharSequence labelSeq = info.loadLabel(pm);
            String label = labelSeq != null
                    ? labelSeq.toString()
                    : info.activityInfo.name;
            
            if (prefixWithSlash.length() == 0 || label.startsWith(prefixWithSlash)) {
                
                String[] labelPath = label.split("/");

                String nextLabel = prefixPath == null ? labelPath[0] : labelPath[prefixPath.length];

                if ((prefixPath != null ? prefixPath.length : 0) == labelPath.length - 1) {
                    addItem(myData, nextLabel, activityIntent(
                            info.activityInfo.applicationInfo.packageName,
                            info.activityInfo.name));
                } else {
                    if (entries.get(nextLabel) == null) {
                        addItem(myData, nextLabel, browseIntent(prefix.equals("") ? nextLabel : prefix + "/" + nextLabel));
                        entries.put(nextLabel, true);
                    }
                }
            }
        }

        Collections.sort(myData, sDisplayNameComparator);
        
        return myData;
    }

    private final static Comparator<Map<String, Object>> sDisplayNameComparator =
        new Comparator<Map<String, Object>>() {
        private final Collator collator = Collator.getInstance();

        public int compare(Map<String, Object> map1, Map<String, Object> map2) {
            return collator.compare(map1.get("title"), map2.get("title"));
        }
    };

    protected Intent activityIntent(String pkg, String componentName) {
        Intent result = new Intent();
        result.setClassName(pkg, componentName);
        return result;
    }
    
    protected Intent browseIntent(String path) {
        Intent result = new Intent();
        result.setClass(this, MainActivity.class);
        result.putExtra("sample.hawk.com.mybasicappcomponents.Path", path);
        return result;
    }

    protected void addItem(List<Map<String, Object>> data, String name, Intent intent) {
        Map<String, Object> temp = new HashMap<String, Object>();
        temp.put("title", name);
        temp.put("intent", intent);
        data.add(temp);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Map<String, Object> map = (Map<String, Object>)l.getItemAtPosition(position);

        Intent intent = new Intent((Intent) map.get("intent"));
        intent.addCategory(Intent.CATEGORY_SAMPLE_CODE);
        printCurrentActivityName(intent);
        startActivity(intent);
    }

    private void printCurrentActivityName(Intent intent){
        // Hawk: print the current activity name on logcat
        String NowClassName = intent.getComponent().getClassName();
        String RootClassName = "sample.hawk.com.mybasicappcomponents.MainActivity";
        if(NowClassName.equals(RootClassName))
            ;
        else
            Log.e("Hawk:" , NowClassName);
    }

}
