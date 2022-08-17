package com.br.appmarvelstudios.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.br.appmarvelstudios.databinding.ListCharacterFavoriteBinding
import com.br.appmarvelstudios.model.Character
import com.br.appmarvelstudios.ui.adapter.CharacterFavoriteAdapter
import com.br.appmarvelstudios.ui.fragment.extension.showMensseger
import com.br.appmarvelstudios.ui.viewmodel.AvengersViewModel
import com.br.appmarvelstudios.ui.viewmodel.StateAppViewModel
import com.br.appmarvelstudios.ui.viewmodel.visualsComponent
import kotlinx.android.synthetic.main.list_character_favorite.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.sharedViewModel

class CharacterListFavoriteFragment : Fragment() {

    private val viewModel: AvengersViewModel by inject()
    private val stateAppViewModel: StateAppViewModel by sharedViewModel()
    private val adapterCharacterFavorite: CharacterFavoriteAdapter by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewDataBinding = ListCharacterFavoriteBinding.inflate(inflater, container, false)
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        stateAppViewModel.hasComponent = visualsComponent(appBar = true, bottomNavigation = false)
        configurationRecyclerView()
        getAllCharacterSaveDao()
    }

    private fun configurationRecyclerView(){
        listCharacterFavorite.run {
            adapter = adapterCharacterFavorite
        }
    }

    private fun getAllCharacterSaveDao() {
        viewModel.getAllCharacterDao().observe(viewLifecycleOwner, Observer { resources ->
            resources.dados?.let { characterDao -> verifyList(characterDao) }
            resources.error?.let { messageError -> showMensseger(messageError) }
        })
    }

    private fun verifyList(characterDao: List<Character>) =
        if (characterDao.isEmpty()) {
            no_character.visibility = View.VISIBLE
            listCharacterFavorite.visibility = View.GONE
        } else {
            adapterCharacterFavorite.updates(characterDao)
        }

}