package com.cvsingh.chamademo.api

import com.cvsingh.chamademo.model.Location
import com.cvsingh.chamademo.model.ResponseList
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    companion object {
        const val ENDPOINT = "https://rebrickable.com/api/v3/"
        const val URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=28.5355,77.3910&radius=15000&type=restaurant&keyword=cruise&key=GoogleApiKey"
    }

    @GET("location/{location}/radius/{radius}/type/{type}/key/{key}/")
    suspend fun getPosts(@Path("location") location: Double? = null,
                              @Path("radius") radius: Int? = null,
                              @Path("type") type: String? = null,

                                               @Path("key") key: String? = null): Response<ResponseList>

    @GET("/maps/api/place/nearbysearch/json")
    fun getPlacesList(
                 @Query("location") location: String? = null,
                 @Query("radius") radius: Int? = null,
                 @Query("type") type: String? = null,
                // @Query("keyword") keyword: String? = null,
                 @Query("key") key: String? = null): Observable<ResponseList>

    @GET()
    fun getPost(): Observable<ResponseList>
}
