package com.example.kotlin.pokedexapp

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin.pokedexapp.databinding.ActivityMainBinding
import com.example.kotlin.pokedexapp.pokemon.Pokemon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : Activity() {

    private lateinit var binding: ActivityMainBinding

    // El adapter maneja la lógica de la lista
    private val adapter: PokemonAdapter = PokemonAdapter()

    // El data contiene la informacion que queremos desplegar, por lo general después del llamado
    // a la BD. Es el Data Class Object
    private lateinit var data: ArrayList<PokemonBase>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeBinding()
        getPokemonList()
    }

    private fun initializeBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    //
    private fun setUpRecyclerView(dataForList: ArrayList<PokemonBase>) {
        binding.rvPokemon.setHasFixedSize(true)
        val linearLayoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )

        binding.rvPokemon.layoutManager = linearLayoutManager
        adapter.PokemonAdapter(dataForList,this)
        binding.rvPokemon.adapter = adapter
    }

    /*
    private fun testData(): ArrayList<PokemonBase> {
        var result = ArrayList<PokemonBase>()

        result.add(PokemonBase("bulbasaur", ""))
        result.add(PokemonBase("pikachu", ""))
        result.add(PokemonBase("charmander", ""))

        return result
    }*/

    private fun getPokemonList(){
        CoroutineScope(Dispatchers.IO).launch {
            val pokemonRepository = PokemonRepository()
            val result:PokedexObject? = pokemonRepository.getPokemonList(Constants
                .MAX_POKEMON_NUMBER)
            Log.d("Salida", result?.count.toString())
            CoroutineScope(Dispatchers.Main).launch {
                setUpRecyclerView(result?.results!!)
            }
        }
    }
}