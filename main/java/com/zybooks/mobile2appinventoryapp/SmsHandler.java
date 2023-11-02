package com.zybooks.mobile2appinventoryapp;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.telephony.SmsManager;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class SmsHandler {

    private static final int SMS_PERMISSION_CODE = 1001;
    private Activity activity;

    public SmsHandler(Activity activity) {
        this.activity = activity;
    }

    public boolean checkForSmsPermission() {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.SEND_SMS}, SMS_PERMISSION_CODE);
            return true;  // Indicates permission is being requested
        } else {
            Toast.makeText(activity, "SMS permission already granted.", Toast.LENGTH_SHORT).show();
            return false;  // Indicates permission is already granted
        }
    }


    public void handlePermissionResult(int requestCode, int[] grantResults) {
        if (requestCode == SMS_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(activity, "SMS permission granted.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(activity, "SMS permission denied. App will run without SMS notifications.", Toast.LENGTH_SHORT).show();
            }

            // Navigate to HomeActivity
            Intent intent = new Intent(activity, HomeActivity.class);
            activity.startActivity(intent);
            activity.finish();
        }
    }

}
