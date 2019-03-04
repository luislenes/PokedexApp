package com.luislenes.pokedexapp.persistence.remote

import com.luislenes.pokedexapp.persistence.model.PokemonDetails
import com.luislenes.pokedexapp.persistence.model.PokemonList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {

    @GET("pokemon")
    fun getPokemonList(@Query("limit") limit: Int, @Query("offset") offset: Int): Call<PokemonList>

    @GET("pokemon/{name}")
    fun getPokemonByName(@Path("name") name: String): Call<PokemonDetails>
}