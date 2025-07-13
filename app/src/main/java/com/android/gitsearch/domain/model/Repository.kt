package com.android.gitsearch.domain.model

data class Repository(
    val id: Int,
    val name: String,
    val description: String,
    val language: String?,
    val stargazersCount: Int,
    val htmlUrl: String
)