package com.chslcompany.gallery.ui.fragment.popular.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.chslcompany.core.model.PhotoDomain
import com.chslcompany.core.usecase.insertusecase.GetInsertGalleryUseCase
import com.chslcompany.core.usecase.popularusecase.GetPopularUseCase
import com.chslcompany.gallery.ui.extensions.watchStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(
    private val getPopularUseCase: GetPopularUseCase,
    private val getInsertGalleryUseCase: GetInsertGalleryUseCase
) : ViewModel() {

    private var _favoriteUiState = MutableLiveData<FavoriteUiStatus>()
    val favoriteUiState : LiveData<FavoriteUiStatus> get() = _favoriteUiState

    fun popularWallPapers(): Flow<PagingData<PhotoDomain>> {
        return getPopularUseCase(
            GetPopularUseCase.GetPopularParams(getPagingConfig())
        ).cachedIn(viewModelScope)
    }

    fun favoritePhoto(photoDomain: PhotoDomain) = viewModelScope.launch {
        getInsertGalleryUseCase.invoke(GetInsertGalleryUseCase.Params(photoDomain))
            .watchStatus(
                loading = { _favoriteUiState.value = FavoriteUiStatus.Loading },
                success = { _favoriteUiState.value = FavoriteUiStatus.FavoritePhoto(true) },
                error = { _favoriteUiState.value = FavoriteUiStatus.FavoritePhoto(false) }
            )
    }

    sealed class FavoriteUiStatus{
        object Loading : FavoriteUiStatus()
        class FavoritePhoto(val saved : Boolean) : FavoriteUiStatus()
    }

    private fun getPagingConfig() = PagingConfig(PAGING_SIZE)

    companion object {
        private const val PAGING_SIZE = 40
    }
}