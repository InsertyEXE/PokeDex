package com.example.pokedexretrofit.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokedexretrofit.R
import com.example.pokedexretrofit.databinding.ItemPokemonBinding
import kotlinx.android.synthetic.main.item_pokemon.view.*


class PokemonAdapter(private val items: List<Pokemon>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(pokemon: Pokemon) {
            ItemPokemonBinding.bind(itemView).apply {
                val numeroFormatado: String = pokemon.id.toString().padStart(3, '0')
                val imgThumb = "https://www.serebii.net/pokemongo/pokemon/$numeroFormatado.png"

                itemView.item_txt_nome.text = pokemon.name.capitalize()
                itemView.item_txt_numero.text = "N° $numeroFormatado"
                Glide.with(itemView).load(imgThumb).into(itemView.item_img_pokemon)

                if (pokemon.types.size > 1) {
                    itemView.item_txt_type1.text = pokemon.types[0].type.name
                    itemView.item_txt_type2.text = pokemon.types[1].type.name
                } else {
                    itemView.item_txt_type1.text = pokemon.types[0].type.name
                    itemView.item_txt_type2.visibility = View.GONE
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        PokemonViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PokemonViewHolder -> {
                holder.bind(items[position])
            }
        }
    }

    override fun getItemCount() = items.size
}