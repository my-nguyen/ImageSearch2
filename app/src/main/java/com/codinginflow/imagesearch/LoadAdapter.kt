package com.codinginflow.imagesearch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.codinginflow.imagesearch.databinding.LoadFooterBinding

class LoadAdapter(val callback: () -> Unit) : LoadStateAdapter<LoadAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: LoadFooterBinding) : RecyclerView.ViewHolder(binding.root) {
        // RecyclerView only creates a few ViewHolder's for the whole list of items.
        // set setOnClickListener in init() instead of in bind() to avoid calling it for each item
        // in the whole list.
        init {
            binding.retry.setOnClickListener {
                callback.invoke()
            }
        }

        fun bind(loadState: LoadState) {
            binding.apply {
                progress.isVisible = loadState is LoadState.Loading
                error.isVisible = loadState !is LoadState.Loading
                retry.isVisible = loadState !is LoadState.Loading
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LoadFooterBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }
}
