package com.br.appmarvelstudios.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.br.appmarvelstudios.database.dao.CharacterDao
import com.br.appmarvelstudios.model.Character

@Database(entities = [Character::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao() : CharacterDao
}