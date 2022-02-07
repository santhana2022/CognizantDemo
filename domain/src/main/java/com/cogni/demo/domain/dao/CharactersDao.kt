package com.cogni.demo.domain.dao

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.cogni.demo.domain.entities.CharacterEntity


/**
 * Data access object to query the database.
 */
@Dao
interface CharactersDao {

    @Query("SELECT * FROM characters ORDER BY id DESC")
    fun getAll(): List<CharacterEntity>

    @Insert
    fun insertAll(vararg character: CharacterEntity)

    @Query("DELETE FROM characters")
    fun nukeTable()

    @Query("SELECT * FROM characters ORDER BY id DESC")
    fun selectAllCharacterCursor(): Cursor

    @Query("SELECT * FROM characters WHERE id = :id")
    fun selectCharacterById(id: Long): Cursor?
}
