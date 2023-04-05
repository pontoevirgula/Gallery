package com.chslcompany.gallery.ui.fragment.gallery

import androidx.lifecycle.ViewModel
import com.chslcompany.core.usecase.base.CoroutinesDispatchers
import com.chslcompany.core.usecase.deletefavoriteusecase.GetDeleteFavoriteUseCase
import com.chslcompany.core.usecase.galleryusecase.GetGalleryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val getGalleryUseCase: GetGalleryUseCase,
    private val getDeleteFavoriteUseCase: GetDeleteFavoriteUseCase,
    private val coroutinesDispatchers: CoroutinesDispatchers
) : ViewModel()

