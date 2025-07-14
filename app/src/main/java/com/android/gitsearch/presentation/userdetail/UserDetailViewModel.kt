package com.android.gitsearch.presentation.userdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.gitsearch.domain.usecase.GetUserDetailUseCase
import com.android.gitsearch.domain.usecase.GetUserReposUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getUserDetailUseCase: GetUserDetailUseCase,
    private val getUserReposUseCase: GetUserReposUseCase
) : ViewModel() {
    private val userName = savedStateHandle.get<String>("userName")
    private val _state = MutableStateFlow(UserDetailState(isLoading = true))
    val state: StateFlow<UserDetailState> = _state

    init {
        loadUserData()
    }

    private fun loadUserData() {
        if (userName.isNullOrBlank()) {
            _state.value = _state.value.copy(
                isLoading = false,
                error = "Invalid username"
            )
            return
        }

        viewModelScope.launch {
            try {
                val details = getUserDetailUseCase(userName = userName)
                val repositories = getUserReposUseCase(userName = userName)

                _state.value = _state.value.copy(
                    isLoading = false,
                    userDetail = details,
                    repositories = repositories,
                    error = null
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message ?: "Unknow Error"
                )
            }
        }
    }
}