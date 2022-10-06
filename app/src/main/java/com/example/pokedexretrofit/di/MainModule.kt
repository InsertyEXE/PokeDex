package com.example.pokedexretrofit.di

import com.example.pokedexretrofit.data.MainRepository
import com.example.pokedexretrofit.model.IMainView
import com.example.pokedexretrofit.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel { (view: IMainView) -> MainViewModel(view, MainRepository(get())) }
}