package com.example.kotlin.pokedexapp.model

import com.example.kotlin.pokedexapp.model.pokemon.Pokemon
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


// URL BASE https://pokeapi.co/api/v2/
interface PokemonAPIService {
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int
    ): PokedexObject

    @GET("pokemon/{numberPokemon}")
    suspend fun getPokemonInfo(
        @Path("numberPokemon") numberPokemon: Int
    ): com.example.kotlin.pokedexapp.model.pokemon.Pokemon
}