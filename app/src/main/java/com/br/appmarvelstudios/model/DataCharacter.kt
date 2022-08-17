package com.br.appmarvelstudios.model

import com.google.gson.annotations.SerializedName

data class DataCharacter(
    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("modified")
    val modified: String,

    @SerializedName("thumbnail")
    val thumbnail: Thumbnail,

    @SerializedName("resourceURI")
    val resourceURI: String,
)
