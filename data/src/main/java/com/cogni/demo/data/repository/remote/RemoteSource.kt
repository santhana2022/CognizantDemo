package com.cogni.demo.data.repository.remote

import com.cogni.demo.domain.dto.Characters
import com.cogni.demo.domain.dto.Quotes
import retrofit2.Response
import retrofit2.http.GET

interface RemoteSource {

    @GET(PATH_CHARACTERS)
    suspend fun getAllCharacter(): Response<Characters>

    @GET("$QUOTE_URL/$PATH_RANDOM")
    suspend fun getSingleRandomQuote(): Response<Quotes>

    @GET(PATH_QUOTES)
    suspend fun getAllQuotes(): Response<Quotes>
}