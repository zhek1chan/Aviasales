package com.example.aviasales.di

import android.content.Context
import com.example.data.SharedPrefsStorage
import com.example.data.dto.DtoMappers
import com.example.data.network.Api
import com.example.data.network.NetworkClient
import com.example.data.network.RetrofitNetworkClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    single<NetworkClient> { RetrofitNetworkClient(androidContext(), api = get()) }
    single<Api> {
        Retrofit.Builder()
            .baseUrl("https://run.mocky.io/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }

    single {
        androidContext().getSharedPreferences(
            SharedPrefsStorage.CITY_FROM,
            Context.MODE_PRIVATE
        )
    }

    single { DtoMappers() }
    single { SharedPrefsStorage(sharedPreferences = get()) }
}