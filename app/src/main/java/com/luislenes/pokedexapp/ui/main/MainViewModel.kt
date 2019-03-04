package com.luislenes.pokedexapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.luislenes.pokedexapp.persistence.model.Pokemon
import com.luislenes.pokedexapp.persistence.remote.PokeRemoteRepository

class MainViewModel : ViewModel() {

    private val repository: PokeRemoteRepository = PokeRemoteRepository()
    private var _pokemonList: LiveData<MutableList<Pokemon>>
    val pokemonList get() = _pokemonList

    init {
        _pokemonList = repository.getPokemonList()
    }

    fun getNextPokemonPage() {
        _pokemonList = repository.getPokemonList()
    }
}