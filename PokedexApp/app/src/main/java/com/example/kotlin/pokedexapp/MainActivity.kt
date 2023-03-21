package com.example.kotlin.pokedexapp

import android.app.Activity
import android.os.Bundle
import com.example.kotlin.pokedexapp.databinding.ActivityMainBinding

class MainActivity: Activity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeBinding()
    }

    private fun initializeBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}