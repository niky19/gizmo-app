package com.example.gizmoapp.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Surface
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
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
import kotlinx.coroutines.launch

@Composable
@Preview
fun ProfileScreenPreview() {
    val navController = rememberNavController()
    val userRepository = UserRepository(apiService = ApiService.create())
    val userViewModel = object : UserViewModel(userRepository) {}

    ProfileScreen(navController, userViewModel)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProfileScreen(navController: NavController, userViewModel: UserViewModel) {
    userViewModel.viewUserProfile.observeForever {
        if (it != null) {
            navController.navigate("login")
        }
    }
    val backgroundColor = Color(161722)
    Surface(
        modifier = Modifier.fillMaxSize()
        // .background(backgroundColor), color = backgroundColor
    ) {

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 52.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile_pic_lulu),
                //painter = rememberImagePainter(userProfile.value?.profilePictureUrl),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .padding(12.dp)
                    .size(100.dp)
                    .clip(CircleShape)
            )
            Spacer(Modifier.height(12.dp))

            Text(
                text = userViewModel.viewUserProfile.value?.username ?: "LULU",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

            Spacer(Modifier.height(10.dp))
            Text(text = "Aprendiz", fontSize = 12.sp)
            Spacer(Modifier.height(18.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    verticalArrangement = Arrangement.Center, modifier = Modifier.width(90.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "${userViewModel.viewUserProfile.value?.achievements ?: 0}")

                    }

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "Logros")
                    }
                }

                Spacer(Modifier.width(16.dp))

                Column(
                    verticalArrangement = Arrangement.Center, modifier = Modifier.width(90.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "${userViewModel.viewUserProfile.value?.level ?: 0}")
                    }

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Text(text = "Nivel")
                    }
                }

                Spacer(Modifier.width(16.dp))

                Column(
                    verticalArrangement = Arrangement.Center, modifier = Modifier.width(90.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "${userViewModel.viewUserProfile.value?.problemsSolved ?: 0}")
                    }

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "Completados")

                    }
                }
            }
            Spacer(Modifier.height(16.dp))

            Button(onClick = { /*TODO: Navigate to Edit Profile Screen*/ }) {
                Text(text = "Editar perfil")
            }
            Spacer(Modifier.height(16.dp))


            // Drop-down menu
            DropdownMenu(
                expanded = false,
                onDismissRequest = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth()
            ) {}


            val state = rememberPagerState { 10 }
            val animationScope = rememberCoroutineScope()
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(42.dp),
                    

                ) {
                    Button(onClick = {
                        animationScope.launch {
                            state.animateScrollToPage(state.currentPage)
                        }
                    }) {
                        Text(text = "achievements")
                    }
                    Button(onClick = {
                        animationScope.launch {
                            state.animateScrollToPage(state.currentPage + 1)
                        }
                    }) {
                        Text(text = "progess")
                    }
                }
                HorizontalPager(
                    modifier = Modifier.weight(0.7f), state = state
                ) { page ->
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2), modifier = Modifier.fillMaxSize()
                    ) {
                        items(10) {
                            Box(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .size(100.dp)
                                    .background(Color(0xFF2D2F33))
                            )
                        }
                    }
                    Text(
                        text = "Page: $page", modifier = Modifier.fillMaxWidth()
                    )
                }

            }


        }


    }
}