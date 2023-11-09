package com.example.gizmoapp.model.question
//TODO IMPROVE OPTION TO BE A LIST OF STRING
data class Question(
    val statement: String?,
    val answer: Int?,
    val options: List<String?>,
    ) {
}