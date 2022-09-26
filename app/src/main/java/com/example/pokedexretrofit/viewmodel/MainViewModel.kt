package com.example.pokedexretrofit.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexretrofit.model.Pokemon
import com.example.pokedexretrofit.model.PokemonLista
import com.example.pokedexretrofit.rest.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainViewModel(private val repository: MainRepository) : ViewModel() {

    val pokemon = MutableLiveData<Pokemon>()

    fun listarPokemons() {
        viewModelScope.launch(Dispatchers.IO) {
            val res = repository.listarPokemons().body()
            res?.results?.let { pokemonList ->
                pokemonList.forEach { pokemonQuery ->
                    val pokemonResult = repository.buscarPokemon(pokemonQuery.name).body()

                    viewModelScope.launch(Dispatchers.Main) {
                        pokemon.value = pokemonResult
                    }
                }
            }
        }
    }
}