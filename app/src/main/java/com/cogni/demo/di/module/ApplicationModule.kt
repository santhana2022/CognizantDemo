package com.cogni.demo.di.module

import android.content.Context
import com.cogni.demo.CognizantDemoApplication
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

/**
 *
 */
@InstallIn(SingletonComponent::class)
@Module
object ApplicationModule {

    @Provides
    fun provideApplicationContext(@ApplicationContext application: CognizantDemoApplication): Context =
        application.applicationContext

    @Provides
    fun providesCoroutineScope(): CoroutineScope {
        return MainScope()
    }

    @Provides
    fun providesGson(): Gson {
        return Gson()
    }
}
