package com.cogni.demo.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data class that represent the a table in the database.
 */
@Entity(tableName = "characters")
data class CharacterEntity(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    val msg: String,
    val timestamp: Long
) {

//    @PrimaryKey(autoGenerate = true)
//    var id: Long = 0
}
