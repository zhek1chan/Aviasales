package com.example.aviasales.di

import com.example.data.RecsRepositoryImpl
import com.example.data.SharedPrefsRepositoryImpl
import com.example.data.TicketsRecsRepositoryImpl
import com.example.data.TicketsRepositoryImpl
import com.example.java.interactor.FromCityInteractor
import com.example.java.interactor.FromCityInteractorImpl
import com.example.java.interactor.RecsInteractor
import com.example.java.interactor.RecsInteractorImpl
import com.example.java.interactor.TicketsInteractor
import com.example.java.interactor.TicketsInteractorImpl
import com.example.java.interactor.TicketsRecsInteractor
import com.example.java.interactor.TicketsRecsInteractorImpl
import com.example.java.repository.RecsRepository
import com.example.java.repository.SharedPrefsRepository
import com.example.java.repository.TicketsRecsRepository
import com.example.java.repository.TicketsRepository
import org.koin.dsl.module

val domainModule = module {

    factory<RecsInteractor> {
        RecsInteractorImpl(repository = get())
    }
    single<RecsRepository> {
        RecsRepositoryImpl(networkClient = get(), mapper = get())
    }

    factory<TicketsInteractor> {
        TicketsInteractorImpl(repository = get())
    }

    single<TicketsRepository> {
        TicketsRepositoryImpl(networkClient = get(), mapper = get())
    }

    factory<TicketsRecsInteractor> {
        TicketsRecsInteractorImpl(repository = get())
    }

    single<TicketsRecsRepository> {
        TicketsRecsRepositoryImpl(
            networkClient = get(),
            mapper = get()
        )
    }

    factory<FromCityInteractor> {
        FromCityInteractorImpl(repository = get())
    }

    single<SharedPrefsRepository> {
        SharedPrefsRepositoryImpl(sharedPrefsStorage = get())
    }
}