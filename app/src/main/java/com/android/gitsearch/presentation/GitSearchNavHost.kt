package com.android.gitsearch.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.gitsearch.presentation.navigation.Screen
import com.android.gitsearch.presentation.userdetail.UserDetailScreenWithViewModel
import com.android.gitsearch.presentation.userlist.UserListScreenWithViewModel

@Composable
fun GitSearchNavHost() {
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

            UserDetailScreenWithViewModel(
                navController = navController,
                userName = userName
            )
        }

    }
}