package com.example.gizmoapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gizmoapp.model.question.Question
import com.example.gizmoapp.repository.ZenGameRepository

class ZenGameViewModel(private val zenGameRepository: ZenGameRepository) : ViewModel() {
    private val _questions = MutableLiveData<List<Question>>()
    suspend fun getQuestions() = zenGameRepository.getQuestions()
}