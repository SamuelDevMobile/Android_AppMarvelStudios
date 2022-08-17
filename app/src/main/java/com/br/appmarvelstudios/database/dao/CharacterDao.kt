package com.br.appmarvelstudios.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.br.appmarvelstudios.model.Character

@Dao
interface CharacterDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertCharacter(vararg character: Character)

    @Query("SELECT * FROM Character")
    suspend fun getAllCharacter(): List<Character>

    @Query("SELECT * FROM Character WHERE entryid = :taskId")
    suspend fun getTaskById(taskId: String): Character?

}