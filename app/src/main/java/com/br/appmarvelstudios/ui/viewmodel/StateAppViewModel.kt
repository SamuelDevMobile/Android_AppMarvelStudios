package com.br.appmarvelstudios.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StateAppViewModel : ViewModel() {

    val componentes: LiveData<visualsComponent> get() = _component

    private var _component: MutableLiveData<visualsComponent> =
        MutableLiveData<visualsComponent>().also {
            it.value = hasComponent
        }

    var hasComponent: visualsComponent = visualsComponent()
        set(value) {
            field = value
            _component.value = value
        }
}

class visualsComponent(
    val appBar: Boolean = false,
    val bottomNavigation: Boolean = false
)
