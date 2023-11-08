package com.example.gizmoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.gizmo_app.view.HomeScreenForm
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
                val navController = rememberNavController()
                NavHost(navController, startDestination = "home") {
                    navigation(
                        startDestination = "login",
                        route = "auth") {
                        composable("home") {

                        }
                        composable("login") {

                        }
                        composable("register") {

                        }
                        composable("forgot_password") {

                        }
                    }
                }
            }
        }
    }
}

@Composable
inline fun <reified T : ViewModel>  NavBackStackEntry.sharedViewModel(navController: NavController) : T {
    val navGraphRoute = destination.parent?.route?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}

