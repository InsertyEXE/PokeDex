<h1 align="center">PokeDex</h1>



Uma Pokedex simples, mostrando informações básicas dos 151 primeiros pokemons (Primeira geração).
Construido e feito para o sistema operacional Android com o android minimo Lollipop(SDK 21).
Essa aplicação foi construida utilizando Coroutines, Retrofit, KOIN, arquitetura MVVM, Glide.

![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/kotlin-%230095D5.svg?style=for-the-badge&logo=kotlin&logoColor=white) 

<img src="/preview/imgs.png"/>

## API Pokemon
<img src="https://user-images.githubusercontent.com/24237865/83422649-d1b1d980-a464-11ea-8c91-a24fdf89cd6b.png" align="left" width="21%"/>

Essa PokeDex utiliza a [PokeApi](<https://pokeapi.co/api/v2/>).
<p>A PokeApi prove uma interface de RESTful API com bastantes detalhes.</p>
<br></br>

## Features
* Architecture: MVVM
  * data: Configurações do Retrofit para fazer a requisição a API 
  * di: Configurações da biblitoeca KOIN 
  * model: modelo das classes

* Lenguages:
  * ![Kotlin](https://img.shields.io/badge/kotlin-%230095D5.svg?style=for-the-badge&logo=kotlin&logoColor=white) 100%  

* Library:
  * Retrofit: 'com.squareup.retrofit2:retrofit:2.9.0' [Site retrofit squareup](<https://square.github.io/retrofit/>)
  * Gson: 'com.squareup.retrofit2:converter-gson:2.9.0' 
  * viewBinding: viewBinding{ enabled = true }
  * Glide: 'com.github.bumptech.glide:glide:4.13.0' [Biblioteca Glide](<https://github.com/bumptech/glide>)
  * viewModel: "androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.0"
  * KOIN: "io.insert-koin:koin-android:3.2.0" [Bliblioteca KOIN](<https://insert-koin.io>)
  * coroutines: 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9' & 'androidx.lifecycle:lifecycle-runtime-ktx:2.3.1'
  
## Try
App: [Download PokeDex](https://github.com/InsertyEXE/PokeDex/blob/master/PokeDex.apk)

