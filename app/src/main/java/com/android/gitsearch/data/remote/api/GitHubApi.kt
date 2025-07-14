package com.android.gitsearch.data.remote.api

import com.android.gitsearch.data.remote.dto.SearchUserResponse
import com.android.gitsearch.data.remote.dto.UserDetailDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApi {
    @GET("search/users")
    suspend fun searchUsers(
        @Query("q") query: String
    ): SearchUserResponse

    @GET("users/{username}")
    suspend fun getUserDetail(
        @Path("username") userName: String
    ): UserDetailDto

}