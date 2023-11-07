package com.example.gizmoapp.repository

import com.example.gizmoapp.model.LoginRequest
import com.example.gizmoapp.model.RegisterRequest
import com.example.gizmoapp.model.User
import com.example.gizmoapp.retrofit.ApiService

class UserRepository(private val apiService: ApiService) {


    private val apiInterface = ApiService.create()

    suspend fun getUsers(): List<User>? {
        val response = apiInterface.getUsers("http://localhost:1337/api/users/")
        return if (response.isSuccessful) response.body() else null
    }


    suspend fun registerUser(request: RegisterRequest) = apiService.registerUser(request)

    suspend fun findUser(loginRequest: LoginRequest): User? {
        val users = getUsers()
        return users?.find { it.username == loginRequest.username }
    }
}

