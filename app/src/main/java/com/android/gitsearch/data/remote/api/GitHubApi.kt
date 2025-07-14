package com.android.gitsearch.data.remote.api

import com.android.gitsearch.data.remote.dto.RepositoryDto
import com.android.gitsearch.data.remote.dto.SearchUserResponse
import com.android.gitsearch.data.remote.dto.UserDetailDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApi {
    @GET("search/users")
    suspend fun searchUsers(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): SearchUserResponse

    @GET("users/{username}")
    suspend fun getUserDetail(
        @Path("username") userName: String
    ): UserDetailDto

    @GET("/users/{username}/repos")
    suspend fun getUserRepos(
        @Path("username") userName: String
    ): List<RepositoryDto>
}