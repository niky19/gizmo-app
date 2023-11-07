package com.example.gizmoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.gizmoapp.repository.UserRepository
import com.example.gizmoapp.retrofit.ApiService
import com.example.gizmoapp.ui.theme.GizmoAppTheme

class MainActivity : ComponentActivity() {
    private val userRepository = UserRepository(ApiService.create())
    private val viewModel = UserViewModel(userRepository)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GizmoAppTheme {

            }
        }
    }
}

