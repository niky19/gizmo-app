package com.example.gizmoapp.repository

import com.example.example.QuestionResponse
import com.example.gizmoapp.model.User
import com.example.gizmoapp.model.question.Question
import com.example.gizmoapp.retrofit.ApiService

class ZenGameRepository(private val apiService: ApiService) {
    private val apiInterface = ApiService.create()

    suspend fun getQuestions(): QuestionResponse? {
        val response = apiInterface.getQuestions("http://10.0.2.2:1337/api/questions/")
        println("$response  RESPONSE")
        return if (response.isSuccessful) response.body() else null
    }
}