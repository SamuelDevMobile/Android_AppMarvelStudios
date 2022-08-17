package com.br.appmarvelstudios.repository

abstract class Resource<T>(
    val dados: T? = null,
    val error: String? = null
)

class Success<T>(
    dados: T? = null
) : Resource<T>(dados = dados) {
    override fun equals(other: Any?) = dados == (other as? Success<*>)?.dados
}

class Failure<T>(
    erro: String
) : Resource<T>(error = erro) {
    override fun equals(other: Any?) = error == (other as? Failure<*>)?.error
}