package com.example.pokedexretrofit.rest

import com.example.pokedexretrofit.model.Pokemon
import retrofit2.Response

class MainRepository(private val repository: PokemonDexService) {

    fun buscarPokemon(nome: String) = repository.buscarPokemon(nome).execute()

    fun listarPokemons(limit: Int = 151) = repository.listarPokemon(limit).execute()
}