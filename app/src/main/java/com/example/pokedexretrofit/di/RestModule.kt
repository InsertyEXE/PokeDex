package com.example.pokedexretrofit.di

import com.example.pokedexretrofit.data.PokemonDexService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val restModule = module {

    single { providerRetrofit().create(PokemonDexService::class.java) }
}

fun providerRetrofit(): Retrofit{
   return Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}