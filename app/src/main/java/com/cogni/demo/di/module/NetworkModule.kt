package com.cogni.demo.di.module

import android.content.Context
import com.cogni.demo.data.repository.remote.BASE_URL
import com.cogni.demo.data.repository.remote.RemoteSource
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    @Singleton
    fun providesOkhttpCache(@ApplicationContext context: Context): Cache {
        return Cache(context.cacheDir, 1024)
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(cache: Cache): OkHttpClient {
        val client = OkHttpClient.Builder()
            .addInterceptor(Interceptor { chain ->
                val request = chain.request()
                // TODO : Manipulate request
                println("Request  \n $request")
                val response = chain.proceed(request)
                println("Response \n ${response.peekBody(Long.MAX_VALUE).string()}")
                // TODO : Manipulate response
                response
            })
            .cache(cache)
            .connectTimeout(6, TimeUnit.SECONDS)
            .writeTimeout(6, TimeUnit.SECONDS)
            .readTimeout(6, TimeUnit.SECONDS)
        //.addNetworkInterceptor(StethoInterceptor())

        return client.build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        val gsonBuilder = GsonBuilder()
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory((GsonConverterFactory.create(gsonBuilder.create())))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun providesRemoteDataSource(retrofit: Retrofit): RemoteSource {
        return retrofit.create(RemoteSource::class.java)
    }
}