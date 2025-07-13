package com.android.gitsearch.data.remote.api

import com.android.gitsearch.data.remote.dto.SearchUserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubAPI {
    @GET("search/users")
    suspend fun searchUsers(
        @Query("q") query: String
    ): SearchUserResponse
}