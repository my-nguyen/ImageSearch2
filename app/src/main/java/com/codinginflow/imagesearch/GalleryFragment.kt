package com.codinginflow.imagesearch

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.codinginflow.imagesearch.databinding.FragmentGalleryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryFragment: Fragment(R.layout.fragment_gallery) {
    private val viewModel by viewModels<GalleryViewModel>()
    private lateinit var binding: FragmentGalleryBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentGalleryBinding.bind(view)
        val adapter = PhotosAdapter()
        binding.apply {
            recycler.setHasFixedSize(true)
            recycler.adapter = adapter.withLoadStateHeaderAndFooter(
                header = LoadAdapter { adapter.retry() },
                footer = LoadAdapter { adapter.retry() }
            )
        }

        viewModel.photos.observe(viewLifecycleOwner) { paging ->
            adapter.submitData(viewLifecycleOwner.lifecycle, paging)
        }

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_gallery, menu)
        val searchView = menu.findItem(R.id.menu_search).actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    binding.recycler.scrollToPosition(0)
                    viewModel.searchPhotos(query)
                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(p0: String?) = true
        })
    }
}