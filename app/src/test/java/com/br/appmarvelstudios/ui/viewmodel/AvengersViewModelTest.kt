package com.br.appmarvelstudios.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.br.appmarvelstudios.MainCoroutineRule
import com.br.appmarvelstudios.database.AppDatabase
import com.br.appmarvelstudios.model.Character
import com.br.appmarvelstudios.repository.AvengersRepository
import com.br.appmarvelstudios.repository.Success
import com.br.appmarvelstudios.retrofit.AppRetrofit
import com.br.appmarvelstudios.retrofit.webclient.WebClient
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@Config(manifest = Config.NONE)
class AvengersViewModelTest {

    private lateinit var avengersViewModel: AvengersViewModel

    private lateinit var avengersRepository: AvengersRepository

    private lateinit var database: AppDatabase

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineDispatcher = MainCoroutineRule()

    @Before
    fun setupAvengersViewModel() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        avengersRepository =
            AvengersRepository(WebClient(AppRetrofit().services), database.characterDao())

        avengersViewModel = AvengersViewModel(avengersRepository)
    }

    @After
    fun closeDb() = database.close()

    @Test
    fun insertSave_saveCharacterInTheDataBase() = runBlockingTest {

        val character = Character(
            nameCharacter = "Thor",
            descriptionCharacter = "gordao",
            pathCharacter = "caminho",
            extensionCharacter = "jpg"
        )
        val returnCharacter = avengersViewModel.internalSave(character)

        assertThat(returnCharacter.value, `is`(Success()))
    }
}