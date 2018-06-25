package com.mutant.sample.nasa.paginglibrary.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.mutant.sample.nasa.paginglibrary.Injection.provideViewModelFactory
import com.mutant.sample.nasa.paginglibrary.R
import com.mutant.sample.nasa.paginglibrary.model.QueryDate
import com.mutant.sample.nasa.paginglibrary.viewmodel.ApodViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ApodViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO inject view model factory
        viewModel = ViewModelProviders.of(this, provideViewModelFactory(this))
                .get(ApodViewModel::class.java)
        initAdapter()

        // TODO Choose by user
        val queryDate = QueryDate("2018-06-01", "2018-06-24")
        viewModel.searchApod(queryDate)
        initSearch(queryDate)
    }

    private fun initAdapter() {
        val adapter = ApodsAdapter()
        list_apods.adapter = adapter
        viewModel.apods.observe(this, Observer {
            showEmptyList(it?.size == 0)
            adapter.submitList(it)
        })

        viewModel.networkErrors.observe(this, Observer {
            // TODO change view
            Toast.makeText(this, "\uD83D\uDE28 Wooops $it", Toast.LENGTH_LONG).show()
        })
    }

    private fun showEmptyList(show: Boolean) {
        list_apods.visibility = if(show) View.GONE else View.VISIBLE
        text_view_empty_list.visibility = if(show) View.VISIBLE else View.GONE
    }

    private fun initSearch(queryDate: QueryDate) {
        // TODO search
    }

}
