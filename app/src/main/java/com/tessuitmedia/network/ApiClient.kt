package com.tessuitmedia.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://reqres.in/api/"
    private  lateinit var apiService: ApiService

    val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NTdkYmM4NzY3N2QyOTBhM2VmNGJhYzQiLCJpYXQiOjE3MDMwNTI4MDQsImV4cCI6MTcwMzMxMjAwNH0.4Xk99iKXjAAt3OEAeMju6eoC0emPE_USrY88XPt5Mn0"
    val client = OkHttpClient.Builder().apply {
        addInterceptor{chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
            chain.proceed(request)
        }

    }.build()
    fun getApi(): ApiService{
        if(!::apiService.isInitialized) {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

           apiService = retrofit.create(ApiService::class.java)
        }
        return  apiService
    }


}

