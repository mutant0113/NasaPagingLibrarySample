package com.mutant.sample.nasa.paginglibrary.model.db

import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.mutant.sample.nasa.paginglibrary.model.Apod

@Dao
interface ApodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(apods: List<Apod>)

    @Query("SELECT * FROM apods WHERE (date >= :dateFrom) AND (date <= :dateTo) ORDER BY date DESC")
    fun queryByDate(dateFrom: String, dateTo: String): DataSource.Factory<Int, Apod>
}
