package com.mutant.sample.nasa.paginglibrary.contract

import com.mutant.sample.nasa.paginglibrary.model.Apod

interface DetailContract {

    interface View {

        fun setView(apod: Apod)
    }

    interface ViewModel {

        fun showDetailPage(apod: Apod)
    }
}