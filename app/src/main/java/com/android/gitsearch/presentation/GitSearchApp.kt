package com.android.gitsearch.presentation

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.gitsearch.domain.model.Repository
import com.android.gitsearch.domain.model.UserDetail
import com.android.gitsearch.presentation.navigation.Screen
import com.android.gitsearch.presentation.userdetail.UserDetailScreen
import com.android.gitsearch.presentation.userlist.UserListScreenWithViewModel
import androidx.core.net.toUri

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

            // TODO: delete this (this is for testing)
            val dummyUser = UserDetail(
                avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
                userName = "judy",
                fullName = "hey judy",
                followers = 1000,
                following = 42
            )

            val dummyRepos = listOf(
                Repository(1, "grit", "Git library", "Ruby", 250, "https://github.com/mojombo/grit"),
                Repository(
                    2,
                    "semver",
                    "Semantic versioning",
                    "Go",
                    100,
                    "https://github.com/mojombo/semver"
                )
            )
            val context = LocalContext.current

            UserDetailScreen(
                navController = navController,
                userDetail = dummyUser,
                repositories = dummyRepos,
                onRepositoryClick = { repo ->
                    val intent = Intent(Intent.ACTION_VIEW, repo.htmlUrl.toUri())
                    context.startActivity(intent)
                }
            )
        }

    }
}