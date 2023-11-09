@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.gizmoapp.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
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
import com.example.gizmoapp.viewmodel.UserViewModel
import com.example.gizmoapp.model.RegisterRequest

@Composable
fun RegisterScreen(navController: NavController, viewModel: UserViewModel) {
    RegisterScreenForm(navController = navController, viewModel = viewModel)
}

@Composable
fun RegisterScreenForm(
    navController: NavController, viewModel: ViewModel
) {
    val userViewModel: UserViewModel = viewModel as UserViewModel
    Surface {

        var registerRequest by remember { mutableStateOf(RegisterRequest()) }
        val context = LocalContext.current
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp)
        ) {
            UsernameField(
                value = registerRequest.username,
                onChange = { data -> registerRequest = registerRequest.copy(username = data) },
                modifier = Modifier.fillMaxWidth(),
                label = "Username"
            )
            Spacer(Modifier.height(16.dp))
            EmailField(
                value = registerRequest.email,
                onChange = { data -> registerRequest = registerRequest.copy(email = data) },
                modifier = Modifier.fillMaxWidth(),
                label = "Email"
            )
            Spacer(Modifier.height(16.dp))
            NewPasswordField(
                value = registerRequest.password,
                onChange = { data -> registerRequest = registerRequest.copy(password = data) },
                modifier = Modifier.fillMaxWidth(),
                label = "Password",
            )
            Spacer(Modifier.height(16.dp))
            RepeatPasswordField(
                value = registerRequest.repeatPassword,
                onChange = { data ->
                    registerRequest = registerRequest.copy(repeatPassword = data)
                },
                modifier = Modifier.fillMaxWidth(),
                label = "Repeat Password",
            )

            Spacer(Modifier.height(22.dp))
            Button(
                onClick = {
                    userViewModel.registerUser(
                        registerRequest.username, registerRequest.email, registerRequest.password
                    )
                },
                enabled = registerRequest.isNotEmpty(),
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Register")
            }
            Spacer(Modifier.height(16.dp))
            Text("¿Estás registrado?")
            ClickableText(text = AnnotatedString("Iniciar sesión"), onClick = {
                navController.navigate("login")
            })
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailField(
    value: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Email",
    placeholder: String = ""
) {
    val focusManager = LocalFocusManager.current
    val leadingIcon = @Composable {
        Icon(
            Icons.Default.Email, contentDescription = "", tint = MaterialTheme.colorScheme.primary
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
fun NewPasswordField(
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

@Composable
fun RepeatPasswordField(
    value: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Repeat Password",
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
        keyboardActions = KeyboardActions(onDone = { /* Handle the action on "Done" */ }),
        placeholder = { Text(placeholder) },
        label = { Text(label) },
        singleLine = true,
        visualTransformation = if (isPassVisible) VisualTransformation.None else PasswordVisualTransformation()
    )
}

@Preview
@Composable
fun RegisterFormPreview() {
    //RegisterForm()
}