package com.chslcompany.gallery.ui.fragment.popular.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.chslcompany.core.model.PhotoDomain
import com.chslcompany.core.usecase.popularusecase.GetPopularUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(
    private val getPopularUseCase: GetPopularUseCase
) : ViewModel() {

    fun popularWallPapers() : Flow<PagingData<PhotoDomain>>  {
        return getPopularUseCase(
            GetPopularUseCase.GetPopularParams(getPagingConfig())
        ).cachedIn(viewModelScope)
    }

    private fun getPagingConfig() = PagingConfig(PAGING_SIZE)

    companion object {
        private const val PAGING_SIZE = 40
    }
}