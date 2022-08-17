package com.br.appmarvelstudios.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.br.appmarvelstudios.databinding.ItemListCharacterFavoriteBinding
import com.br.appmarvelstudios.model.Character

class CharacterFavoriteAdapter(
    private val context: Context,
    private val listCharacterFavorite: MutableList<Character> = mutableListOf()
) : RecyclerView.Adapter<CharacterFavoriteAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val viewDataBinding = ItemListCharacterFavoriteBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(viewDataBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = listCharacterFavorite[position]
        val image = character.pathCharacter + "." + character.extensionCharacter
        holder.bind(character, image)
    }

    override fun getItemCount(): Int = listCharacterFavorite.size


    fun updates(listCharacterFavorite: List<Character>){
        this.listCharacterFavorite.clear()
        this.listCharacterFavorite.addAll(listCharacterFavorite)
        notifyDataSetChanged()
    }

    inner class ViewHolder(
       private val viewDataBinding: ItemListCharacterFavoriteBinding
    ) : RecyclerView.ViewHolder(viewDataBinding.root) {

        private lateinit var character : Character

        init {
            viewDataBinding.mcontext = context
        }

        fun bind(characterFavorite: Character, image: String){
            this.character = characterFavorite
            viewDataBinding.character = characterFavorite
            viewDataBinding.characterImage = image
        }
    }
}