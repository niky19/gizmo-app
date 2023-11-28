package com.example.gizmoapp.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.gizmoapp.R
import com.example.gizmoapp.repository.UserRepository
import com.example.gizmoapp.retrofit.ApiService
import com.example.gizmoapp.viewmodel.UserViewModel

@Composable
@Preview
fun ProfileScreenPreview() {
    val navController = rememberNavController()
    val userRepository = UserRepository(apiService = ApiService.create())
    val userViewModel = object : UserViewModel(userRepository) {}

    ProfileScreen(navController, userViewModel)
}

@Composable
fun ProfileScreen(navController: NavController, userViewModel: UserViewModel) {
    userViewModel.viewUserProfile.observeForever {
        if (it != null) {
            navController.navigate("login")
        }
    }
    val backgroundColor = Color(161722)
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
        color = backgroundColor
    ) {

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile_pic_lulu),
                //painter = rememberImagePainter(userProfile.value?.profilePictureUrl),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            )
            Spacer(Modifier.height(16.dp))

            Text(
                text = userViewModel.viewUserProfile.value?.username ?: "LULU",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

            Spacer(Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(16.dp)) {
                    Text(text = "Logros:")
                    Text(text = "${userViewModel.viewUserProfile.value?.achievements ?: 0}")
                }
                //Spacer(modifier = Modifier.width(8.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(16.dp)) {
                    Text(text = "Nivel:")
                    Text(
                        text = "${userViewModel.viewUserProfile.value?.level ?: 0}",
                        fontSize = 16.sp
                    )
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(16.dp)) {
                    Text(text = "Completados:")
                    Text(text = "${userViewModel.viewUserProfile.value?.problemsSolved ?: 0}")
                }
            }
            Spacer(Modifier.height(16.dp))

            Button(onClick = { /*TODO: Navigate to Edit Profile Screen*/ }) {
                Text(text = "Editar perfil")
            }
            Spacer(Modifier.height(16.dp))

            // Settings Button
            Button(onClick = { /*TODO: Navigate to Settings Screen*/ }) {
                Text(text = "Configuración")
            }
            Spacer(Modifier.height(16.dp))

            // Drop-down menu
            DropdownMenu(
                expanded = false,
                onDismissRequest = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth()
            ) {}
        }
    }
}

