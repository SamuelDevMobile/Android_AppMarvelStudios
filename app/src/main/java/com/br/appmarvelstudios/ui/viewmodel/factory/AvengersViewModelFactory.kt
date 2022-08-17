package com.br.appmarvelstudios.ui.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.br.appmarvelstudios.repository.AvengersRepository
import com.br.appmarvelstudios.ui.viewmodel.AvengersViewModel

class AvengersViewModelFactory(
    private val repository: AvengersRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AvengersViewModel(repository) as T
    }
}