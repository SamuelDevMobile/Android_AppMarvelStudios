package com.br.appmarvelstudios.retrofit.service

import com.br.appmarvelstudios.Constants.Companion.APIKEY
import com.br.appmarvelstudios.Constants.Companion.HASH_MD5
import com.br.appmarvelstudios.Constants.Companion.TIMES_TAMP
import com.br.appmarvelstudios.model.HeadQuarters
import retrofit2.Response
import retrofit2.http.GET

interface AvengersService {

    @GET("/v1/public/characters?ts=${TIMES_TAMP}&apikey=${APIKEY}&hash=${HASH_MD5}")
    suspend fun getAllCharacters() : Response<HeadQuarters>

}