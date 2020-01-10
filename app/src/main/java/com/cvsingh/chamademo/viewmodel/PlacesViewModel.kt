package com.cvsingh.chamademo.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.cvsingh.chamademo.model.PlaceModel

class PlacesViewModel: ViewModel() {
    private val postTitle = MutableLiveData<String>()
    private val postBody = MutableLiveData<String>()

    fun bind(post: PlaceModel){
        postTitle.value = post.name
        postBody.value = "Rating: "+post.rating
    }

    fun getPostTitle():MutableLiveData<String>{
        return postTitle
    }

    fun getPostBody():MutableLiveData<String>{
        return postBody
    }
}