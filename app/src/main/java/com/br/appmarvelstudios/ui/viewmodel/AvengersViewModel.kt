package com.br.appmarvelstudios.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.appmarvelstudios.Constants.Companion.UNABLE_TO_RECEIVE_INTERNAL_CHARACTERS
import com.br.appmarvelstudios.Constants.Companion.UNABLE_TO_SAVE_CHARACTER
import com.br.appmarvelstudios.model.Character
import com.br.appmarvelstudios.model.HeadQuarters
import com.br.appmarvelstudios.repository.AvengersRepository
import com.br.appmarvelstudios.repository.Failure
import com.br.appmarvelstudios.repository.Resource
import com.br.appmarvelstudios.repository.Success
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AvengersViewModel(
    private val repository: AvengersRepository
) : ViewModel() {

    private val _toastText = MutableLiveData<String?>()
    val toastText: LiveData<String?>
        get() = _toastText

    private val _spinner = MutableLiveData<Boolean>(false)
    val spinner: LiveData<Boolean>
        get() = _spinner

    private val _allCharacters = MutableLiveData<HeadQuarters?>()
    val allCharacters: LiveData<HeadQuarters?>
        get() = _allCharacters

    fun getAllCharacters(): HeadQuarters? {
        launchDataLoad {
            _allCharacters.value = repository.getAllCharacters()
        }
        return _allCharacters.value
    }

    fun internalSave(character: Character) = MutableLiveData<Resource<Character>>().also {insertCharacter ->
        viewModelScope.launch {
            val resource: Resource<Character> = try {
                repository.saveCharacter(character)
                Success()
            } catch (e: Exception){
                Failure(erro = UNABLE_TO_SAVE_CHARACTER)
            }
            insertCharacter.postValue(resource)
        }
        return insertCharacter
    }

    fun getAllCharacterDao() = MutableLiveData<Resource<List<Character>>>().also { allCharacterDao ->
        viewModelScope.launch {
            val resource: Resource<List<Character>> = try {
                Success(dados = repository.getAllCharacterDao())
            } catch (e: Exception){
                Failure(erro = UNABLE_TO_RECEIVE_INTERNAL_CHARACTERS)
            }
            allCharacterDao.postValue(resource)
        }
        return allCharacterDao
    }

    private fun launchDataLoad(block: suspend () -> Unit): Job {
        return viewModelScope.launch {
            try {
                _spinner.value = true
                block()
            } catch (error: java.lang.Exception) {
                _toastText.value = error.message
            } finally {
                _spinner.value = false
            }
        }
    }

}