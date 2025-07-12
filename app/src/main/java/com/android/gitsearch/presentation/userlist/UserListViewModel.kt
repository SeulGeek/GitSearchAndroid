package com.android.gitsearch.presentation.userlist

import androidx.lifecycle.ViewModel
import com.android.gitsearch.domain.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UserListViewModel : ViewModel() {
    private val _state = MutableStateFlow(UserListState())
    val state: StateFlow<UserListState> = _state

    // TODO: change to api data not dummy data
    private val dummyUsers = listOf(
        User(1, "judy", "https://avatars.githubusercontent.com/u/1?v=4"),
        User(2, "edy", "https://avatars.githubusercontent.com/u/2?v=4"),
        User(3, "jolie", "https://avatars.githubusercontent.com/u/3?v=4"),
    )

    init {
        _state.value = _state.value.copy(users = dummyUsers)
    }

    fun onQueryChanged(newQuery: String) {
        _state.value = _state.value.copy(
            query = newQuery,
            users = dummyUsers.filter {
                it.userName.contains(newQuery, ignoreCase = true)
            }
        )
    }
}