package com.mutant.sample.nasa.paginglibrary.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.mutant.sample.nasa.paginglibrary.DateUtils
import com.mutant.sample.nasa.paginglibrary.DialogUtils
import com.mutant.sample.nasa.paginglibrary.Injection.provideViewModelFactory
import com.mutant.sample.nasa.paginglibrary.R
import com.mutant.sample.nasa.paginglibrary.model.QueryDate
import com.mutant.sample.nasa.paginglibrary.viewmodel.ApodViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ApodViewModel

    // Default 2018-06-01 to 2018-06-30
    private var queryDate = QueryDate(Date(1527782400000), Date(1530288000000))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this, provideViewModelFactory(this))
                .get(ApodViewModel::class.java)
        initAdapter()

        text_view_start_date.text = queryDate.getStartDateStr()
        text_view_end_date.text = queryDate.getEndDateStr()
        initListeners()
        search()
    }

    private fun initListeners() {
        text_view_start_date.setOnClickListener({
            val minDateMillisecs = 803232000000 // 1995/06/15
            DialogUtils.createPickDateDialog(this@MainActivity, it as TextView, minDateMillisecs).show()
        })

        text_view_end_date.setOnClickListener({
            val selectDateMillisecs = DateUtils.dateStrToMillisecs((text_view_start_date as TextView).text.toString())
            DialogUtils.createPickDateDialog(this@MainActivity, it as TextView, selectDateMillisecs).show()
        })

        button_search.setOnClickListener({
            search()
        })
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
        list_apods.visibility = if (show) View.GONE else View.VISIBLE
        text_view_empty_list.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun search() {
        updateQueryDate()
        viewModel.searchApod(queryDate)
    }

    private fun updateQueryDate() {
        val startDate = Date(DateUtils.dateStrToMillisecs(text_view_start_date.text.toString()))
        val endDate = Date(DateUtils.dateStrToMillisecs(text_view_end_date.text.toString()))
        queryDate = QueryDate(startDate, endDate)
    }

}
