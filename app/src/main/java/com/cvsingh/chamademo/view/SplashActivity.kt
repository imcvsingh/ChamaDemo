package com.cvsingh.chamademo.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.cvsingh.chamademo.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val intent = Intent(this, PlacesListActivity::class.java)
// To pass any data to next activity
        //intent.putExtra("keyIdentifier", value)
// start your next activity
        startActivity(intent)
        finish()
    }
}
