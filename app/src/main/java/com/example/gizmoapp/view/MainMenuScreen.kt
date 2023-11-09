package com.example.gizmoapp.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gizmoapp.viewmodel.ZenGameViewModel

@Composable
fun MainMenuScreen(navController: NavController, viewModel: ZenGameViewModel) {
    MainMenuForm(navController = navController, viewModel = viewModel)
}

@Composable
fun MainMenuForm(navController: NavController, viewModel: ZenGameViewModel) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "GIZMO", style = MaterialTheme.typography.labelLarge)
            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = { navController.navigate("login") }) {
                Text(text = "Jugar")
            }
        }
    }
}


/**
@Preview
@Composable
fun MainMenuFormPreview() {
MainMenuForm()
}
 **/