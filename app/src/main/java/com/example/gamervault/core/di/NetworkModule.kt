package com.example.gamervault.core.di

import com.example.gamervault.data.remote.ApiKeyInterceptor
import com.example.gamervault.data.remote.GamesVaultApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn( SingletonComponent::class)
object NetworkModule {


    @Provides
    @Singleton
    fun provideRetrofit () : Retrofit {

        val client = OkHttpClient.Builder()
            .addInterceptor(ApiKeyInterceptor())
            .build()

        return Retrofit
            .Builder()
            .baseUrl("https://api.rawg.io/api/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideGamesVaultApi(retrofit: Retrofit) : GamesVaultApi {
        return retrofit.create(GamesVaultApi::class.java)
    }

}