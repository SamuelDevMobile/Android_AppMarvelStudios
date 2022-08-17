package com.br.appmarvelstudios.repository

import com.br.appmarvelstudios.database.dao.CharacterDao
import com.br.appmarvelstudios.model.Character
import com.br.appmarvelstudios.retrofit.webclient.WebClient

class AvengersRepository(
    private val webClient: WebClient,
    var dao: CharacterDao
) {

    suspend fun getAllCharacters() = webClient.getAllCharacters()

    suspend fun saveCharacter(character: Character) = dao.insertCharacter(character)

    suspend fun getAllCharacterDao() = dao.getAllCharacter()

}