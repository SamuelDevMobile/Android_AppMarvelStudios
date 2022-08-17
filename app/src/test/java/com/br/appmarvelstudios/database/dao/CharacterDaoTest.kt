package com.br.appmarvelstudios.database.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.br.appmarvelstudios.MainCoroutineRule
import com.br.appmarvelstudios.database.AppDatabase
import com.br.appmarvelstudios.model.Character
import com.br.appmarvelstudios.ui.viewmodel.AvengersViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@Config(manifest = Config.NONE)
class CharacterDaoTest {

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutinesRule = MainCoroutineRule()

    private lateinit var database: AppDatabase

    @Before
    fun initDb(){
        database = Room.inMemoryDatabaseBuilder(
            getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
    }

    @After
    fun closeDb() = database.close()

    @Test
    fun insertCharacterAndGetById() = runBlockingTest {

        val character = Character(
            nameCharacter = "Samuel",
            descriptionCharacter = "Ribeiro",
            pathCharacter = "teste",
            extensionCharacter = "jpg"
        )
        database.characterDao().insertCharacter(character)

        val characterReturned = database.characterDao().getTaskById(character.id)

        assertThat(characterReturned?.id, `is`(character.id))
        assertThat(characterReturned?.nameCharacter, `is`(character.nameCharacter))
        assertThat(characterReturned?.descriptionCharacter, `is`(character.descriptionCharacter))
        assertThat(characterReturned?.pathCharacter, `is`(character.pathCharacter))
        assertThat(characterReturned?.extensionCharacter, `is`(character.extensionCharacter))
    }

}