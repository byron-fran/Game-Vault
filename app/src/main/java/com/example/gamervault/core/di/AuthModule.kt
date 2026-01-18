package com.example.gamervault.core.di

import com.example.gamervault.data.repositories.AuthRepositoryImpl
import com.example.gamervault.data.source.remote.firebase.FirebaseAuthDataSource
import com.example.gamervault.domain.repositories.AuthRepository
import com.example.gamervault.domain.usecases.GetCurrentUserUseCase
import com.example.gamervault.domain.usecases.IsAuthenticatedUseCase
import com.example.gamervault.domain.usecases.SignInUseCase
import com.example.gamervault.domain.usecases.SignOutUseCase
import com.example.gamervault.domain.usecases.SignUpUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object AuthModule {


    @Provides
    fun provideSignInUseCase(repository: AuthRepository) = SignInUseCase(repository)

    @Provides
    fun provideSignUpUseCase(repository: AuthRepository) = SignUpUseCase(repository)

    @Provides
    fun provideSignOutUseCase(repository: AuthRepository) = SignOutUseCase(repository)

    @Provides
    fun provideGetCurrentUserUseCase(repository: AuthRepository) = GetCurrentUserUseCase(repository)

    @Provides
    fun provideIsAuthenticatedUseCase(repository: AuthRepository) = IsAuthenticatedUseCase(repository)
    @Provides
    fun provideAuthRepository(authDataSource: FirebaseAuthDataSource) : AuthRepository = AuthRepositoryImpl(authDataSource)
}