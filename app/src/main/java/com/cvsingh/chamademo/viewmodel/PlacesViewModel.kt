package com.cvsingh.chamademo.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.cvsingh.chamademo.model.PlaceModel

class PlacesViewModel: ViewModel() {
    private val title = MutableLiveData<String>()
    private val address = MutableLiveData<String>()
    private val rating = MutableLiveData<String>()
    private val timing = MutableLiveData<Boolean>()
    private val distance = MutableLiveData<String>()


    fun bind(model: PlaceModel){
        title.value = model.name
        address.value = "B-21, Sec 12, Noida"
        timing.value = model.opening_hours.open_now
        rating.value = "Rating: "+model.rating
        distance.value = "4.5 mi"
    }

    fun getTitle():MutableLiveData<String>{
        return title
    }

    fun getAddress():MutableLiveData<String>{
        return address
    }

    fun getRating():MutableLiveData<String>{
        return rating
    }

    fun getTiming():MutableLiveData<Boolean>{
        return timing
    }

    fun getDistance():MutableLiveData<String>{
        return distance
    }


}