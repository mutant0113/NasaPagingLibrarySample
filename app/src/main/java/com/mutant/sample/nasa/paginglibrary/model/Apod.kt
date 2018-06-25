package com.mutant.sample.nasa.paginglibrary.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "apods")
data class Apod(
        @PrimaryKey
        @field:SerializedName("title")
        val title: String,

        @field:SerializedName("date")
        val date: String,

        @field:SerializedName("explanation")
        val explanation: String,

        @field:SerializedName("hdurl")
        val hdUrl: String?,

        @field:SerializedName("media_type")
        val mediaType: String,

        @field:SerializedName("url")
        val url: String
)
