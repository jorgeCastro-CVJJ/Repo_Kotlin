package com.example.kotlin.pokedexapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.pokedexapp.databinding.ItemPokemonBinding
import com.example.kotlin.pokedexapp.pokemon.Pokemon


class PokemonAdapter : RecyclerView.Adapter<PokemonViewHolder>() {
    var data: ArrayList<PokemonBase> = ArrayList()

    fun PokemonAdapter(basicData: ArrayList<PokemonBase>) {
        this.data = basicData
    }

    // Le dice al RecyclerView que layout vamos a utilizar, e igualmente observa que utilizamos
    // la forma del bindign para cargar los componentes de la vista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding = ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonViewHolder(binding)
    }

    // Se encarga de pintar la celda, utiliza el viewholder que es el encargado de conectar los
    // componentes de una celda con el dato particular de l alsita. Observa que para esto tenemos
    // la variable item que se obtiene para ajustar la posición de la lsita y encuadrar lo que
    // vemos con el elemento correspondiente
    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    // Regresa la cantidad de datos de la lista !IMPORTANT para utilizar RecyclerView y optimizar
    // recursos
    override fun getItemCount(): Int {
        return data.size
    }


}