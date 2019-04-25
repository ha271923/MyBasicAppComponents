package sample.hawk.com.mybasicappcomponents.permission;

import android.os.Bundle;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.permission.activity.BasePermissionActivity;

public class PermissionActivity2 extends BasePermissionActivity {
    
    private static final String TAG = "Activity2";
    
    @Override
    protected String[] getRequiredPermissions() {
        return new String[] {
                // Contacts group
                android.Manifest.permission.READ_CONTACTS, // Contacts display name, EmailAddressAdapter
                
                // Calendar
                android.Manifest.permission.READ_CALENDAR, // Propose meeting
                
                // Storage group
                android.Manifest.permission.READ_EXTERNAL_STORAGE, // Show Attachment 
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE, // Save Attachment
                
        };
    }
    
    @Override
    protected String[] getDesiredPermissions() {
        return new String[] {
                // Contacts group
                android.Manifest.permission.READ_CONTACTS, 
                
                // Calendar
                android.Manifest.permission.READ_CALENDAR,
                
                // Storage group
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
        };
    }
    
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        
        setContentView(R.layout.permission_activity_2);
    }
}
