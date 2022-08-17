package com.br.appmarvelstudios.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.br.appmarvelstudios.R
import com.br.appmarvelstudios.databinding.ListCharacterBinding
import com.br.appmarvelstudios.model.DataCharacter
import com.br.appmarvelstudios.ui.adapter.CharacterAdapter
import com.br.appmarvelstudios.ui.fragment.extension.showMensseger
import com.br.appmarvelstudios.ui.viewmodel.AvengersViewModel
import com.br.appmarvelstudios.ui.viewmodel.StateAppViewModel
import com.br.appmarvelstudios.ui.viewmodel.visualsComponent
import kotlinx.android.synthetic.main.list_character.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.sharedViewModel

class CharacterListFragment : Fragment() {

    private val stateAppViewModel: StateAppViewModel by sharedViewModel()
    private val viewModel: AvengersViewModel by inject()
    private val adapterCharacter: CharacterAdapter by inject()
    private val controller by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewDataBinding = ListCharacterBinding.inflate(inflater, container, false)
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        stateAppViewModel.hasComponent =
            visualsComponent(appBar = true, bottomNavigation = true)
        configurationRecyclerView()
        loadSpinner()
        getAllCharacters()
        setAllCharacters()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu_search, menu)

        val searchItem = menu.findItem(R.id.form_character_menu_search)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapterCharacter.filter.filter(newText)
                return false
            }
        })
    }

    private fun loadSpinner() {
        viewModel.spinner.observe(viewLifecycleOwner) { value ->
            value.let { show ->
                progressBar.visibility = if (show) View.VISIBLE else View.GONE
            }
        }
    }

    private fun setAllCharacters() {
        viewModel.allCharacters.observe(viewLifecycleOwner) { character ->
            character?.let { adapterCharacter.updates(it.data.results) }
        }
        viewModel.toastText.observe(viewLifecycleOwner) { text ->
            text?.let {
                showMensseger(it)
            }
        }
    }

    private fun getAllCharacters() = viewModel.getAllCharacters()

    private fun configurationRecyclerView() {
        myRecyclerView.run {
            adapter = adapterCharacter
        }
        adapterCharacter.onItemClickListener = { dataCharacter ->
            goToCharacterDetailFragment(dataCharacter)
        }
    }

    private fun goToCharacterDetailFragment(dataCharacter: DataCharacter) {
        CharacterListFragmentDirections
            .actionListCharacterFragmentToDetailCharacterFragment(
                dataCharacter.name,
                dataCharacter.description,
                dataCharacter.thumbnail.path,
                dataCharacter.thumbnail.extension
            ).run {
                controller.navigate(this)
            }
    }
}