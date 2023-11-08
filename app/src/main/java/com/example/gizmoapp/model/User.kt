package com.example.gizmoapp.model

import android.os.Parcelable

data class User(
    val id: Int,
    val username: String,
    val password: String,
    val email: String,
)