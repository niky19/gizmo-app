package com.example.gizmoapp.retrofit

import com.example.gizmoapp.model.RegisterRequest
import com.example.gizmoapp.model.User
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

interface ApiService {
    @GET()
    suspend fun getUsers(@Url url : String): Response<List<User>>
    @POST("register")
    suspend fun registerUser(@Body request: RegisterRequest): Response<User>

    companion object {
        val BASE_URL = "http://localhost:1337/api/users/"

        fun create(): ApiService {
            val client = OkHttpClient.Builder().build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}