package com.android.gitsearch.presentation.navigation

sealed class Screen(val route: String) {
    data object UserList : Screen("user_list")
    data object UserDetail : Screen("user_detail/{userName}") {
        fun createRoute(userName: String) = "user_detail/$userName"
    }
}