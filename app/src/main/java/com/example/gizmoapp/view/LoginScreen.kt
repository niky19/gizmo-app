@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.gizmoapp.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.gizmoapp.MainActivity
import com.example.gizmoapp.viewmodel.UserViewModel
import com.example.gizmoapp.model.LoginRequest
import com.example.gizmoapp.ui.theme.GizmoAppTheme

@Composable
fun LoginScreen(navController: NavController, viewModel: ViewModel) {
    val userViewModel: UserViewModel = viewModel as UserViewModel
    LoginForm(navController, userViewModel)
}

@Composable
fun LoginForm(
    navController: NavController, viewModel: ViewModel
) {
    val userViewModel: UserViewModel = viewModel as UserViewModel
    Surface {
        var loginRequest by remember { mutableStateOf(LoginRequest()) }
        val context = LocalContext.current
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp)
        ) {
            UsernameField(
                value = loginRequest.username,
                onChange = { data -> loginRequest = loginRequest.copy(username = data) },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(16.dp))
            PasswordField(
                value = loginRequest.password,
                onChange = { data -> loginRequest = loginRequest.copy(password = data) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(12.dp))
            LabeledCheckbox(
                label = "Remember Me", onCheckChanged = {
                    loginRequest = loginRequest.copy(remember = !loginRequest.remember)
                }, isChecked = loginRequest.remember
            )
            Spacer(Modifier.height(22.dp))
            Button(
                onClick = {
                    userViewModel.loginUser(loginRequest.username, loginRequest.password)
                },
                enabled = loginRequest.isNotEmpty(),
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Login")
            }
            Spacer(Modifier.height(16.dp))
            Text("¿No estás registrado?")
            ClickableText(text = AnnotatedString("Crear cuenta"), onClick = {
                navController.navigate("register")
            })
        }
    }
}

//Esto ya no lo usaré pero lo voy a dejar por si acaso
fun checkCredentials(creds: Credentials, context: Context) {
    if (creds.isNotEmpty() && creds.username == "admin") {
        context.startActivity(Intent(context, MainActivity::class.java))
        (context as Activity).finish()
    } else {
        Toast.makeText(context, "Wrong Credentials", Toast.LENGTH_SHORT).show()
    }
}

data class Credentials(
    var username: String = "", var pass: String = "", var remember: Boolean = false
) {
    fun isNotEmpty(): Boolean {
        return username.isNotEmpty() && pass.isNotEmpty()
    }
}


@Composable
fun LabeledCheckbox(label: String, onCheckChanged: () -> Unit, isChecked: Boolean) {
    Row(
        Modifier
            .clickable(onClick = onCheckChanged)
            .padding(4.dp)
    ) {
        Checkbox(checked = isChecked, onCheckedChange = null)
        Spacer(Modifier.size(6.dp))
        Text(label)
    }
}

@Composable
fun UsernameField(
    value: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Username",
    placeholder: String = ""
) {

    val focusManager = LocalFocusManager.current
    val leadingIcon = @Composable {
        Icon(
            Icons.Default.Person, contentDescription = "", tint = MaterialTheme.colorScheme.primary
        )
    }
    TextField(
        value = value,
        onValueChange = onChange,
        modifier = modifier,
        leadingIcon = leadingIcon,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
        placeholder = { Text(placeholder) },
        label = { Text(label) },
        singleLine = true,
        visualTransformation = VisualTransformation.None


    )
}

@Composable
fun PasswordField(
    value: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Password",
    placeholder: String = ""
) {
    var isPassVisible by remember { mutableStateOf(false) }

    val leadingIcon = @Composable {
        Icon(
            Icons.Default.Key, contentDescription = "", tint = MaterialTheme.colorScheme.primary
        )
    }

    val trailingIcon = @Composable {
        IconButton(onClick = { isPassVisible = !isPassVisible }) {
            Icon(
                if (isPassVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }


    TextField(
        value = value,
        onValueChange = onChange,
        modifier = modifier,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done, keyboardType = KeyboardType.Password
        ),
        placeholder = { Text(placeholder) },
        label = { Text(label) },
        singleLine = true,
        visualTransformation = if (isPassVisible) VisualTransformation.None else PasswordVisualTransformation()


    )
}


@Preview(showBackground = true, device = "id:Nexus One", showSystemUi = true)
@Composable
fun GreetingPreview() {
    GizmoAppTheme {
        //LoginForm()
    }
}