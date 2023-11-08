package com.example.gizmo_app.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.gizmoapp.UserViewModel
import com.example.gizmoapp.repository.UserRepository
import com.example.gizmoapp.retrofit.ApiService
import com.example.gizmoapp.ui.theme.GizmoAppTheme


@Composable
fun HomeScreenForm(navController: NavController, viewModel: ViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "GIZMO", color = MaterialTheme.colorScheme.primary
        )
        Spacer(Modifier.size(32.dp))
        Button(onClick = { //TODO: Navegacion al loginscreen)
        })
        {
            Text("Entrar")
        }
        Spacer(Modifier.size(16.dp))
        Button(onClick = {//TODO:Navegacion al registerscreen
        }) {
            Text("Registrarse")
        }
    }
}

@Preview(showBackground = true, device = "id:Nexus One", showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    GizmoAppTheme {
        // HomeScreenForm()
    }
}
