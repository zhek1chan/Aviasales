package com.example.aviasales

import android.app.Application
import com.example.aviasales.di.dataModule
import com.example.aviasales.di.domainModule
import com.example.aviasales.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.context.GlobalContext.startKoin

class App : Application(), KoinComponent {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                dataModule,
                domainModule,
                viewModelModule
            )
        }
        instance = this
    }

    companion object {
        lateinit var instance: App
    }
}

