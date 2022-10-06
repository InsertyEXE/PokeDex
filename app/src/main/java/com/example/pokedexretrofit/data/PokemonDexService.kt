package com.example.pokedexretrofit.data


import com.example.pokedexretrofit.model.Pokemon
import com.example.pokedexretrofit.model.PokemonLista
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface PokemonDexService {

    @GET("pokemon/{pokemon}")
    fun fetchPokemon(@Path("pokemon") pokemonName: String?): Call<Pokemon>

    @GET("pokemon")
    fun listAllPokemon(@Query("limit") limit: Int): Call<PokemonLista>

}


