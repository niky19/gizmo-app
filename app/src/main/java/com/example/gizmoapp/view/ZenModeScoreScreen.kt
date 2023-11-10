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
fun ZenModeScoreScreeen(navController: NavController, viewModel: ZenGameViewModel) {

    val scoreText = "Your score is"


    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = scoreText, style = MaterialTheme.typography.labelLarge)

            Spacer(modifier = Modifier.height(32.dp))
            Text(text = viewModel._score.toString(), style = MaterialTheme.typography.labelLarge)
        }

    }
}

