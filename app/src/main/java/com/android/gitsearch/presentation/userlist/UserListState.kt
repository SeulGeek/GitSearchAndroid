package com.android.gitsearch.presentation.userlist

import com.android.gitsearch.domain.model.User

data class UserListState(
    val query: String = "",
    val users: List<User> = emptyList()
)