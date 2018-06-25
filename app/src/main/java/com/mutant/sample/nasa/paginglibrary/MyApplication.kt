package com.mutant.sample.nasa.paginglibrary

import android.app.Application

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        DebugUtils.setDebugEnabled(BuildConfig.DEBUG)
    }
}