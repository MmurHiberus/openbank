package com.hiberus.openbank.marvel.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.hiberus.openbank.marvel.db.entities.MarvelCharacter

@Dao
interface MarvelCharacterDao {

    @Query("SELECT * FROM MarvelCharacter")
    fun findAllCharacters(): LiveData<List<MarvelCharacter>>

    @Query("DELETE FROM MarvelCharacter")
    fun deleteAll():Int

    @Insert(onConflict = REPLACE)
    fun insertAll(characters: List<MarvelCharacter>?): LongArray?

    @Insert(onConflict = REPLACE)
    fun insert(character: MarvelCharacter?): Long?
}