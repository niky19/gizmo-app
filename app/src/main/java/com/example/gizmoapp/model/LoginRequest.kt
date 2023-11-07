package com.example.gizmoapp.model

data class LoginRequest(
    var username: String = "",
    //var email: String = "",
    var password: String = "",
    var remember: Boolean = false
) {
    fun isNotEmpty(): Boolean {
        return username.isNotEmpty() && password.isNotEmpty()
    }
}