package com.contreras.rodrigo.poketinder

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private fun getRetrofit(): Retrofit {
    val httpClient = OkHttpClient.Builder().addInterceptor { chain ->
        val newRequest = chain.request().newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("NAME-HEADER", "VALUE-HEADER")
            .build()
        chain.proceed(newRequest)
    }

    val client = httpClient.build()

    return Retrofit.Builder()
        .baseUrl("https://pokeapi.co")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
}

