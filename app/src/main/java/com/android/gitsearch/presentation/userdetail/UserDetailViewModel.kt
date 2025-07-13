package com.android.gitsearch.presentation.userdetail

import androidx.lifecycle.ViewModel
import com.android.gitsearch.domain.model.Repository
import com.android.gitsearch.domain.model.UserDetail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UserDetailViewModel(userName: String) : ViewModel() {
    // TODO: change it as API data
    private val _userDetail = MutableStateFlow(
        UserDetail(
            avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
            userName = "judy",
            fullName = "hey judy",
            followers = 1000,
            following = 42
        )
    )

    private val _repositories = MutableStateFlow(
        listOf(
            Repository(1, "grit", "Git library", "Ruby", 250, "https://github.com/mojombo/grit"),
            Repository(
                2,
                "semver",
                "Semantic versioning",
                "Go",
                100,
                "https://github.com/mojombo/semver"
            )
        )
    )

    val userDetail: StateFlow<UserDetail> = _userDetail
    val repositories: StateFlow<List<Repository>> = _repositories
}