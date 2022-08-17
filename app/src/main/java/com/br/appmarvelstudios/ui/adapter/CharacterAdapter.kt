package com.br.appmarvelstudios.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.br.appmarvelstudios.databinding.ItemCharacterBinding
import com.br.appmarvelstudios.model.DataCharacter
import java.util.*
import kotlin.collections.ArrayList

class CharacterAdapter(
    private val context: Context?,
    private var listCharacter: MutableList<DataCharacter> = mutableListOf(),
    private var searchItemCharacter: MutableList<DataCharacter> = mutableListOf(),
    var onItemClickListener: (characterSelected: DataCharacter) -> Unit = {}
) : RecyclerView.Adapter<CharacterAdapter.ViewHolder>(), Filterable {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val viewDataBinding = ItemCharacterBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(viewDataBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataCharacter = listCharacter[position]
        val image = dataCharacter.thumbnail.path + "." + dataCharacter.thumbnail.extension
        holder.bind(dataCharacter, image)
    }

    override fun getItemCount(): Int = listCharacter.size

    @ExperimentalStdlibApi
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList = ArrayList<DataCharacter>()

                if (constraint == null || constraint.isEmpty()) {
                    filteredList.addAll(searchItemCharacter)
                } else {
                    val filterPattern = constraint.toString().toLowerCase(Locale.ROOT).trim()
                    for (row in searchItemCharacter){
                        if (row.name.toLowerCase(Locale.ROOT).contains(filterPattern)) {
                            filteredList.add(row)
                        }
                    }
                }
                val filterResults = FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, results: FilterResults?) {
                listCharacter.clear()
                listCharacter = results?.values as ArrayList<DataCharacter>
                updateList()
            }
        }
    }

    fun updates(listCharacter: List<DataCharacter>) {
        this.listCharacter.clear()
        this.listCharacter.addAll(listCharacter)
        this.searchItemCharacter.clear()
        this.searchItemCharacter.addAll(listCharacter)
        updateList()
    }

    fun updateList() {
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        private val viewDataBinding: ItemCharacterBinding
    ) : RecyclerView.ViewHolder(viewDataBinding.root), View.OnClickListener {

        private lateinit var character: DataCharacter

        init {
            viewDataBinding.clickCharacter = this
            viewDataBinding.mcontext = context
        }

        override fun onClick(p0: View?) {
            if (::character.isInitialized) {
                onItemClickListener(character)
            }
        }

        fun bind(characterReceived: DataCharacter, image: String) {
            this.character = characterReceived
            viewDataBinding.character = characterReceived
            viewDataBinding.characterImage = image
        }
    }
}