package com.android.gitsearch.presentation.userdetail

import com.android.gitsearch.domain.model.Repository
import com.android.gitsearch.domain.model.UserDetail

data class UserDetailState(
    val isLoading: Boolean = false,
    val userDetail: UserDetail? = null,
    val repositories: List<Repository> = emptyList(),
    val error: String? = null
)