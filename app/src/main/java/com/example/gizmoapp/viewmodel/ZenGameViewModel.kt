package com.example.gizmoapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.example.QuestionResponse
import com.example.gizmoapp.model.question.Question
import com.example.gizmoapp.repository.ZenGameRepository
import kotlinx.coroutines.launch

class ZenGameViewModel(private val zenGameRepository: ZenGameRepository) : ViewModel() {
    var _questions = MutableLiveData<List<Question>>()
    fun getQuestions() {
        println("MA MAN IS GETTING")
            viewModelScope.launch {
                try {
                    val result = zenGameRepository.getQuestions()
                    println("tuptu $result")
                    if(result?.data?.size != 0) {
                        println(mapQuestions(result!!))
                        _questions.value = mapQuestions(result!!)
                    }
                } catch (e: Exception) {
                    println(e.message)
                }
            }
    }

    private fun mapQuestions(questionResponse: QuestionResponse): List<Question> {
        return  questionResponse.data.map {
            Question(it.attributes?.statement, it.attributes?.answer, listOf(it.attributes?.option1, it.attributes?.option2, it.attributes?.option3, it.attributes?.option4))
         }

    }


}