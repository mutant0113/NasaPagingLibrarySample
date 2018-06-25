package com.mutant.sample.nasa.paginglibrary.model.api

import com.mutant.sample.nasa.paginglibrary.BuildConfig
import com.mutant.sample.nasa.paginglibrary.model.Apod
import com.mutant.sample.nasa.paginglibrary.model.QueryDate
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// TODO why?
fun searchApods(service: NasaService, queryDate: QueryDate): Observable<List<Apod>> {
    return service.searchApods(NasaService.apiKey, queryDate.startDate, queryDate.endDate)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}

interface NasaService {

    @GET("planetary/apod")
    fun searchApods(@Query("api_key") apiKey: String,
                    @Query("start_date") startDate: String,
                    @Query("end_date") endDate: String): Observable<List<Apod>>

    companion object {
        private const val BASE_URL = "https://api.nasa.gov/"
        // Because there is a query limit, so we use "DEMO_KEY" in debug mode to avoid it.
        val apiKey = if (BuildConfig.DEBUG) "DEMO_KEY" else "jlG8oXiEr0obpG0dZ31Q1M3dIB8ALIWSearZY5pL"

        fun create(): NasaService {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder()
                    .addInterceptor(logger)
                    .build()
            return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
                    .create(NasaService::class.java)
        }
    }
}