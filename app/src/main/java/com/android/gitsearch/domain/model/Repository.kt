package com.android.gitsearch.domain.model

data class Repository(
    val id: Int,
    val repoName: String,
    val description: String?,
    val language: String?,
    val starCount: Int,
    val htmlUrl: String
)