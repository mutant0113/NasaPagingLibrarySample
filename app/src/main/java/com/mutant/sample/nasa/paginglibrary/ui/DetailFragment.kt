package com.mutant.sample.nasa.paginglibrary.ui

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.mutant.sample.nasa.paginglibrary.R
import com.mutant.sample.nasa.paginglibrary.model.Apod
import kotlinx.android.synthetic.main.fragment_detail.view.*

class DetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
//        val binding = DataBindingUtil.setContentView<ViewDataBinding>(activity, R.layout.fragment_detail)
//        binding.setVariable(BR.user, arguments.getParcelable<Apod>(ARGUMENT_APOD))
        val root = LayoutInflater.from(activity).inflate(R.layout.fragment_detail, container, false)
        setView(root, arguments.getParcelable(ARGUMENT_APOD))
        return root
    }

    private fun setView(root: View, apod: Apod) {
        Glide.with(this).load(apod.url).into(view.image_view_photo)
        root.text_view_date.text = apod.date
        root.text_view_title.text = apod.title
        root.text_view_explanation.text = apod.explanation
    }

    companion object {
        private const val ARGUMENT_APOD = "ARGUMENT_APOD"

        fun newInstance(apod: Apod): DetailFragment {
            val bundle = Bundle()
            bundle.putParcelable(ARGUMENT_APOD, apod)
            val fragment = DetailFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}