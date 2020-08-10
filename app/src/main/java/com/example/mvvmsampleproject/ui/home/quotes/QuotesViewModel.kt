package com.example.mvvmsampleproject.ui.home.quotes

import androidx.lifecycle.ViewModel
import com.example.mvvmsampleproject.data.repository.QuotesRepository
import com.example.mvvmsampleproject.util.lazyDeferred

class QuotesViewModel(
    repository : QuotesRepository
) : ViewModel() {
    val quotes by lazyDeferred{ repository.getQuotes()}
}