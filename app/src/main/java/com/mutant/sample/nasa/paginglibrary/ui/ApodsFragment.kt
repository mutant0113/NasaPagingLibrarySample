package com.mutant.sample.nasa.paginglibrary.ui

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.mutant.sample.nasa.paginglibrary.DateUtils
import com.mutant.sample.nasa.paginglibrary.DialogUtils
import com.mutant.sample.nasa.paginglibrary.R
import com.mutant.sample.nasa.paginglibrary.model.QueryDate
import com.mutant.sample.nasa.paginglibrary.viewmodel.ApodViewModel
import kotlinx.android.synthetic.main.fragment_apods.*
import kotlinx.android.synthetic.main.fragment_apods.view.*
import java.util.*

class ApodsFragment : Fragment() {

    private lateinit var rootView: View

    // Default 2018-06-01 to 2018-06-30
    private var queryDate = QueryDate(Date(1527782400000), Date(1530288000000))
    private lateinit var viewModel: ApodViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        rootView = LayoutInflater.from(context).inflate(R.layout.fragment_apods, container, false)
        setView()
        return rootView
    }

    fun setViewModel(viewModel: ApodViewModel) {
        this.viewModel = viewModel
    }

    fun setView() {
        initAdapter()
        rootView.text_view_start_date.text = queryDate.getStartDateStr()
        rootView.text_view_end_date.text = queryDate.getEndDateStr()
        initListeners()
        search()
    }

    private fun initListeners() {
        rootView.text_view_start_date.setOnClickListener({
            val minDateMillisecs = 803232000000 // 1995/06/15
            DialogUtils.createPickDateDialog(context, it as TextView, minDateMillisecs).show()
        })

        rootView.text_view_end_date.setOnClickListener({
            val selectDateMillisecs = DateUtils.dateStrToMillisecs((text_view_start_date as TextView).text.toString())
            DialogUtils.createPickDateDialog(context, it as TextView, selectDateMillisecs).show()
        })

        rootView.button_search.setOnClickListener({
            search()
        })
    }

    private fun search() {
        updateQueryDate()
        viewModel.searchApod(queryDate)
    }

    private fun initAdapter() {
        val adapter = ApodsAdapter()
        rootView.list_apods.adapter = adapter

        viewModel.apods.observe(this, Observer {
            showEmptyList(it?.size == 0)
            adapter.submitList(it)
        })

        viewModel.networkErrors.observe(this, Observer {
            // TODO change view
            Toast.makeText(activity, "\uD83D\uDE28 Wooops $it", Toast.LENGTH_LONG).show()
        })
    }

    private fun showEmptyList(show: Boolean) {
        list_apods.visibility = if (show) View.GONE else View.VISIBLE
        text_view_empty_list.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun updateQueryDate() {
        val startDate = Date(DateUtils.dateStrToMillisecs(rootView.text_view_start_date.text.toString()))
        val endDate = Date(DateUtils.dateStrToMillisecs(rootView.text_view_end_date.text.toString()))
        queryDate = QueryDate(startDate, endDate)
    }

    companion object {

        fun newInstance(): ApodsFragment {
            return ApodsFragment()
        }
    }
}