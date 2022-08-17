package com.br.appmarvelstudios.retrofit.webclient

import com.br.appmarvelstudios.retrofit.AppRetrofit
import com.br.appmarvelstudios.retrofit.service.AvengersService

class WebClient(
    private val services: AvengersService = AppRetrofit().services
) {

    suspend fun getAllCharacters() = services.getAllCharacters().body()

}