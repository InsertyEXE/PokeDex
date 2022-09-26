package com.example.pokedexretrofit.model

import com.example.pokedexretrofit.R

data class Pokemon(
    val id: Int,
    val name: String,
    val types: List<PokemonType>
)

data class PokemonType(
    val type: NameTypes
)

data class NameTypes(
    val name: String
)

enum class ConverterTypes(val nomeTipo: String, val colorTipo: Int)
{
    bug("bug", R.color.bug),
    dark("dark", R.color.dark),
    dragon("dragon", R.color.dragon),
    eletric("electric", R.color.electric),
    fairy("fairy", R.color.fairy),
    fight("fighting", R.color.fighting),
    fire("fire", R.color.fire),
    flying("flying", R.color.flying),
    ghost("ghost", R.color.ghost),
    grass("grass", R.color.grass),
    ground("ground", R.color.ground),
    ice("ice", R.color.ice),
    normal("normal", R.color.normal),
    poison("poison", R.color.poison),
    psychic("psychic", R.color.psychic),
    rock("rock", R.color.rock),
    steel("steel", R.color.steel),
    water("water", R.color.water)
}




