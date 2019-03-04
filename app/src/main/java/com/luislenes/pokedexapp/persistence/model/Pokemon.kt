package com.luislenes.pokedexapp.persistence.model

import com.google.gson.annotations.SerializedName

data class PokemonAbility(val name: String,
                          @SerializedName("is_hidden") val isHidden: Boolean)
data class PokemonType(val name: String)
data class PokemonStat(val name: String)
data class PokemonStatValue(@SerializedName("base_stat") val baseStat: Int,
                            val stat: PokemonStat)
data class PokemonSprites(@SerializedName("front_default") val frontDefault: String,
                          @SerializedName("front_shiny") val frontShiny: String)
data class PokemonDetails(val id: Int, val name: String, val height: Int, val weight: Int,
                          val abilities: List<PokemonAbility>, val types: List<PokemonType>,
                          val stats: List<PokemonStatValue>, val sprites: PokemonSprites)
data class Pokemon(val name: String, val url: String) {
    val id: Int
        get() {
            val list = url.split("/")
            return list[list.size - 2].toInt()
        }
}
data class PokemonList(val count: Int, val next: String, val previous: String, val results: MutableList<Pokemon>)