package com.android.gitsearch.domain.usecase

import com.android.gitsearch.domain.model.User
import com.android.gitsearch.domain.repository.UserRepository
import javax.inject.Inject

class SearchUsersUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(query: String): List<User> {
        return if (query.isBlank()) emptyList() else repository.searchUsers(query)
    }
}