package com.br.appmarvelstudios.di

import androidx.room.Room
import com.br.appmarvelstudios.Constants.Companion.NAME_BANK_OF_DICE
import com.br.appmarvelstudios.database.AppDatabase
import com.br.appmarvelstudios.database.dao.CharacterDao
import com.br.appmarvelstudios.repository.AvengersRepository
import com.br.appmarvelstudios.retrofit.webclient.WebClient
import com.br.appmarvelstudios.ui.adapter.CharacterAdapter
import com.br.appmarvelstudios.ui.adapter.CharacterFavoriteAdapter
import com.br.appmarvelstudios.ui.fragment.CharacterDetailFragment
import com.br.appmarvelstudios.ui.fragment.CharacterListFavoriteFragment
import com.br.appmarvelstudios.ui.fragment.CharacterListFragment
import com.br.appmarvelstudios.ui.viewmodel.AvengersViewModel
import com.br.appmarvelstudios.ui.viewmodel.StateAppViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val databaseModule = module {
    single<AppDatabase> {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            NAME_BANK_OF_DICE
        ).build()
    }
}

val webClientModule = module {
    single<WebClient> { WebClient() }
}

val daoModule = module {
    single<CharacterDao> { get<AppDatabase>().characterDao() }
    single<AvengersRepository> { AvengersRepository(get(), get()) }
}

val uiModule = module {
    factory<CharacterListFragment> { CharacterListFragment() }
    factory<CharacterListFavoriteFragment> { CharacterListFavoriteFragment() }
    factory<CharacterDetailFragment> { CharacterDetailFragment() }
    factory<CharacterAdapter> { CharacterAdapter(get()) }
    factory<CharacterFavoriteAdapter> { CharacterFavoriteAdapter(get()) }
}

val viewModelModule = module {
    viewModel<AvengersViewModel> { AvengersViewModel(get()) }
    viewModel<StateAppViewModel> { StateAppViewModel() }
}