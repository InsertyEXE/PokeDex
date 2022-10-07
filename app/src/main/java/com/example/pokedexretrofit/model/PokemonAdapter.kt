package com.example.pokedexretrofit.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokedexretrofit.R
import com.example.pokedexretrofit.databinding.ItemPokemonBinding
import kotlinx.android.synthetic.main.item_pokemon.view.*


class PokemonAdapter(private val items: List<Pokemon>, private val onItemClick: (Pokemon) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(pokemon: Pokemon, onItemClick: (Pokemon) -> Unit) {

            itemView.setOnClickListener { onItemClick(pokemon) }

            ItemPokemonBinding.bind(itemView).apply {
                val formatedNumber: String = pokemon.id.toString().padStart(3, '0')
                val imgThumb = "https://www.serebii.net/pokemongo/pokemon/$formatedNumber.png"

                itemView.item_txt_nome.text = pokemon.name.capitalize()
                itemView.item_txt_numero.text = "N° $formatedNumber"
                Glide.with(itemView).load(imgThumb).into(itemView.item_img_pokemon)

                val pokeTypeFirst = pokemon.types[0].type.name

                itemView.view_item.setBackgroundColor(ContextCompat.getColor(itemView.context,
                    ConverterTypes.valueOf(pokeTypeFirst).colorType
                ))

                if (pokemon.types.size > 1) {

                    ConverterTypes.values().forEach {

                        if (it.name == pokeTypeFirst) {
                            itemView.item_txt_type1.text = pokeTypeFirst
                            itemView.item_txt_type1.setBackgroundColor(ContextCompat.getColor(itemView.context, it.colorType))
                        }

                        if (it.name == pokemon.types[1].type.name) {
                            itemView.item_txt_type2.text = pokemon.types[1].type.name
                            itemView.item_txt_type2.setBackgroundColor(ContextCompat.getColor(itemView.context, it.colorType))
                        }

                    }
                } else {
                    ConverterTypes.values().forEach {
                        if (it.name == pokeTypeFirst){
                            itemView.item_txt_type1.setBackgroundColor(ContextCompat.getColor(itemView.context, it.colorType))
                            itemView.item_txt_type1.text = pokeTypeFirst
                            itemView.item_txt_type2.visibility = View.GONE
                        }
                    }

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
                holder.bind(items[position], onItemClick)
            }
        }
    }

    override fun getItemCount() = items.size
}