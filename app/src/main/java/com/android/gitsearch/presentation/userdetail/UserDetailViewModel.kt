package com.android.gitsearch.presentation.userdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.gitsearch.domain.usecase.GetUserDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getUserDetailUseCase: GetUserDetailUseCase
) : ViewModel() {
//    private val _repositories = MutableStateFlow(
//        listOf(
//            Repository(1, "grit", "Git library", "Ruby", 250, "https://github.com/mojombo/grit"),
//            Repository(
//                2,
//                "semver",
//                "Semantic versioning",
//                "Go",
//                100,
//                "https://github.com/mojombo/semver"
//            )
//        )
//    )
//

//    val repositories: StateFlow<List<Repository>> = _repositories

    private val userName = savedStateHandle.get<String>("userName") ?: ""
    val _state = MutableStateFlow(UserDetailState(isLoading = true))
    val state: StateFlow<UserDetailState> = _state

    init {
        loadUserData()
    }

    private fun loadUserData() {
        viewModelScope.launch {
            try {
                val details = getUserDetailUseCase(userName = userName)

                _state.value = _state.value.copy(
                    isLoading = false,
                    userDetail = details,
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