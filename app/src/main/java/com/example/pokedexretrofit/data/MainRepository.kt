package com.example.pokedexretrofit.data

import com.example.pokedexretrofit.model.Pokemon
import com.example.pokedexretrofit.model.PokemonLista
import retrofit2.Response

class MainRepository(private val repository: PokemonDexService) {

    fun fetchPokemon(nome: String?): Response<Pokemon> = repository.fetchPokemon(nome).execute()

    fun listAllPokemon(limit: Int = 151): Response<PokemonLista> = repository.listAllPokemon(limit).execute()
}