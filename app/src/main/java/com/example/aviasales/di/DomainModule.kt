package com.example.aviasales.di

import com.example.aviasales.data.RecsRepositoryImpl
import com.example.aviasales.data.SharedPrefsRepositoryImpl
import com.example.aviasales.data.TicketsRecsRepositoryImpl
import com.example.aviasales.data.TicketsRepositoryImpl
import com.example.aviasales.domain.interactor.FromCityInteractor
import com.example.aviasales.domain.interactor.FromCityInteractorImpl
import com.example.aviasales.domain.interactor.RecsInteractor
import com.example.aviasales.domain.interactor.RecsInteractorImpl
import com.example.aviasales.domain.interactor.TicketsInteractor
import com.example.aviasales.domain.interactor.TicketsInteractorImpl
import com.example.aviasales.domain.interactor.TicketsRecsInteractor
import com.example.aviasales.domain.interactor.TicketsRecsInteractorImpl
import com.example.aviasales.domain.repository.RecsRepository
import com.example.aviasales.domain.repository.SharedPrefsRepository
import com.example.aviasales.domain.repository.TicketsRecsRepository
import com.example.aviasales.domain.repository.TicketsRepository
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