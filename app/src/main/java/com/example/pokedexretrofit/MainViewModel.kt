package com.example.pokedexretrofit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexretrofit.model.Pokemon
import com.example.pokedexretrofit.data.MainRepository
import com.example.pokedexretrofit.model.IMainView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainViewModel(private val view: IMainView, private val repository: MainRepository) : ViewModel() {

    val pokemon = MutableLiveData<Pokemon>()
    val pokemonQuery = MutableLiveData<Pokemon?>()

    fun listAllPokemon() {
        view.showProgress(true)
        viewModelScope.launch(Dispatchers.IO) {
            val res = repository.listAllPokemon().body()
            res?.results?.let { pokemonList ->
                pokemonList.forEach { pokemonQuery ->
                    val pokemonResult = repository.fetchPokemon(pokemonQuery.name).body()
                    viewModelScope.launch(Dispatchers.Main) {
                        pokemon.value = pokemonResult
                        view.showProgress(false)
                    }
                }
            }
        }
    }

    fun fetchPokemon(name: String?){
        view.showProgress(true)
        viewModelScope.launch(Dispatchers.IO){
            val res = repository.fetchPokemon(name).body()
            viewModelScope.launch(Dispatchers.Main){
                pokemonQuery.value = res
                view.showProgress(false)
            }
        }
    }


}