package com.example.gizmoapp.model

data class RegisterRequest(
    var username: String = "",
    var email: String = "",
    var password: String = "",
    var repeatPassword: String = "",
    var remember: Boolean = false
) {
    fun isNotEmpty(): Boolean {
        return username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && repeatPassword.isNotEmpty()
    }
}