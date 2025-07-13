package com.android.gitsearch.data.remote.repository

import com.android.gitsearch.data.remote.api.GitHubApi
import com.android.gitsearch.domain.model.User
import com.android.gitsearch.domain.repository.UserRepository

class UserRepositoryImpl(
    private val api: GitHubApi
) : UserRepository {
    override suspend fun searchUsers(query: String): List<User> {
        val response = api.searchUsers(query)

        return response.items.map {
            User(
                id = it.id,
                userName = it.userName,
                avatarUrl = it.avatarUrl
            )
        }
    }
}