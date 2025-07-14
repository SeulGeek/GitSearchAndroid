package com.android.gitsearch.domain.model

data class UserDetail(
    val userName: String,
    val avatarUrl: String,
    val fullName: String?,
    val followers: Int,
    val following: Int,
)
