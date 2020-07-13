package com.app.petitionatlas.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.app.petitionatlas.R
import com.app.petitionatlas.sharedvalues.SharedValues

class CheckPermissions : AppCompatActivity() {

   companion object{
       private val REQUEST_CODE_PERMISSION = 2
   }


    var storagePermissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    var TAG = "CHECKPERMISSION_LOG"
    var count = 0
    var isSendToSettings = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        //checkPermissions();
//startActivity();
        if (ActivityCompat.checkSelfPermission(
                this,
                storagePermissions[0]
            ) != PackageManager.PERMISSION_GRANTED
            || ActivityCompat.checkSelfPermission(
                this,
                storagePermissions[1]
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            checkPermissions()
        } else {
            startActivity()
        }
    }


    private fun checkPermissions() {
        Log.d(TAG, "CountValue $count")
        if (count < 1) {
            try {
                if (ActivityCompat.checkSelfPermission(this, storagePermissions[0]) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, storagePermissions[1]) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, storagePermissions, REQUEST_CODE_PERMISSION)
                    Log.d(TAG, "storagePermissions")
                    count++
                    Log.d(TAG, "Count$count")
                } else {
                    Log.d(TAG, "ACTIVITY")
                    startActivity()
                }
            } catch (e: Exception) {
                Log.d(TAG, e.message)
                e.printStackTrace()
            }
        } else {
            startActivity()
            //            Log.d(TAG, "DIALOG");
//            AlertDialog.Builder alert = new AlertDialog.Builder(this);
//            alert.setCancelable(false);
//            alert.setMessage("Permission required to proceed further ");
//            alert.setPositiveButton("Allow permissions", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
////                    Toast.makeText(Permission.this, "check permission " + count, Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                    Uri uri = Uri.fromParts("package", getPackageName(), null);
//                    intent.setData(uri);
//                    startActivityForResult(intent, 101);
//                    isSendToSettings = true;
//                }
//            });
//            alert.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    Intent intent = new Intent(Intent.ACTION_MAIN);
//                    intent.addCategory(Intent.CATEGORY_HOME);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
//                    startActivity(intent);
//                    finish();
//                    System.exit(0);
//                }
//            });
//            alert.show();
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) { //Checking the request code of our request
        if (requestCode == REQUEST_CODE_PERMISSION) { //If permission is granted
            if (grantResults.size > 0) {
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    checkPermissions()
                    return
                } else if (grantResults[1] != PackageManager.PERMISSION_GRANTED) {
                    checkPermissions()
                    return
                } else {
                    startActivity()
                }
            } else {
                checkPermissions()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (isSendToSettings) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    storagePermissions[0]
                ) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(
                    this,
                    storagePermissions[1]
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                Log.d(TAG, "RESUME")
                checkPermissions()
            } else {
                Log.d(TAG, "RESUMECHECK")
                startActivity()
            }
            isSendToSettings = false
        }
    }

    private fun startActivity() { //   if (checkLocationOnOff()) {
        val sharedValues = SharedValues(this@CheckPermissions)
        if (sharedValues.getpreferance(SharedValues.USERID)?.trim()?.length!! > 0) { //            Intent i1 = new Intent(CheckPermissions.this, HomeActivity.class);
            val i1 = Intent(this@CheckPermissions, HomeActivity::class.java)
            i1.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(i1)
            finish()
        } else {
            val i1 = Intent(this@CheckPermissions, LoginActivity::class.java)
            i1.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(i1)
            finish()
        }
    }
}
