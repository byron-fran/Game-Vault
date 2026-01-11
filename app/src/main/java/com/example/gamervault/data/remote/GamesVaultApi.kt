package com.example.gamervault.data.remote

import com.example.gamervault.data.DTO.GameDto
import com.example.gamervault.data.DTO.GameResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GamesVaultApi {

    @GET("games")
    suspend fun getGames(@Query("page") page : Int = 1, @Query("size") pageSize : Int = 10) : Response<GameResponseDto>

    @GET("games/{id}")
    suspend fun getGameById(@Path("id") id : Int) : GameDto?

}


