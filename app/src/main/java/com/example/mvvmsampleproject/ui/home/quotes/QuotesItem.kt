package com.example.mvvmsampleproject.ui.home.quotes

import com.example.mvvmsampleproject.R
import com.example.mvvmsampleproject.data.db.entities.Quote
import com.example.mvvmsampleproject.databinding.ListQuotesBinding
import com.xwray.groupie.databinding.BindableItem

class QuotesItem (
    private  val quote : Quote
): BindableItem<ListQuotesBinding>() {

    override fun getLayout() = R.layout.list_quotes

    override fun bind(viewBinding: ListQuotesBinding, position: Int) {
       viewBinding.setQuote(quote)
    }
}