package com.cvsingh.chamademo.api

import com.cvsingh.chamademo.model.Location
import com.cvsingh.chamademo.model.ResponseList
import com.cvsingh.chamademo.utils.BASE_URL
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface ApiService {

    @GET("/maps/api/place/nearbysearch/json")
    fun getPlacesList(
                 @Query("location") location: String? = null,
                 @Query("radius") radius: Int? = null,
                 @Query("type") type: String? = null,
                 @Query("key") key: String? = null): Observable<ResponseList>

    companion object {
        //val interceptor = HttpLoggingInterceptor()
        //interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        val okHttpClient = OkHttpClient.Builder()
            //.addInterceptor(interceptor)
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
    }

}