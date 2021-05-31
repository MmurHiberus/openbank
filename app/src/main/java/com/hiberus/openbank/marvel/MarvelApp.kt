package com.hiberus.openbank.marvel

import android.app.Application
import androidx.room.Room
import com.hiberus.openbank.marvel.constants.DBConstants.DB.Companion.DB_NAME
import com.hiberus.openbank.marvel.db.AppDB

class MarvelApp : Application() {
    companion object{
        private lateinit var INSTANCE: MarvelApp
        fun get(): MarvelApp{
            return INSTANCE
        }
    }
    var DB: AppDB? =null
    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        DB = Room.databaseBuilder(applicationContext, AppDB::class.java, DB_NAME).build()
    }
}