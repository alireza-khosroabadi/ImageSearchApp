package com.alireza.searchimage.data.remote.apiService

import com.alireza.searchimage.data.remote.model.image.ImageListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/api")
suspend fun searchImage(@Query("key") key: String,
                        @Query("q") query: String,
                        @Query("page") page: Int,
                        @Query("per_page") perPageItem: Int): ImageListDto

}
