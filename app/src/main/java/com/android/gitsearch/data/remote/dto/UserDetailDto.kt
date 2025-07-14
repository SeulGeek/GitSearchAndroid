package com.android.gitsearch.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDetailDto(
    @field:Json(name = "login") val userName: String,
    @field:Json(name = "avatar_url") val avatarUrl: String,
    @field:Json(name = "name") val fullName: String?,
    @field:Json(name = "followers") val followers: Int,
    @field:Json(name = "following") val following: Int
)
