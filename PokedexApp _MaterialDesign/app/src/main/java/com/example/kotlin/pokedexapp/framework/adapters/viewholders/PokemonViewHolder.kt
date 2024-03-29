package com.example.kotlin.pokedexapp.framework.adapters.viewholders

import android.content.Context
import android.content.Intent
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.kotlin.pokedexapp.data.network.model.PokemonBase
import com.example.kotlin.pokedexapp.data.network.model.pokemon.Pokemon
import com.example.kotlin.pokedexapp.databinding.ItemPokemonBinding
import com.example.kotlin.pokedexapp.domain.PokemonInfoRequirement
import com.example.kotlin.pokedexapp.framework.views.activities.PokemonDetailActivity
import com.example.kotlin.pokedexapp.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokemonViewHolder(private val binding: ItemPokemonBinding) : RecyclerView.ViewHolder
    (binding.root) {
    fun bind(item: PokemonBase, context: Context) {
        binding.tvName.text = item.name
        getPokemonInfo(item.url, binding.ivPhoto, context)

        binding.llPokemon.setOnClickListener {
            passViewGoToPokemonDetail(item.url,context)
        }
    }

    private fun getPokemonInfo(url: String, imageView: ImageView, context: Context) {
        var pokemonStringNumber: String = url.replace("https://pokeapi.co/api/v2/pokemon", "")
        pokemonStringNumber = pokemonStringNumber.replace("/", "")
        val pokemonNumber: Int = Integer.parseInt(pokemonStringNumber)

        CoroutineScope(Dispatchers.IO).launch {
            val pokemonInfoRequirement = PokemonInfoRequirement()
            val result: Pokemon? = pokemonInfoRequirement(pokemonNumber)
            CoroutineScope(Dispatchers.Main).launch {

                val urlImage = result?.sprites?.other?.official_artwork?.front_default.toString()

                val requestOptions = RequestOptions()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .fitCenter()
                    .priority(Priority.HIGH)

                Glide.with(context).load(urlImage?.toString())
                    .apply(requestOptions)
                    .into(imageView)
            }
        }
    }

    private fun passViewGoToPokemonDetail(url: String,context:Context){
        var intent: Intent = Intent(context, PokemonDetailActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        context.startActivity(intent)
        intent.putExtra(Constants.URL_POKEMON,url)
    }
}
