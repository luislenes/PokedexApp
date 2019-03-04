package com.luislenes.pokedexapp.persistence

import androidx.lifecycle.LiveData
import com.luislenes.pokedexapp.persistence.model.Pokemon
import com.luislenes.pokedexapp.persistence.model.PokemonDetails

interface PokemonRepository {

    fun getPokemonList(): LiveData<MutableList<Pokemon>>

    fun getPokemonByName(name: String): LiveData<PokemonDetails>
}