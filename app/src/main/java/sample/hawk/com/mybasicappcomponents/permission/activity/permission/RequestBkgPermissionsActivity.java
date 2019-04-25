/*
 * Copyright (C) 2015 The Android Open Source Project
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

package sample.hawk.com.mybasicappcomponents.permission.activity.permission;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Activity that requests permissions needed for activities exported from Contacts.
 */
public class RequestBkgPermissionsActivity extends Activity {
    
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        
        RequestPemissionNotification.clearNotification(this);
        
        Intent it = getIntent();
        String[] desiredPermissions = it.getStringArrayExtra(RequestPemissionNotification.KEY_DESIRED_PERMISSION);
        
        RequestPermissionsActivity.startPermissionActivity(this, desiredPermissions, desiredPermissions);
        
        finish();
    }
}
