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
import com.example.gizmoapp.view.HomeScreen
import com.example.gizmoapp.repository.UserRepository
import com.example.gizmoapp.retrofit.ApiService
import com.example.gizmoapp.ui.theme.GizmoAppTheme
import com.example.gizmoapp.view.LoginScreen
import com.example.gizmoapp.view.MainMenuScreen
import com.example.gizmoapp.view.RegisterScreen
import com.example.gizmoapp.viewmodel.ZenGameViewModel

class MainActivity : ComponentActivity() {
    private val userRepository = UserRepository(ApiService.create())
    //private val viewModel = UserViewModel(userRepository)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GizmoAppTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = "auth") {
                    //Navegacion entre pantallas relacionadas con gestiones de usuario
                    navigation(
                        startDestination = "home",
                        route = "auth"
                    ) {
                        composable("home") {
                            val userViewModel = it.sharedViewModel<UserViewModel>(navController)
                            HomeScreen(navController, userViewModel)
                        }
                        composable("login") {
                            val userViewModel = it.sharedViewModel<UserViewModel>(navController)
                            LoginScreen(navController, userViewModel)
                        }
                        composable("register") {
                            val userViewModel = it.sharedViewModel<UserViewModel>(navController)
                            RegisterScreen(navController, userViewModel)
                        }
                        composable("forgot_password") {
                            val userViewModel = it.sharedViewModel<UserViewModel>(navController)
                            //TODO: Implementar recuperacion de contraseña
                        }
                    }

                    //Navegacion entre pantallas relacionadas con el modo de juego Zen
                    navigation(
                        startDestination = "game",
                        route = "zengamemode"
                    ) {
                        composable("main_menu") {
                            val viewModel = it.sharedViewModel<ZenGameViewModel>(navController)
                            MainMenuScreen(navController, viewModel)
                        }
                    }
                }

            }
        }
    }
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}

