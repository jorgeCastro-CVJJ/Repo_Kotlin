package com.example.kotlin.pokedexapp

import android.net.Network
import com.example.kotlin.pokedexapp.pokemon.Pokemon

class PokemonRepository() {

    private lateinit var api: PokemonAPIService
    suspend fun getPokemonList(limit: Int): PokedexObject? {
        api = NetworkModuleDI()
        return try {
            api.getPokemonList(limit)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun getPokemonInfo(numberPokemon: Int): Pokemon? {
        api = NetworkModuleDI()
        return try {
            api.getPokemonInfo(numberPokemon)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            null
        }
    }
}
