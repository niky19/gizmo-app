package com.example.gizmoapp.view

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gizmoapp.repository.ZenGameRepository
import com.example.gizmoapp.viewmodel.ZenGameViewModel


@Composable
fun ZenModeScreen(navController: NavController, viewModel: ZenGameViewModel) {

    var questionCount = 0
    val currentQuestion = viewModel._questions.value?.get(questionCount)

    val option1 = currentQuestion?.options?.get(0)
    val option2 = currentQuestion?.options?.get(1)
    val option3 = currentQuestion?.options?.get(2)
    val option4 = currentQuestion?.options?.get(3)


    Box(
        modifier = Modifier
            .fillMaxSize()
            .border(width = 4.dp, color = Blue, shape = RoundedCornerShape(16.dp)),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .border(width = 4.dp, color = Blue, shape = RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Spacer(modifier = Modifier.height(32.dp))
                Button(onClick = {

                }) { Text(text = option1.toString()) }

                Spacer(modifier = Modifier.height(32.dp))
                Button(onClick = {}) { Text(text = option2.toString()) }

                Spacer(modifier = Modifier.height(32.dp))
                Button(onClick = {}) { Text(text = option3.toString()) }

                Spacer(modifier = Modifier.height(32.dp))
                Button(onClick = {}) {
                    Text(text = option4.toString())

                }

            }
        }
    }
}