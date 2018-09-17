package com.oliveira.silas.cad.ui.main

import android.app.Application
import com.oliveira.silas.cad.di.*
import org.koin.android.ext.android.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this,
                listOf(repositoryModule,
                        viewModelModule,
                        interactorModule,
                        retrofit))

    }
}