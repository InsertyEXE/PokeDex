package com.example.pokedexretrofit.view


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokedexretrofit.databinding.ActivityMainBinding
import com.example.pokedexretrofit.model.Pokemon
import com.example.pokedexretrofit.model.PokemonAdapter
import com.example.pokedexretrofit.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var pokemons: MutableList<Pokemon> = arrayListOf()
    private val viewmodel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvDex.layoutManager = GridLayoutManager(this, 2)

    }


    override fun onResume() {
        super.onResume()

        if (pokemons.isEmpty())
            viewmodel.listarPokemons()

        viewmodel.pokemon.observe(this, Observer {

                if (!pokemons.contains(it))
                    pokemons.add(it)

                pokemons.sortBy { pokemon ->
                    pokemon.id
                }

                binding.rvDex.adapter = PokemonAdapter(pokemons)
        })

    }
}