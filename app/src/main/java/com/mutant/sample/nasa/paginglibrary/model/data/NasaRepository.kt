package com.mutant.sample.nasa.paginglibrary.model.data

import com.mutant.sample.nasa.paginglibrary.model.api.ApodService
import com.mutant.sample.nasa.paginglibrary.model.db.ApodLocalCache

class NasaRepository(private val service: ApodService, private val cache: ApodLocalCache) {
}