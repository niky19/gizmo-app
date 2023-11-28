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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gizmoapp.model.question.Question
import com.example.gizmoapp.repository.ZenGameRepository
import com.example.gizmoapp.viewmodel.ZenGameViewModel


@Composable
fun ZenModeScreen(navController: NavController, viewModel: ZenGameViewModel) {
    
    var statement by remember { mutableStateOf(viewModel._questions.value?.get(viewModel.questionIndex)?.statement) }
    var option1 by remember {
        mutableStateOf(
            viewModel._questions.value?.get(viewModel.questionIndex)?.options?.get(
                0
            )
        )
    }
    var option2 by remember {
        mutableStateOf(
            viewModel._questions.value?.get(viewModel.questionIndex)?.options?.get(
                1
            )
        )
    }
    var option3 by remember {
        mutableStateOf(
            viewModel._questions.value?.get(viewModel.questionIndex)?.options?.get(
                2
            )
        )
    }
    var option4 by remember {
        mutableStateOf(
            viewModel._questions.value?.get(viewModel.questionIndex)?.options?.get(
                3
            )
        )
    }

    fun updateText(question: Question) {
        statement = question.statement
        option1 = question.options[0]
        option2 = question.options[1]
        option3 = question.options[2]
        option4 = question.options[3]
    }

    // Actualiza el estado utilizando el parámetro de la función
    fun answerQuestion(answer: Int) {
        if (viewModel._questions.value?.get(viewModel.questionIndex)?.answer == answer) {
            viewModel._score += 1
        }
        viewModel.questionIndex += 1
        if (viewModel.questionIndex < viewModel._questions.value?.size!!) {
            updateText(viewModel._questions.value?.get(viewModel.questionIndex)!!)
        } else {
            navController.navigate("score")
        }
    }





    Box(
        modifier = Modifier.fillMaxSize(),
        //.border(width = 4.dp, color = Blue, shape = RoundedCornerShape(16.dp))
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = statement.toString(),
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(18.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = { answerQuestion(0) }) { Text(text = option1.toString()) }

            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = { answerQuestion(1) }) { Text(text = option2.toString()) }

            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = { answerQuestion(2) }) { Text(text = option3.toString()) }

            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = { answerQuestion(3) }) {
                Text(text = option4.toString())
            }
        }
    }
}
