package com.cvsingh.chamademo.viewmodel

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import com.cvsingh.chamademo.R
import com.cvsingh.chamademo.view.PlacesListActivity

class MainActivityViewModel: ViewModel(), View.OnClickListener {


    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id){
                R.id.btCafe -> {
                    val intent = Intent(v.context, PlacesListActivity::class.java)
                    intent.putExtra("key", "CAFE")
                    v.context.startActivity(intent)
                }

                R.id.btRestaurant ->
                        Log.e("Click", "RESTAURANT")

                R.id.btBar ->
                    Log.e("Click", "BAR")


            }
        }

    }
}