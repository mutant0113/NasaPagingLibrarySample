package com.mutant.sample.nasa.paginglibrary

import android.util.Log

object DebugUtils {
    private val TAG = DebugUtils::class.java.simpleName
    private var debugEnabled: Boolean = false

    fun setDebugEnabled(enabled: Boolean) {
        debugEnabled = enabled
    }

    fun isDebugEnabled(): Boolean {
        return debugEnabled
    }

    fun v(msg: String) {
        v(TAG, msg)
    }

    fun v(tag: String, msg: String) {
        if (debugEnabled) {
            Log.v(tag, msg)
        }
    }

    fun i(msg: String) {
        i(TAG, msg)
    }

    fun i(tag: String, msg: String) {
        if (debugEnabled) {
            Log.i(tag, msg)
        }
    }

    fun d(msg: String) {
        d(TAG, msg)
    }

    fun d(tag: String, msg: String) {
        if (debugEnabled) {
            Log.d(tag, msg)
        }
    }

    fun w(msg: String) {
        w(TAG, msg)
    }

    fun w(tag: String, msg: String) {
        if (debugEnabled) {
            Log.w(tag, msg)
        }
    }

    fun wtf(msg: String) {
        wtf(TAG, msg)
    }

    fun wtf(tag: String, msg: String) {
        if (debugEnabled) {
            Log.wtf(tag, msg)
        }
    }
}