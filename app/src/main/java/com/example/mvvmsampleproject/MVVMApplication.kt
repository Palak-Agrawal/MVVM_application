package com.example.mvvmsampleproject

import android.app.Application
import com.example.mvvmsampleproject.data.db.AppDataBase
import com.example.mvvmsampleproject.data.network.MyApi
import com.example.mvvmsampleproject.data.network.NetworkConnectionInterceptor
import com.example.mvvmsampleproject.data.preferences.PreferenceProvider
import com.example.mvvmsampleproject.data.repository.QuotesRepository
import com.example.mvvmsampleproject.data.repository.UserRepository
import com.example.mvvmsampleproject.ui.auth.AuthViewModelFactory
import com.example.mvvmsampleproject.ui.home.profile.ProfileViewModelFactory
import com.example.mvvmsampleproject.ui.home.quotes.QuotesViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MVVMApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@MVVMApplication))
        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { AppDataBase(instance()) }
        bind() from singleton { PreferenceProvider(instance()) }
        bind() from singleton { UserRepository(instance(),instance() ) }
        bind() from singleton { QuotesRepository(instance(),instance() ,instance() ) }
        bind() from provider {AuthViewModelFactory(instance())}
        bind() from provider {ProfileViewModelFactory(instance())}
        bind() from provider { QuotesViewModelFactory(instance()) }
    }
}