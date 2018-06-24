package com.mutant.sample.nasa.paginglibrary.model.data

import com.mutant.sample.nasa.paginglibrary.model.api.NasaService
import com.mutant.sample.nasa.paginglibrary.model.db.ApodLocalCache

class NasaRepository(private val service: NasaService, private val cache: ApodLocalCache) {
}