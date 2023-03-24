package com.example.kotlin.pokedexapp.framework.views

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin.pokedexapp.data.network.model.PokemonBase
import com.example.kotlin.pokedexapp.databinding.ActivityMainBinding
import com.example.kotlin.pokedexapp.framework.adapters.PokemonAdapter
import com.example.kotlin.pokedexapp.framework.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    // El adapter maneja la lógica de la lista
    private val adapter: PokemonAdapter = PokemonAdapter()

    // El data contiene la informacion que queremos desplegar, por lo general después del llamado
    // a la BD. Es el Data Class Object
    private lateinit var data: ArrayList<PokemonBase>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeBinding()
        initializeObserver()
        viewModel.getPokemonList()
    }

    private fun initializeObserver() {
        viewModel.pokedexObjectLiveData.observe(this) { podekexObject ->
            setUpRecyclerView(podekexObject.results)
        }
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
        adapter.PokemonAdapter(dataForList, this)
        binding.rvPokemon.adapter = adapter
    }

}