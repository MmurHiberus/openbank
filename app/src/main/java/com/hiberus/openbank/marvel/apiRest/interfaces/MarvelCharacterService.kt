package com.hiberus.openbank.marvel.apiRest.interfaces

import com.hiberus.openbank.marvel.apiRest.dto.CharacterListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelCharacterService {

    @GET("/v1/public/characters")
    suspend fun requestCharacterList(
        @Query("apikey") apikey: String,
        @Query("ts") ts: String,
        @Query("hash") hash: String)
    : CharacterListResponse

}