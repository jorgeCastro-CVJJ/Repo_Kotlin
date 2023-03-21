package com.example.kotlin.pokedexapp

import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.pokedexapp.databinding.ItemPokemonBinding
import com.example.kotlin.pokedexapp.pokemon.Pokemon

class PokemonViewHolder(private val binding: ItemPokemonBinding) : RecyclerView.ViewHolder
    (binding.root) {
    fun bind(item: PokemonBase) {
        binding.tvName.text = item.name
    }
}
