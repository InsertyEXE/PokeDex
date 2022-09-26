package com.example.pokedexretrofit.di

import com.example.pokedexretrofit.rest.PokemonDexService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

val restModule = module {

    single { providerRetrofit() }
    single { providerRetrofit().create(PokemonDexService::class.java) }
}

fun providerRetrofit(): Retrofit{
   return Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}