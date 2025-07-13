package com.android.gitsearch.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.gitsearch.presentation.navigation.Screen
import com.android.gitsearch.presentation.userlist.UserListScreenWithViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GitSearchApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.UserList.route
    ) {
        composable(Screen.UserList.route) {
            UserListScreenWithViewModel(
                onUserClick = { userName ->
                    navController.navigate(Screen.UserDetail.createRoute(userName))
                }
            )
        }

        composable(Screen.UserDetail.route) { backStackEntry ->
            val userName = backStackEntry.arguments?.getString("userName") ?: ""

            Scaffold(
                topBar = { TopAppBar(title = { Text("User Detail: $userName") }) }
            ) { innerPadding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    Text("This is $userName's detail Screen")
                }

            }
        }

    }
}