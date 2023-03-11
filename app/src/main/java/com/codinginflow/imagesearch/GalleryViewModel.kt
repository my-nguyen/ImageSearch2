package com.codinginflow.imagesearch

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(val repository: Repository) : ViewModel()