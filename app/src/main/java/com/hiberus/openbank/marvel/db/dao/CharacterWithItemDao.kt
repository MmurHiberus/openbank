package com.hiberus.openbank.marvel.db.dao
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Transaction
import com.hiberus.openbank.marvel.MarvelApp
import com.hiberus.openbank.marvel.db.dto.CharacterWithItemsDto
import com.hiberus.openbank.marvel.db.entities.MarvelCharacter

@Dao
abstract class CharacterWithItemDao {

    @Query("SELECT * FROM MarvelCharacter order by name")
    abstract fun findAllCharactersWithItems(): LiveData<List<CharacterWithItemsDto>>


    fun deleteAll():Int{
        val deletedChars = MarvelApp.get().DB?.marvelCharacterDao()?.deleteAll()?:0
        val deletedItems = MarvelApp.get().DB?.characterItemDao()?.deleteAll()?:0
        return deletedChars+deletedItems
    }



    fun insertAll(characters: List<CharacterWithItemsDto>?){
        deleteAll()
        characters?.forEach {
            MarvelApp.get().DB?.marvelCharacterDao()?.insert(it.marvelCharacter)
            MarvelApp.get().DB?.characterItemDao()?.insertAll(it.itemList)
        }
    }
}