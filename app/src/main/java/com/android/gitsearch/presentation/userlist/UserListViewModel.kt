package com.android.gitsearch.presentation.userlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.gitsearch.domain.model.User
import com.android.gitsearch.domain.usecase.SearchUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val searchUsersUseCase: SearchUsersUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(UserListState())
    val state: StateFlow<UserListState> = _state

    fun onQueryChanged(newQuery: String) {
        _state.value = _state.value.copy(query = newQuery)

        viewModelScope.launch {
            try {
                val result: List<User> = searchUsersUseCase(newQuery)
                _state.value = _state.value.copy(users = result)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}