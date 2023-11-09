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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gizmoapp.repository.ZenGameRepository
import com.example.gizmoapp.viewmodel.ZenGameViewModel


@Composable
fun ZenModeScreen(navController: NavController, viewModel: ZenGameViewModel) {

    var questionCount = 0
    val currentQuestion = viewModel._questions.value?.get(questionCount)


    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = currentQuestion?.statement.toString(),
                style = MaterialTheme.typography.labelLarge
            )
            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = {
                /// TODO IN
            }) { Text(text = "option1") }

            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = {
                /// TODO IN
            }) { Text(text = "option2") }

            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = {
                /// TODO IN
            }) { Text(text = "option3") }

            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = {
                /// TODO IN
            }) {Text(text = "option4")

            }

        }
    }
}