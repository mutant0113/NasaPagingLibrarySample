package com.mutant.sample.nasa.paginglibrary.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.mutant.sample.nasa.paginglibrary.R
import com.mutant.sample.nasa.paginglibrary.model.Apod
import kotlinx.android.synthetic.main.apod_view_item.view.*

class ApodViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    fun bind(apod: Apod?) {
        if (apod == null) {

        } else {
            showApodData(apod)
        }
    }

    private fun showApodData(apod: Apod) {
        Glide.with(itemView.image_view_photo.context)
                .load(apod.url)
                .into(itemView.image_view_photo)
//                        .placeholder()
//                .error(R.drawable.ic_person)
//                .animate()
        itemView.text_view_title.text = apod.title
    }

    companion object {
        fun create(parent: ViewGroup): ApodViewHolder {
            val rootView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.apod_view_item, parent, false)
            return ApodViewHolder(rootView)
        }
    }
}