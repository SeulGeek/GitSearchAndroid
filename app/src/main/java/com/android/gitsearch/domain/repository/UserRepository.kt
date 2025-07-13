package com.android.gitsearch.domain.repository

import com.android.gitsearch.domain.model.User

interface UserRepository {
    suspend fun searchUsers(query: String): List<User>
}