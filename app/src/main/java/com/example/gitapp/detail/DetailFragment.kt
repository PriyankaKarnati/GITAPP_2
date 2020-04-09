package com.example.gitapp.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.gitapp.R
import com.example.gitapp.databinding.FragmentDetailBinding
import com.example.gitapp.detail.DetailViewModelFactory
import com.example.gitapp.network.GitProperty

class DetailFragment : Fragment() {
    lateinit var gitProperty: GitProperty


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        @Suppress("UNUSED_VARIABLE")
        val application = requireNotNull(activity).application
        val binding = FragmentDetailBinding.inflate(inflater)
        binding.setLifecycleOwner(this)

        val gitProperty = DetailFragmentArgs.fromBundle(arguments!!).selectedProperty


        val detailViewModelFactory = DetailViewModelFactory(gitProperty, application)

        binding.detailViewModel =
            ViewModelProviders.of(this, detailViewModelFactory).get(DetailViewModel::class.java)

        return binding.root
            //integrated detail page to overviewPage
    }
}