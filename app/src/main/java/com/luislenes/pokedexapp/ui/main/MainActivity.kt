package com.luislenes.pokedexapp.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.luislenes.pokedexapp.R
import com.luislenes.pokedexapp.ui.adapters.PokeListAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    private lateinit var pokeListAdapter: PokeListAdapter
    private lateinit var layoutManager: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpRecyclerView()
        setUpViewModel()
    }

    private fun setUpRecyclerView() {
        pokeListAdapter =  PokeListAdapter(this)
        pokemonList.adapter = pokeListAdapter
        layoutManager = GridLayoutManager(this, 3)
        pokemonList.layoutManager = layoutManager
        setUpScrollListener()
    }

    private fun setUpViewModel() {
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        mainViewModel.pokemonList.observe(this, Observer {
            pokeListAdapter.pokemonList?.addAll(it)
            pokeListAdapter.notifyDataSetChanged()
        })
    }

    private fun setUpScrollListener() {
        pokemonList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    if ((layoutManager.childCount + layoutManager.findFirstVisibleItemPosition()) >= layoutManager.itemCount) {
                        mainViewModel.getNextPokemonPage()
                    }
                }
            }
        })
    }
}
