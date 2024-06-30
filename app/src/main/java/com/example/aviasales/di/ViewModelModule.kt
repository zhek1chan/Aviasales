package com.example.aviasales.di

import com.example.aviasales.presentation.main.MainViewModel
import com.example.aviasales.presentation.searchfilter.SearchFilterViewModel
import com.example.aviasales.presentation.tickets.TicketsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        MainViewModel(recsInteractor = get(), fromCityInteractor = get())
    }
    viewModel {
        SearchFilterViewModel(ticketsRecsInteractor = get())
    }

    viewModel {
        TicketsViewModel(ticksInteractor = get())
    }

}