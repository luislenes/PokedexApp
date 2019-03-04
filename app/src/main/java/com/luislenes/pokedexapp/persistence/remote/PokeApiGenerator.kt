package com.luislenes.pokedexapp.persistence.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokeApiGenerator {

    companion object {
        val instance = PokeApiGenerator()
    }

    private var api: PokeApi? = null
    private val baseUrl = "https://pokeapi.co/api/v2/"
    val spriteUrlFormat = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/%d.png"

    fun pokeApi(): PokeApi {
        if (api == null) {
            api = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(PokeApi::class.java)
        }
        return api!!
    }

    private fun getOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    }
}