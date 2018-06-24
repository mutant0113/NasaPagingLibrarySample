package com.mutant.sample.nasa.paginglibrary.model.api

import com.google.gson.annotations.SerializedName
import com.mutant.sample.nasa.paginglibrary.model.Apod

data class ApodSearchResponse(
        @SerializedName("total_count") val total: Int = 0,
        @SerializedName("apods") val apods: List<Apod> = emptyList())