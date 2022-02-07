package com.cogni.demo.data.repository

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import com.cogni.demo.data.applyCommonSideEffects
import com.cogni.demo.data.common.CallError
import com.cogni.demo.data.common.CallResult
import com.cogni.demo.data.common.CallResult.Error
import com.cogni.demo.data.common.CallResult.Success
import com.cogni.demo.data.repository.local.AppDatabase
import com.cogni.demo.data.repository.remote.CHARACTER_URL
import com.cogni.demo.data.repository.remote.QUOTES_URL
import com.cogni.demo.data.repository.remote.QUOTE_URL
import com.cogni.demo.data.repository.remote.RemoteSource
import com.cogni.demo.domain.dto.Characters
import com.cogni.demo.domain.dto.Quote
import com.cogni.demo.domain.dto.Quotes
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import javax.inject.Inject

/**
 *
 */
class AppRepository @Inject constructor(
    private val httpClient: OkHttpClient,
    private val localRepo: AppDatabase,
    private val remoteRepo: RemoteSource,
    private val gson: Gson
) {

    fun fetchAllCharacters(): Flow<CallResult<Characters>> = flow {
        remoteRepo.getAllCharacter().run {
            if (isSuccessful) {
                if (body() != null) {
                    emit(Success(this.body()!!))

                } else {
                    emit(Error(CallError.ErrorEmptyData))
                }
            } else {
                emit(Error(CallError.ErrorServer))
            }
        }
    }.applyCommonSideEffects().catch {
        emit(Error(CallError.ErrorException(it)))
    }

    suspend fun fetchSingleRandomQuote() : Flow<CallResult<Quotes>> = flow {
        remoteRepo.getSingleRandomQuote().run {
            if (isSuccessful) {
                if (body() != null) {
                    emit(Success(this.body()!!))

                } else {
                    emit(Error(CallError.ErrorEmptyData))
                }
            } else {
                emit(Error(CallError.ErrorServer))
            }
        }
    }.applyCommonSideEffects().catch {
        emit(Error(CallError.ErrorException(it)))
    }

//        try {
//            val url =
//                Uri.parse(QUOTE_URL).buildUpon().appendEncodedPath("random").build().toString()
//            val request = Request.Builder().url(url).build()
//            val response = httpClient.newCall(request).execute()
//            gson.fromJson(response.body?.charStream(), Quotes::class.java)
//        } catch (e: Exception) {
//            Log.d("BadRepository", "Exception fetching quotes list", e)
//            null
//        }
//    }



    suspend fun fetchCharacterData(): Characters? = withContext(Dispatchers.IO) {
        try {
            val request = Request.Builder().url(CHARACTER_URL).build()
            val response = httpClient.newCall(request).execute()
            gson.fromJson(response.body?.charStream(), Characters::class.java)
        } catch (e: Exception) {
            Log.d("BadRepository", "Exception fetching character list", e)
            null
        }
    }

    suspend fun fetchImageData(imageUrl: String): Bitmap? = withContext(Dispatchers.IO) {
        var bitmap: Bitmap?
        val request = Request.Builder().url(imageUrl).build()
        val response = httpClient.newCall(request).execute()

        response.body?.byteStream().use {
            bitmap = BitmapFactory.decodeStream(it)
        }
        bitmap
    }

    suspend fun fetchAllQuotes(): Quotes? = withContext(Dispatchers.IO) {
        try {
            val request = Request.Builder().url(QUOTES_URL).build()
            val response = httpClient.newCall(request).execute()
            gson.fromJson(response.body?.charStream(), Quotes::class.java)
        } catch (e: Exception) {
            Log.d("BadRepository", "Exception fetching quotes list", e)
            null
        }
    }

    suspend fun fetchQuotesById(quoteID: String): Quotes? = withContext(Dispatchers.IO) {
        try {
            val url = Uri.parse(QUOTE_URL).buildUpon().appendEncodedPath(quoteID).build().toString()
            val request = Request.Builder().url(url).build()
            val response = httpClient.newCall(request).execute()
            gson.fromJson(response.body?.charStream(), Quotes::class.java)
        } catch (e: Exception) {
            Log.d("BadRepository", "Exception fetching quotes list", e)
            null
        }
    }

    suspend fun fetchSingleRandomQuote1(): Quotes? = withContext(Dispatchers.IO) {
        try {
            val url =
                Uri.parse(QUOTE_URL).buildUpon().appendEncodedPath("random").build().toString()
            val request = Request.Builder().url(url).build()
            val response = httpClient.newCall(request).execute()
            gson.fromJson(response.body?.charStream(), Quotes::class.java)
        } catch (e: Exception) {
            Log.d("BadRepository", "Exception fetching quotes list", e)
            null
        }
    }

    suspend fun fetchSingleRandomQuoteByAuthor(author: String): Quote? =
        withContext(Dispatchers.IO) {
            try {
                val url = Uri.parse(QUOTE_URL).buildUpon().appendEncodedPath("random")
                    .appendQueryParameter("author", author).build().toString()
                val request = Request.Builder().url(url).build()
                val response = httpClient.newCall(request).execute()
                gson.fromJson(response.body?.charStream(), Quote::class.java)
            } catch (e: Exception) {
                Log.d("BadRepository", "Exception fetching quotes list", e)
                null
            }
        }
}
