package com.example.gitapp.vals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.gitapp.databinding.FragmentOverviewBinding
import com.example.gitapp.databinding.ListviewItemBinding

class OverviewFragment : Fragment() {
    private val viewModel: OverviewViewModel by lazy {
        ViewModelProviders.of(this).get(OverviewViewModel::class.java)
    }
    private lateinit var photoListAdapter:PhotoListAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentOverviewBinding.inflate(inflater)
        //val binding = ListviewItemBinding.inflate(inflater)

        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel
        photoListAdapter = PhotoListAdapter(PhotoListAdapter.OnClickListener {
            viewModel.displaySelectedProperties(it)
        })
        binding.propertyList.adapter = photoListAdapter

        viewModel.navigateToSelected.observe(this, Observer {
            if(null!=it){
                this.findNavController().navigate(OverviewFragmentDirections.actionOverviewToDetail(it))
                viewModel.displayCompleted()
            }

        })
        return binding.root
    }

}