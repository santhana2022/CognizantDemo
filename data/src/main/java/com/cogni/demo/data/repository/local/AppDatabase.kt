package com.cogni.demo.data.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cogni.demo.domain.dao.CharactersDao
import com.cogni.demo.domain.entities.CharacterEntity
import javax.inject.Inject

@Database(entities = [CharacterEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharactersDao
}