package com.cvsingh.chamademo.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.cvsingh.chamademo.R
import com.cvsingh.chamademo.databinding.ActivityPlacesListBinding
import com.cvsingh.chamademo.viewmodel.PlacesListViewModel

class PlacesListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlacesListBinding
    private lateinit var viewModel: PlacesListViewModel

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_places_list)
        binding.rvPlacesList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        viewModel = ViewModelProviders.of(this).get(PlacesListViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer {
                //errorMessage -> if(errorMessage != null) showError(errorMessage) else hideError()
        })
        binding.viewModel = viewModel
    }
}
