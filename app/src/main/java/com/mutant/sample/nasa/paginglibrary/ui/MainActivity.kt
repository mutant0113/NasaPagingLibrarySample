package com.mutant.sample.nasa.paginglibrary.ui

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mutant.sample.nasa.paginglibrary.ActivityUtils
import com.mutant.sample.nasa.paginglibrary.Injection.provideViewModelFactory
import com.mutant.sample.nasa.paginglibrary.R
import com.mutant.sample.nasa.paginglibrary.model.Apod
import com.mutant.sample.nasa.paginglibrary.viewmodel.ApodViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ApodViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this, provideViewModelFactory(this))
                .get(ApodViewModel::class.java)

        var apodsFragment = supportFragmentManager.findFragmentById(R.id.content_main) as ApodsFragment?
        if (apodsFragment == null) {
            apodsFragment = ApodsFragment.newInstance()
            ActivityUtils.addFragment(supportFragmentManager, apodsFragment, R.id.content_main, TAG_FRAGMENT_APODS)
        }
        apodsFragment.setViewModel(viewModel)
    }

    fun showDetailView(apod: Apod?) {
        if (supportFragmentManager.findFragmentById(R.id.content_main) !is DetailFragment) {
            if (apod != null) {
                val detailFragment = DetailFragment.newInstance(apod)
                ActivityUtils.addFragment(supportFragmentManager, detailFragment, R.id.content_main, TAG_FRAGMENT_DETAIL)
                ActivityUtils.hideFragment(supportFragmentManager, TAG_FRAGMENT_APODS)
            }
        }
    }

    override fun onBackPressed() {
        var forgroundFragment = supportFragmentManager.findFragmentById(R.id.content_main)
        if (forgroundFragment is DetailFragment) {
            ActivityUtils.removeFragment(supportFragmentManager, forgroundFragment)
            ActivityUtils.showFragment(supportFragmentManager, TAG_FRAGMENT_APODS)
        } else {
            super.onBackPressed()
        }
    }

    companion object {
        private const val TAG_FRAGMENT_APODS = "TAG_FRAGMENT_APODS"
        private const val TAG_FRAGMENT_DETAIL = "TAG_FRAGMENT_DETAIL"
    }

}
