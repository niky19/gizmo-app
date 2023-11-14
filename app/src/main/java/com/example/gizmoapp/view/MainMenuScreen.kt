package com.example.gizmoapp.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.gizmoapp.repository.ZenGameRepository
import com.example.gizmoapp.retrofit.ApiService
import com.example.gizmoapp.viewmodel.ZenGameViewModel

@Composable
fun MainMenuScreen(navController: NavController, viewModel: ZenGameViewModel) {

    viewModel._questions.observeForever {
        if (it != null) {
            println(viewModel._questions.value)
            navController.navigate("game")
        }
    }


    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "GIZMO", style = MaterialTheme.typography.labelLarge)
            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = { viewModel.getQuestions() }) {
                Text(text = "Jugar")
            }
        }
            ExtendedFloatingActionButton(

                onClick = { navController.navigate("profile") }) {

            }
        }
    }


    @Preview
    @Composable
    fun MainMenuScreenPreview() {
        val navController = rememberNavController()

        val apiInterface = ApiService.create()

        val zenGameRepository: ZenGameRepository = ZenGameRepository(apiInterface)
        val viewModel = ZenGameViewModel(zenGameRepository)
        MainMenuScreen(navController = navController, viewModel = viewModel)
    }
