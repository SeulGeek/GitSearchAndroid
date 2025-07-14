package com.android.gitsearch.domain.repository

import com.android.gitsearch.domain.model.Repository
import com.android.gitsearch.domain.model.User
import com.android.gitsearch.domain.model.UserDetail

interface UserRepository {
    suspend fun searchUsers(query: String): List<User>
    suspend fun getUserDetail(userName: String): UserDetail
    suspend fun getUserRepos(userName: String): List<Repository>
}