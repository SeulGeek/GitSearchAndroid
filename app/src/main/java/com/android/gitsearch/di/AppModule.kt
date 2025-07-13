package com.android.gitsearch.di

import com.android.gitsearch.data.remote.repository.UserRepositoryImpl
import com.android.gitsearch.domain.repository.UserRepository
import com.android.gitsearch.domain.usecase.SearchUsersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUserRepository(): UserRepository {
        return UserRepositoryImpl()
    }

    @Provides
    fun provideSearchUserUseCase(
        repository: UserRepository
    ): SearchUsersUseCase {
        return SearchUsersUseCase(repository)
    }
}