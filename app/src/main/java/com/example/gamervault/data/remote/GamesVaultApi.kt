package com.example.gamervault.data.remote

import com.example.gamervault.data.DTO.GameDetailDto
import com.example.gamervault.data.DTO.GameResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GamesVaultApi {

    @GET("games")
    suspend fun getGames(@Query("page") page : Int = 1, @Query("page_size") pageSize : Int = 10) : Response<GameResponseDto>

    @GET("games/{id}")
    suspend fun getGameById(@Path("id") id : Int) : GameDetailDto?

    @GET("games")
    suspend fun searchGames(@Query("search") query : String, @Query("page_size") page: Int = 20) : Response<GameResponseDto>

}


