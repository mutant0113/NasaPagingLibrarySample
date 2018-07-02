package com.mutant.sample.nasa.paginglibrary.ui

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.view.ViewGroup
import com.mutant.sample.nasa.paginglibrary.model.Apod

class ApodsAdapter : PagedListAdapter<Apod, ApodViewHolder>(APOD_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApodViewHolder {
        return ApodViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ApodViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener({
            (it.context as MainActivity).showDetailView(getItem(position))
        })
    }

    companion object {
        val APOD_COMPARATOR = object : DiffUtil.ItemCallback<Apod>() {
            override fun areItemsTheSame(oldItem: Apod?, newItem: Apod?): Boolean =
                    oldItem?.title == newItem?.title

            override fun areContentsTheSame(oldItem: Apod?, newItem: Apod?): Boolean =
                    oldItem == newItem
        }

    }
}