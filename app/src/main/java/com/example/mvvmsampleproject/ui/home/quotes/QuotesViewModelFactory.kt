package com.example.mvvmsampleproject.ui.home.quotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmsampleproject.data.repository.QuotesRepository
import com.example.mvvmsampleproject.data.repository.UserRepository

@Suppress("UNCHECKED_CAST")
class QuotesViewModelFactory(private val repository : QuotesRepository): ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return QuotesViewModel(repository) as T
    }
}