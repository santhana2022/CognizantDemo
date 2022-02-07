package com.cogni.demo.di.module

import android.content.Context
import androidx.room.Room
import com.cogni.demo.domain.dao.CharactersDao
import com.cogni.demo.data.repository.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideLocalDataSource(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "Characters.db"
        ).build()
    }

    @Provides
    fun getCharactersDao(database: AppDatabase): CharactersDao {
        return database.characterDao()
    }
}