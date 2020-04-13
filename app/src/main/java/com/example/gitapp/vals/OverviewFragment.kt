package com.example.gitapp.vals

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gitapp.databinding.FragmentOverviewBinding

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

        binding.propertyList.addOnScrollListener(object:RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val totalItemCount = recyclerView.layoutManager!!.itemCount
                val layoutManager =
                    LinearLayoutManager::class.java.cast(recyclerView.layoutManager)
                val lastItemPosition = layoutManager!!.findLastCompletelyVisibleItemPosition()
                Log.i("ho","started")
                if(totalItemCount==lastItemPosition+2){
                    Log.i("ho","poalkdadhl")
                    viewModel.getGitApiResponse(10)
                }
            }

        })
        viewModel.navigateToSelected.observe(this, Observer {
            if(null!=it){
                this.findNavController().navigate(OverviewFragmentDirections.actionOverviewToDetail(it))
                viewModel.displayCompleted()
            }

        })
        //viewModel.sendToDetails()
        return binding.root
    }


}