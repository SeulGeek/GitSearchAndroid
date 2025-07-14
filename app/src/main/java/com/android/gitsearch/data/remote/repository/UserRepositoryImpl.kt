package com.android.gitsearch.data.remote.repository

import androidx.paging.PagingSource
import com.android.gitsearch.data.remote.api.GitHubApi
import com.android.gitsearch.domain.model.Repository
import com.android.gitsearch.domain.model.User
import com.android.gitsearch.domain.model.UserDetail
import com.android.gitsearch.domain.repository.UserRepository

class UserRepositoryImpl(
    private val api: GitHubApi
) : UserRepository {
    override fun searchUsersPaging(query: String): PagingSource<Int, User> {
        return UserPagingResource(api, query)
    }

    override suspend fun getUserDetail(userName: String): UserDetail {
        val dto = api.getUserDetail(userName)

        return UserDetail(
            userName = dto.userName,
            avatarUrl = dto.avatarUrl,
            fullName = dto.fullName,
            followers = dto.followers,
            following = dto.following
        )
    }

    override suspend fun getUserRepos(userName: String): List<Repository> {
        val response = api.getUserRepos(userName)

        return response
            .filter { !it.fork }
            .map {
                Repository(
                    id = it.id,
                    repoName = it.repoName,
                    description = it.description,
                    language = it.language,
                    starCount = it.starCount,
                    htmlUrl = it.htmlUrl
                )
            }
    }
}