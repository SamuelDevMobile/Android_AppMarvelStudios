package com.br.appmarvelstudios.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Character(
    @PrimaryKey @ColumnInfo(name = "entryid") var id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "nameCharacter") val nameCharacter: String,
    @ColumnInfo(name = "descriptionCharacter") val descriptionCharacter: String,
    @ColumnInfo(name = "pathCharacter") val pathCharacter: String,
    @ColumnInfo(name = "extensionCharacter") val extensionCharacter: String
)