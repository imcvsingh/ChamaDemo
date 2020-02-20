package com.cvsingh.chamademo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cvsingh.chamademo.model.Opening_hours
import com.cvsingh.chamademo.model.PlaceModel

class PlacesViewModel: ViewModel() {
    private val title = MutableLiveData<String>()
    private val address = MutableLiveData<String>()
    private val rating = MutableLiveData<String>()
    private val timing = MutableLiveData<String>()
    private val distance = MutableLiveData<String>()


    fun bind(model: PlaceModel){
        title.value = model.name
        address.value = "B-21, Sec 12, Noida"
        timing.value="Open "
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

    fun getTiming():MutableLiveData<String>{

        if (true)
            timing.value = "Open"
        else
            timing.value = "Close"
        return timing
    }

    fun getDistance():MutableLiveData<String>{
        return distance
    }


}