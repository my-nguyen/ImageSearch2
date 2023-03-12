package com.codinginflow.imagesearch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.codinginflow.imagesearch.databinding.ItemUnsplashPhotoBinding

private val COMPARATOR = object : DiffUtil.ItemCallback<UnsplashData.Result>() {
    override fun areItemsTheSame(oldItem: UnsplashData.Result, newItem: UnsplashData.Result) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: UnsplashData.Result, newItem: UnsplashData.Result) = oldItem == newItem
}

class PhotosAdapter(val listener: OnClickListener): PagingDataAdapter<UnsplashData.Result, PhotosAdapter.ViewHolder>(COMPARATOR) {
    interface OnClickListener {
        fun onClick(photo: UnsplashData.Result)
    }

    inner class ViewHolder(val binding: ItemUnsplashPhotoBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                listener.onClick(getItem(bindingAdapterPosition)!!)
            }
        }

        fun bind(photo: UnsplashData.Result) {
            binding.apply {
                Glide.with(itemView)
                    .load(photo.urls.regular)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .into(image)
                username.text = photo.user.username
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemUnsplashPhotoBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }
}