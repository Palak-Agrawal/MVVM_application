package com.example.mvvmsampleproject.data.network.responses

import com.example.mvvmsampleproject.data.db.entities.Quote

data class QuotesResponse (
    val isSuccessful : Boolean,
    val quotes: List<Quote>
){
}