package com.cvsingh.chamademo.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import android.view.View
import com.cvsingh.chamademo.R
import com.cvsingh.chamademo.api.ApiService
import com.cvsingh.chamademo.model.Location
import com.cvsingh.chamademo.model.PlaceModel
import com.cvsingh.chamademo.utils.BASE_URL
import com.cvsingh.chamademo.view.PlacesListAdapter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

class PlacesListViewModel(): ViewModel(){

    val placesListAdapter: PlacesListAdapter = PlacesListAdapter()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage:MutableLiveData<Int> = MutableLiveData()

    private lateinit var subscription: Disposable

    init{
        loadPosts()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun loadPosts(){
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

        //Build a Retrofit object//
        val requestInterface = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

            //Get a usable Retrofit object by calling .build()//
            .build().create(ApiService::class.java)

        val location = "28.5355,77.3910"
        subscription =
            requestInterface.getPlacesList(
                "28.5355,77.3910",
                15000,
                "Restaurant",
                //"cruise",
                "AIzaSyDOJEpZU5bnyGrXKPj8gSM4w_IndnyeqRg"
            )
         .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrievePostListStart() }
                .doOnTerminate { onRetrievePostListFinish() }
                .subscribe(

                        { result -> onRetrievePostListSuccess(result.results) },
                        { onRetrievePostListError() }
                )
    }

    private fun onRetrievePostListStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrievePostListFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrievePostListSuccess(resultsList:List<PlaceModel>){
        placesListAdapter.updatePostList(resultsList)
    }

    private fun onRetrievePostListError(){
        loadingVisibility.value = View.GONE
        errorMessage.value = R.string.error_msg
    }
}