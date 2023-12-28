package com.tessuitmedia.network

import com.tessuitmedia.models.UserResponse
import retrofit2.http.GET

interface ApiService {

    @GET("users?page=1&per_page=10") // Ganti dengan endpoint yang benar
    fun getAllUser(): retrofit2.Call<UserResponse>
}

