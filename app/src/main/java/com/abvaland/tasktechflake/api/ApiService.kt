package com.abvaland.tasktechflake.api

import com.abvaland.tasktechflake.model.VideoRes
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("gifs/search")
    fun getVideos(@Query("api_key") apiKey : String,
                  @Query("q") query : String,
                  @Query("limit") limit : Int,
                  @Query("offset") offset : Int,
                  @Query("rating") rating : String,
                  @Query("lang") lang : String
                  ) : Observable<Response<VideoRes>>
}