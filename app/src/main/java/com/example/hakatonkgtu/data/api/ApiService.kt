package com.example.hakatonkgtu.data.api

import com.example.hakatonkgtu.data.model.MapDataResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("api/map-data/")
    suspend fun getMapData(): Response<MapDataResponse>
}

object RetrofitClient {
    private const val BASE_URL = "http://localhost:8000/" // Замените на актуальный URL

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}