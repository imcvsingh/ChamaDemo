package com.cvsingh.chamademo

import android.app.Application
import com.cvsingh.chamademo.utils.AppPreferences

class ChamaApp : Application() {

    override fun onCreate() {
        super.onCreate()
        AppPreferences.init(this)
    }
}