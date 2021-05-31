package com.hiberus.openbank.marvel.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.hiberus.openbank.marvel.db.entities.CharacterItem
import com.hiberus.openbank.marvel.db.entities.MarvelCharacter

@Dao
interface CharacterItemDao {


    @Query("DELETE FROM CharacterItem")
    fun deleteAll():Int

    @Insert(onConflict = REPLACE)
    fun insertAll(characters: List<CharacterItem>?): LongArray?
}