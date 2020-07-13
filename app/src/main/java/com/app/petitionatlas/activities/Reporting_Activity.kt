package com.app.petitionatlas.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.petitionatlas.R
import kotlinx.android.synthetic.main.activity_reporting_.*

class Reporting_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reporting_)
        iv_backpress.setOnClickListener { view ->
            finish()
        }
    }
}
