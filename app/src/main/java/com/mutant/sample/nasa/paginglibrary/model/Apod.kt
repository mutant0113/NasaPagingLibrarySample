package com.mutant.sample.nasa.paginglibrary.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "apods")
data class Apod(
        @PrimaryKey
        @field:SerializedName("id")
        private val id: Long,

        @field:SerializedName("date")
        private val date: String,

        @field:SerializedName("explanation")
        private val explanation: String,

        @field:SerializedName("hdurl")
        private val hdUrl: String,

        @field:SerializedName("media_type")
        private val mediaType: String,

        @field:SerializedName("title")
        private val title: String,

        @field:SerializedName("url")
        private val url: String
)
