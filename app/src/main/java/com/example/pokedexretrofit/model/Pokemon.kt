package com.example.pokedexretrofit.model

data class Pokemon(
    val id: Int,
    val name: String,
    val types: List<PokemonType>,
    val stats: List<Stats>,
    val height: Int,
    val weight: Int
)

data class PokemonType(val type: NameTypes)

data class NameTypes(val name: String)

data class Stats(val base_stat: Int)





