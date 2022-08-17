package com.br.appmarvelstudios.ui.adapter

import android.content.Context
import com.br.appmarvelstudios.model.DataCharacter
import com.br.appmarvelstudios.model.Thumbnail
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doNothing
import org.mockito.Mockito.verify
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class CharacterAdapterTest {

    @Mock
    private val context: Context? = null

    @Spy
    private val adapter: CharacterAdapter = CharacterAdapter(context)

    @Test
    fun must_Update_Character_List_When_Receiving_Character_List() {

        doNothing().`when`(adapter).updateList()

        adapter.updates(
            ArrayList(
                listOf(
                    DataCharacter(
                        id = "aa",
                        name = "Thor",
                        description = "gordao",
                        thumbnail = Thumbnail(path = "caminho", extension = "jpg"),
                        modified = "ss",
                        resourceURI = "sss"
                    ),
                    DataCharacter(
                        id = "bb",
                        name = "Loki",
                        description = "mentiroso",
                        thumbnail = Thumbnail(path = "caminho", extension = "jpg"),
                        modified = "ff",
                        resourceURI = "fff"
                    )
                )
            )
        )

        val amountOfCharacterReturned = adapter.itemCount

        verify(adapter).updateList()
        assertThat(amountOfCharacterReturned, `is`(2))
    }
}