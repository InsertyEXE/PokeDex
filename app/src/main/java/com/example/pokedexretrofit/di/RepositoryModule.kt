package com.example.pokedexretrofit.di

import com.example.pokedexretrofit.rest.MainRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { MainRepository(get()) }
}