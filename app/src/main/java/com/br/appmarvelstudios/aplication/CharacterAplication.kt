package com.br.appmarvelstudios.aplication

import android.app.Application
import com.br.appmarvelstudios.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CharacterAplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CharacterAplication)
            modules(
                listOf(
                    webClientModule,
                    daoModule,
                    uiModule,
                    viewModelModule,
                    databaseModule
                )
            )
        }
    }

}