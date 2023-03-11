package com.codinginflow.imagesearch

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(val service: UnsplashService)