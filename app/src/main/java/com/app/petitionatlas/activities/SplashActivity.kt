package com.app.petitionatlas.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.app.petitionatlas.R
import com.app.petitionatlas.sharedvalues.SharedValues


class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT = 3000
    lateinit var sharedValues: SharedValues
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        sharedValues = SharedValues(this@SplashActivity)
        Handler().postDelayed({
//            if (ContextCompat.checkSelfPermission(
//                    this@SplashActivity,
//                    Manifest.permission.ACCESS_FINE_LOCATION
//                )
//                == PackageManager.PERMISSION_GRANTED
//            ) {
                if (sharedValues.getpreferance(SharedValues.USERID)?.trim()?.length!! > 0) {
                    val intent = Intent(this@SplashActivity, HomeActivity::class.java)
                    intent.flags =
                        Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                } else {
                    val intent = Intent(this@SplashActivity, LoginActivity::class.java)
                    intent.flags =
                        Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                }
//            } else {
//                val intent = Intent(this@SplashActivity, CheckPermissions::class.java)
//                intent.flags =
//                    Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
//                startActivity(intent)
//            }
        }, SPLASH_TIME_OUT.toLong())
    }
}
