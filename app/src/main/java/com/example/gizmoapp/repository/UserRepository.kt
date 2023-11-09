package com.example.gizmoapp.repository

import com.example.gizmoapp.model.LoginRequest
import com.example.gizmoapp.model.RegisterRequest
import com.example.gizmoapp.model.User
import com.example.gizmoapp.retrofit.ApiService
import retrofit2.Response

class UserRepository(private val apiService: ApiService) {


    private val apiInterface = ApiService.create()

    suspend fun getUsers(): List<User>? {
        val response = apiInterface.getUsers("http://10.0.2.2:1337/api/users/")
        println("response: ${response.body()}")
        return if (response.isSuccessful) response.body() else null
    }


    suspend fun registerUser(request: RegisterRequest) = apiService.registerUser(request)


    suspend fun findUser(loginRequest: LoginRequest): User? {
        val users = getUsers()
        return users?.find { it.username == loginRequest.username }
    }
}

