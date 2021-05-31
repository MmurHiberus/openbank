package com.hiberus.openbank.marvel.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hiberus.openbank.marvel.constants.DBConstants.DB.Companion.DB_VERSION
import com.hiberus.openbank.marvel.db.converters.DateTypeConverter
import com.hiberus.openbank.marvel.db.dao.CharacterItemDao
import com.hiberus.openbank.marvel.db.dao.CharacterWithItemDao
import com.hiberus.openbank.marvel.db.dao.MarvelCharacterDao
import com.hiberus.openbank.marvel.db.entities.CharacterItem
import com.hiberus.openbank.marvel.db.entities.MarvelCharacter

@Database(
    entities = [MarvelCharacter::class, CharacterItem::class],
    version = DB_VERSION
)
@TypeConverters(DateTypeConverter::class)
abstract class AppDB :RoomDatabase() {
    abstract  fun marvelCharacterDao(): MarvelCharacterDao
    abstract  fun characterItemDao(): CharacterItemDao
    abstract  fun characterWithItemDao(): CharacterWithItemDao
}