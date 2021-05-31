package com.hiberus.openbank.marvel.apiRest.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.hiberus.openbank.marvel.BuildConfig
import com.hiberus.openbank.marvel.apiRest.deserializers.MarvelCharacterDeserializer
import com.hiberus.openbank.marvel.apiRest.dto.CharacterListResponse
import com.hiberus.openbank.marvel.apiRest.interfaces.MarvelCharacterService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private var retrofit: Retrofit? = null
    var marvelCharacterService: MarvelCharacterService? = null

    fun getInstance():RetrofitClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(3, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build()

        val objectMapper = ObjectMapper()
            .registerModule(
                SimpleModule()
                .addDeserializer(CharacterListResponse::class.java, MarvelCharacterDeserializer()))
        val converter = retrofit2.converter.jackson.JacksonConverterFactory.create(objectMapper)
         retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .addConverterFactory(converter)
            .client(okHttpClient).build()

        marvelCharacterService = retrofit!!.create(MarvelCharacterService::class.java)
        return  this
    }


}