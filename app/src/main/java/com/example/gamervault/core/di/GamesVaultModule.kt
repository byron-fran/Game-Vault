package com.example.gamervault.core.di

import com.example.gamervault.data.remote.GamesVaultApi
import com.example.gamervault.data.repositories.GamesVaultRepositoryImpl
import com.example.gamervault.domain.repositories.GamesVaultRepository
import com.example.gamervault.domain.usecases.GetGameByIdUseCase
import com.example.gamervault.domain.usecases.GetGamesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object GamesVaultModule {


    @Provides
    fun provideGetGameUseCase(repository: GamesVaultRepository) = GetGamesUseCase(repository)

    @Provides
    fun provideGetGameByIdUseCase(repository: GamesVaultRepository) = GetGameByIdUseCase(repository)

    @Provides
    fun provideGamesVaultRepository(gamesVaultApi: GamesVaultApi) : GamesVaultRepository = GamesVaultRepositoryImpl(gamesVaultApi)

}