package com.android.gitsearch.domain.repository

import androidx.paging.PagingSource
import com.android.gitsearch.domain.model.Repository
import com.android.gitsearch.domain.model.User
import com.android.gitsearch.domain.model.UserDetail

interface UserRepository {
    fun searchUsersPaging(query: String): PagingSource<Int, User> // For Paging
    suspend fun getUserDetail(userName: String): UserDetail
    suspend fun getUserRepos(userName: String): List<Repository>
}