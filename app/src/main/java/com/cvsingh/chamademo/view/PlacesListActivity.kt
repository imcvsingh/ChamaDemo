package com.cvsingh.chamademo.view

import androidx.lifecycle.Observer
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cvsingh.chamademo.R
import com.cvsingh.chamademo.api.ApiService
import com.cvsingh.chamademo.databinding.ActivityPlacesListBinding
import com.cvsingh.chamademo.model.PlaceModel
import com.cvsingh.chamademo.view.adapters.PlacesListAdapter
import com.cvsingh.chamademo.viewmodel.PlacesListViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class PlacesListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlacesListBinding
    private lateinit var viewModel: PlacesListViewModel

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_places_list)
        binding.rvPlacesList.layoutManager =
            LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
            )

        viewModel = ViewModelProvider(this).get(PlacesListViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer {
                //errorMessage -> if(errorMessage != null) showError(errorMessage) else hideError()
        })

        viewModel.type.value = intent.getStringExtra("key")
        binding.viewModel = viewModel

    }
}
