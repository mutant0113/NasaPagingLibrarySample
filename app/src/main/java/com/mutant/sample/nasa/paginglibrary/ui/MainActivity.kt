package com.mutant.sample.nasa.paginglibrary.ui

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mutant.sample.nasa.paginglibrary.ActivityUtils
import com.mutant.sample.nasa.paginglibrary.Injection.provideViewModelFactory
import com.mutant.sample.nasa.paginglibrary.R
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
            ActivityUtils.addFragment(supportFragmentManager, apodsFragment,
                    R.id.content_main, TAG_FRAGMENT_WORDS)
        }
        apodsFragment.setViewModel(viewModel)
    }

    companion object {
        private const val TAG_FRAGMENT_WORDS = "TAG_FRAGMENT_WORDS"
    }

}
