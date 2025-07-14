package com.android.gitsearch.presentation.userlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.gitsearch.domain.model.User
import com.android.gitsearch.domain.usecase.SearchUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class UserListViewModel @Inject constructor(
    private val searchUsersUseCase: SearchUsersUseCase
) : ViewModel() {

    private val _query = MutableStateFlow("")
    private val _state = MutableStateFlow(UserListState())
    val state: StateFlow<UserListState> = _state

    init {
        // Trigger search only when input changes with debounce
        viewModelScope.launch {
            _query
                .debounce(400) // Wait 400ms after user stops typing
                .distinctUntilChanged() // Skip if the input hasn't changed
                .collect { query ->
                    if (query.isBlank()) {
                        // If query is blank, reset to initial state
                        _state.value = _state.value.copy(users = emptyList(), isLoading = false)
                        return@collect
                    }

                    _state.value = _state.value.copy(isLoading = true, error = null)
                    try {
                        val result: List<User> = searchUsersUseCase(query)
                        _state.value = _state.value.copy(users = result, isLoading = false)
                    } catch (e: Exception) {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            error = e.message ?: "Unknown error occurred"
                        )
                    }
                }
        }
    }

    fun onQueryChanged(newQuery: String) {
        _query.value = newQuery
        _state.value = _state.value.copy(query = newQuery)
    }
}