package com.luislenes.pokedexapp.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.luislenes.pokedexapp.R
import com.luislenes.pokedexapp.persistence.model.Pokemon
import com.luislenes.pokedexapp.persistence.remote.PokeApiGenerator
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find
import java.text.DecimalFormat

class PokeListAdapter(private val context: Context) : RecyclerView.Adapter<PokemonViewHolder>() {

    var pokemonList: MutableList<Pokemon>? = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder(LayoutInflater.from(context).inflate(R.layout.pokemon_item, parent, false))
    }

    override fun getItemCount(): Int {
        return pokemonList?.size ?: 0
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.pokeId.text = applyPokeIdFormat(pokemonList?.get(position)?.id ?: 0)
        Picasso.get().load(PokeApiGenerator.instance.spriteUrlFormat.format(pokemonList?.get(position)?.id))
            .into(holder.image)
        holder.name.text = pokemonList?.get(position)?.name
    }

    private fun applyPokeIdFormat(pokeId: Int): String {
        val df = DecimalFormat("000")
        return "#${df.format(pokeId)}"
    }
}

class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val image = itemView.find<ImageView>(R.id.image)
    val pokeId = itemView.find<TextView>(R.id.pokeId)
    val name = itemView.find<TextView>(R.id.name)
}