package com.example.mvvmsampleproject.ui.home.quotes

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmsampleproject.R
import com.example.mvvmsampleproject.data.db.entities.Quote
import com.example.mvvmsampleproject.databinding.ProfileFragmentBinding
import com.example.mvvmsampleproject.util.Coroutines
import com.example.mvvmsampleproject.util.toast
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.quotes_fragment.*
import org.kodein.di.generic.instance
import org.kodein.di.Kodein
import org.kodein.di.android.x.kodein
import org.kodein.di.KodeinAware

class QuotesFragment : Fragment(), KodeinAware {
    override val kodein  by kodein()


    private lateinit var viewModel: QuotesViewModel
    private  val factory : QuotesViewModelFactory by instance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.quotes_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, factory).get(QuotesViewModel::class.java)
        bindUI()
        }

    private fun bindUI() = Coroutines.main {
        viewModel.quotes.await().observe( viewLifecycleOwner, Observer{
            initRecyclerView(it.toQuoteItem())
        })
    }

    private fun initRecyclerView(quoteItem: List<QuotesItem>) {
        val mAdapter = GroupAdapter<ViewHolder>().apply{
            addAll(quoteItem)
        }
        recyclerView.apply{
           layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mAdapter
        }
    }

    private fun List<Quote>.toQuoteItem() : List<QuotesItem>{
        return this.map{
            QuotesItem(it)
        }
    }
}



