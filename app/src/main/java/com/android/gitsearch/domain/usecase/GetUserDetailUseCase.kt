package com.android.gitsearch.domain.usecase

import com.android.gitsearch.domain.model.UserDetail
import com.android.gitsearch.domain.repository.UserRepository
import javax.inject.Inject

class GetUserDetailUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(userName: String): UserDetail = repository.getUserDetail(userName)
}