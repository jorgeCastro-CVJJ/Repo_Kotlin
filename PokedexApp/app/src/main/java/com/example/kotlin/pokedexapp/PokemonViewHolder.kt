package com.example.kotlin.pokedexapp

import android.content.Context
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.kotlin.pokedexapp.databinding.ItemPokemonBinding
import com.example.kotlin.pokedexapp.pokemon.Pokemon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokemonViewHolder(private val binding: ItemPokemonBinding) : RecyclerView.ViewHolder
    (binding.root) {
    fun bind(item: PokemonBase, context:Context) {
        binding.tvName.text = item.name
        getPokemonInfo(item.url,binding.ivPhoto,context)
    }
    private fun getPokemonInfo(url:String, imageView: ImageView, context: Context){
        var pokemonStringNumber:String = url.replace("https://pokeapi.co/api/v2/pokemon","")
        pokemonStringNumber = pokemonStringNumber.replace("/","")
        val pokemonNumber:Int = Integer.parseInt(pokemonStringNumber)

        CoroutineScope(Dispatchers.IO).launch{
            val pokemonRepository = PokemonRepository()
            val result: Pokemon? = pokemonRepository.getPokemonInfo(pokemonNumber)
            CoroutineScope(Dispatchers.Main).launch{

                val urlImage = result?.sprites?.other?.official_artwork?.front_default.toString()

                val requestOptions = RequestOptions()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .fitCenter()
                    .priority(Priority.HIGH)

                Glide.with(context).load(urlImage)
                    .apply(requestOptions)
                    .into(imageView)
            }
        }
    }
}
