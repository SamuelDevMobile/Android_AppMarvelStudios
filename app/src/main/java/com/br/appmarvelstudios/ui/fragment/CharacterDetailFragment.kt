package com.br.appmarvelstudios.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.br.appmarvelstudios.R
import com.br.appmarvelstudios.databinding.DetailCharacterBinding
import com.br.appmarvelstudios.model.Character
import com.br.appmarvelstudios.repository.Failure
import com.br.appmarvelstudios.repository.Success
import com.br.appmarvelstudios.ui.fragment.extension.showMensseger
import com.br.appmarvelstudios.ui.viewmodel.AvengersViewModel
import com.br.appmarvelstudios.ui.viewmodel.StateAppViewModel
import com.br.appmarvelstudios.ui.viewmodel.visualsComponent
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.sharedViewModel

class CharacterDetailFragment : Fragment() {

    private val arguments: CharacterDetailFragmentArgs by navArgs()
    private val stateViewModel: StateAppViewModel by sharedViewModel()
    private val nameCharacter by lazy { arguments.name }
    private val descriptionCharacter by lazy { arguments.description }
    private val pathCharacter by lazy { arguments.path }
    private val extensionCharacter by lazy { arguments.extension }
    private val viewModel: AvengersViewModel by inject()
    private val controller by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewBinding = DetailCharacterBinding.inflate(inflater, container, false)
        viewBindingData(viewBinding)
        return viewBinding.root
    }

    private fun viewBindingData(viewBinding: DetailCharacterBinding) {
        viewBinding.nameCharacter = nameCharacter
        viewBinding.descriptionCharacter = descriptionCharacter
        viewBinding.imageCharacter = "$pathCharacter.$extensionCharacter"
        viewBinding.mcontext = requireContext()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        stateViewModel.hasComponent = visualsComponent(appBar = true, bottomNavigation = false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu_save, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.form_character_menu_save) {
            insertCharacter(
                Character(
                    nameCharacter = nameCharacter,
                    descriptionCharacter = descriptionCharacter,
                    pathCharacter = pathCharacter,
                    extensionCharacter = extensionCharacter
                )
            )
            showMensseger("Saved character")
        }
        return super.onOptionsItemSelected(item)
    }

    private fun insertCharacter(character: Character) {
        viewModel.internalSave(character).observe(viewLifecycleOwner, Observer { resources ->
            when(resources){
                is Success -> controller.popBackStack()
                is Failure -> resources.error?.let { messageError -> showMensseger(messageError) }
            }
        })
    }
}