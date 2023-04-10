package com.chslcompany.gallery.ui.fragment.gallery

import androidx.lifecycle.*
import com.chslcompany.core.model.PhotoDomain
import com.chslcompany.core.usecase.base.CoroutinesDispatchers
import com.chslcompany.core.usecase.deletefavoriteusecase.GetDeleteFavoriteUseCase
import com.chslcompany.core.usecase.galleryusecase.GetGalleryUseCase
import com.chslcompany.gallery.ui.extensions.watchStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val getGalleryUseCase: GetGalleryUseCase,
    private val getDeleteFavoriteUseCase: GetDeleteFavoriteUseCase,
    private val coroutinesDispatchers: CoroutinesDispatchers
) : ViewModel() {

    private val action = MutableLiveData<Action>()

    init {
        getGallery()
    }

    val state : LiveData<UiState> = action.switchMap { action ->
        liveData(coroutinesDispatchers.main()){
            when(action) {
                is Action.GetGallery -> {
                    getPhoto()
                }
                is Action.DeleteFavorite -> {
                    deletePhoto(action)
                }
            }
        }
    }

    fun deleteGallery(photoDomain: PhotoDomain) {
        action.value = Action.DeleteFavorite(photoDomain)
    }

    private suspend fun LiveDataScope<UiState>.getPhoto() {
        getGalleryUseCase().catch {
            emit(UiState.ErrorGallery)
        }.collect{
            val uiState = if (it.isEmpty()) UiState.EmptyGallery else UiState.ShowGallery(it)
            emit(uiState)
        }
    }

    private suspend fun LiveDataScope<UiState>.deletePhoto(action : Action.DeleteFavorite) {
        getDeleteFavoriteUseCase(
            GetDeleteFavoriteUseCase.Params(action.photoDomain)
        ).watchStatus(
            loading = {},
            success = {
               getGallery()
            },
            error = {
                emit(UiState.ErrorGallery)
            }
        )
    }

    private fun getGallery() {
        action.value = Action.GetGallery
    }

    sealed class Action {
        object GetGallery : Action()
        data class DeleteFavorite(val photoDomain: PhotoDomain) : Action()
    }

    sealed class UiState {
        data class ShowGallery(val photos : List<PhotoDomain>) : UiState()
        object EmptyGallery : UiState()
        object ErrorGallery : UiState()
    }
}

