package com.android.gitsearch.domain.usecase

import com.android.gitsearch.domain.model.User
import com.android.gitsearch.domain.repository.UserRepository

class SearchUsersUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(query: String): List<User> {
        return repository.searchUsers(query)
    }
}