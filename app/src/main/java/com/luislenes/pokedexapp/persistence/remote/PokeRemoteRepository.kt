package com.luislenes.pokedexapp.persistence.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.luislenes.pokedexapp.persistence.PokemonRepository
import com.luislenes.pokedexapp.persistence.model.Pokemon
import com.luislenes.pokedexapp.persistence.model.PokemonDetails
import com.luislenes.pokedexapp.persistence.model.PokemonList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokeRemoteRepository : PokemonRepository {

    private val pokeList = MutableLiveData<MutableList<Pokemon>>()
    private var isLoading = false

    private var limit = 20
    private var offset = 0

    override fun getPokemonList(): LiveData<MutableList<Pokemon>> {
        if (!isLoading) {
            PokeApiGenerator.instance.pokeApi().getPokemonList(limit, offset)
                .enqueue(getPokemonListCallback())
            isLoading = true
        }
        return pokeList
    }

    override fun getPokemonByName(name: String): LiveData<PokemonDetails> {
        val pokemon = MutableLiveData<PokemonDetails>()
        PokeApiGenerator.instance.pokeApi().getPokemonByName(name)
            .enqueue(getPokemonByNameCallback(pokemon))
        return pokemon
    }

    private fun getPokemonListCallback(): Callback<PokemonList> {
        return object: Callback<PokemonList> {
            override fun onFailure(call: Call<PokemonList>, t: Throwable) {
                t.printStackTrace()
                isLoading = false
            }

            override fun onResponse(call: Call<PokemonList>, response: Response<PokemonList>) {
                isLoading = false
                pokeList.value = response.body()?.results
                offset += 20
            }
        }
    }

    private fun getPokemonByNameCallback(pokemon: MutableLiveData<PokemonDetails>): Callback<PokemonDetails> {
        return object: Callback<PokemonDetails> {
            override fun onFailure(call: Call<PokemonDetails>, t: Throwable) {
                pokemon.value = null
            }

            override fun onResponse(call: Call<PokemonDetails>, response: Response<PokemonDetails>) {
                pokemon.value = response.body()
            }
        }
    }
}