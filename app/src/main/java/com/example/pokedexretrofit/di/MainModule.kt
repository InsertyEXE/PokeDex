package com.example.pokedexretrofit.di

import com.example.pokedexretrofit.rest.MainRepository
import com.example.pokedexretrofit.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel { MainViewModel(MainRepository(get())) }
}