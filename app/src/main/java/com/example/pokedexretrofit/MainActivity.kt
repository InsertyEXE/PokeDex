package com.example.pokedexretrofit

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.pokedexretrofit.databinding.ActivityMainBinding
import com.example.pokedexretrofit.model.ConverterTypes
import com.example.pokedexretrofit.model.IMainView
import com.example.pokedexretrofit.model.Pokemon
import com.example.pokedexretrofit.model.PokemonAdapter
import kotlinx.android.synthetic.main.item_dialog_pokemon.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity(), IMainView {

    private lateinit var binding: ActivityMainBinding
    private var pokemons: MutableList<Pokemon> = arrayListOf()
    private val viewmodel: MainViewModel by viewModel { parametersOf(this@MainActivity) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvDex.layoutManager = GridLayoutManager(this, 2)
        if (pokemons.isEmpty()) viewmodel.listAllPokemon()
    }

    override fun onResume() {
        super.onResume()

        viewmodel.pokemon.observe(this) {
            if (!pokemons.contains(it)) pokemons.add(it)
            pokemons.sortBy { pokemon -> pokemon.id }
            binding.rvDex.adapter =
                PokemonAdapter(pokemons) { pokemon -> openDialogPokemon(pokemon) }

        }

        viewmodel.pokemonQuery.observe(this) { pokemonResult ->
            pokemons.clear()
            binding.rvDex.adapter?.notifyDataSetChanged()
            pokemonResult?.let { pokemons.add(it) }
            if (pokemons.isNotEmpty()) binding.rvDex.adapter =
                PokemonAdapter(pokemons) { openDialogPokemon(it) }
        }

        binding.fbSearch.setOnClickListener {
            if (binding.edtDex.text.toString().isNotEmpty()) {
                pokemons.clear()
                binding.rvDex.adapter?.notifyDataSetChanged()
                viewmodel.fetchPokemon(binding.edtDex.text.toString())
            }
        }

        binding.edtDex.addTextChangedListener { text -> if (text?.isEmpty() == true) viewmodel.listAllPokemon() }
    }

    private fun openDialogPokemon(pokemon: Pokemon) {

        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater.inflate(R.layout.item_dialog_pokemon, null)

        val formatedNumber: String = pokemon.id.toString().padStart(3, '0')
        val imgThumb = "https://www.serebii.net/pokemongo/pokemon/$formatedNumber.png"

        Glide.with(this).load(imgThumb).into(inflater.dialog_img_pokemon)

        inflater.dialog_view_item.setBackgroundColor(
            ContextCompat.getColor(
                this,
                ConverterTypes.valueOf(pokemon.types[0].type.name).colorType
            )
        )

        inflater.dialog_txt_numero.text = "N° $formatedNumber"
        inflater.dialog_txt_nome.text = pokemon.name.capitalize()
        inflater.dialog_txt_type1.text = pokemon.types[0].type.name
        inflater.dialog_txt_type1.setBackgroundColor(
            ContextCompat.getColor(
                this,
                ConverterTypes.valueOf(pokemon.types[0].type.name).colorType
            )
        )
        inflater.dialog_txt_type2.visibility = View.GONE
        inflater.height_info.text = pokemon.height.toString()
        inflater.width_info.text = pokemon.weight.toString()
        inflater.hp_info.text = pokemon.stats[0].base_stat.toString()
        inflater.attack_info.text = pokemon.stats[1].base_stat.toString()
        inflater.defense_info.text = pokemon.stats[2].base_stat.toString()

        if (pokemon.types.size > 1) {
            inflater.dialog_txt_type2.visibility = View.VISIBLE
            inflater.dialog_txt_type2.text = pokemon.types[1].type.name
            inflater.dialog_txt_type2.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    ConverterTypes.valueOf(pokemon.types[1].type.name).colorType
                )
            )
        }

        inflater.dialog_btn.setOnClickListener { shareInfo(pokemon) }
        builder.setView(inflater).create().show()
    }

    private fun shareInfo(pokemon: Pokemon) {
        val types = arrayListOf<String>()
        val sendIntent = Intent()
        if (pokemon.types.size > 1) {
            types.add(pokemon.types[0].type.name)
            types.add(pokemon.types[1].type.name)
        } else types.add(pokemon.types[0].type.name)

        val infos = "--- POKEMON INFO ---\n" +
                "name: ${pokemon.name}\n" +
                "PokeDex number: ${pokemon.id}\n" +
                "Types: $types\n" +
                "Hp: ${pokemon.stats[0].base_stat}\n" +
                "Attack: ${pokemon.stats[1].base_stat}\n" +
                "Defense: ${pokemon.stats[2].base_stat}"

        sendIntent.apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, infos)
            type = "text/plain"
        }

        startActivity(Intent.createChooser(sendIntent, null))
    }

    override fun showProgress(enabled: Boolean) {
        if (enabled) binding.progressCirc.visibility = View.VISIBLE
        else binding.progressCirc.visibility = View.GONE
    }

}