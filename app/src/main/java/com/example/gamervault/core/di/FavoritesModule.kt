package com.example.gamervault.core.di

import com.example.gamervault.data.repositories.FavoritesRepositoryImpl
import com.example.gamervault.data.source.remote.firebase.FirebaseFavoritesDataSource
import com.example.gamervault.domain.repositories.FavoritesRepository
import com.example.gamervault.domain.usecases.favorites.DeleteFavoriteByIdUseCase
import com.example.gamervault.domain.usecases.favorites.GetFavoritesUseCase
import com.example.gamervault.domain.usecases.favorites.InsertFavoriteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
object FavoritesModule {

    @Provides
    fun provideInsertFavoriteUseCase(favoritesRepository: FavoritesRepository): InsertFavoriteUseCase {
        return InsertFavoriteUseCase(favoritesRepository)
    }

    @Provides
    fun provideGetFavoritesUseCase(favoritesRepository: FavoritesRepository): GetFavoritesUseCase {
        return GetFavoritesUseCase(favoritesRepository)
    }

    @Provides
    fun provideDeleteFavoriteByIdUseCase(favoritesRepository: FavoritesRepository): DeleteFavoriteByIdUseCase {
        return DeleteFavoriteByIdUseCase(favoritesRepository)
    }

    @Provides
    fun provideFavoritesRepository(favoritesDataSource: FirebaseFavoritesDataSource): FavoritesRepository {
        return FavoritesRepositoryImpl(favoritesDataSource)
    }

}