package com.android.gitsearch.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RepositoryDto(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "name") val repoName: String,
    @field:Json(name = "language") val language: String?,
    @field:Json(name = "stargazers_count") val starCount: Int,
    @field:Json(name = "description") val description: String?,
    @field:Json(name = "html_url") val htmlUrl: String,
    @field:Json(name = "fork") val fork: Boolean
)