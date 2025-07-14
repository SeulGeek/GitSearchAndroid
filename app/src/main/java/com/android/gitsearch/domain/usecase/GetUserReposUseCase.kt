package com.android.gitsearch.domain.usecase

import com.android.gitsearch.domain.model.Repository
import com.android.gitsearch.domain.repository.UserRepository
import javax.inject.Inject

class GetUserReposUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(userName: String): List<Repository> =
        repository.getUserRepos(userName)
}