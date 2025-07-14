package com.android.gitsearch.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserResponseDto(
    @field:Json(name = "login") val userName: String,
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "avatar_url") val avatarUrl: String
)

@JsonClass(generateAdapter = true)
data class SearchUserResponse(
    @field:Json(name = "items") val items: List<UserResponseDto>
)