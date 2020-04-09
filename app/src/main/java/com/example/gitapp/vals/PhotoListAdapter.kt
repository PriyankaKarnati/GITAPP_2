package com.example.gitapp.vals

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gitapp.R
import com.example.gitapp.bindImage
import com.example.gitapp.databinding.ListviewItemBinding
import com.example.gitapp.network.GitProperty
import kotlinx.android.synthetic.main.listview_item.view.*


class PhotoListAdapter(val onClickListener: OnClickListener) :
    ListAdapter<GitProperty, PhotoListAdapter.GitItemViewHolder>(DiffCallBack) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitItemViewHolder {
//        val view =
//            LayoutInflater.from(parent.context).inflate(R.layout.listview_item, parent, false)
//        return GitItemViewHolder(view)
        return GitItemViewHolder(ListviewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: GitItemViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
            holder.itemView.setOnClickListener {
                onClickListener.onClick(item)
            }
        }
    }

    class GitItemViewHolder(private var binding:
                           ListviewItemBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(gitProperty: GitProperty) {
            binding.property = gitProperty
            binding.executePendingBindings()
        }

    }

    companion object DiffCallBack : DiffUtil.ItemCallback<GitProperty>() {
        override fun areItemsTheSame(oldItem: GitProperty, newItem: GitProperty): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: GitProperty, newItem: GitProperty): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class OnClickListener(val clickListener: (gitProperty: GitProperty) -> Unit) {
        fun onClick(gitProperty: GitProperty) = clickListener(gitProperty)
    }
}








