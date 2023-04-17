package com.chslcompany.core.usecase.galleryusecase

import com.chslcompany.core.data.dbrepository.GalleryRepository
import com.chslcompany.core.model.PhotoDomain
import com.chslcompany.core.usecase.base.FlowUseCase
import com.chslcompany.core.usecase.base.CoroutinesDispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface GetGalleryUseCase {
    suspend operator fun invoke(params: Unit = Unit): Flow<List<PhotoDomain>>
}

class GetGalleyUseCaseImpl @Inject constructor(
    private val repository: GalleryRepository,
    private val dispatcher: CoroutinesDispatchers
) : FlowUseCase<Unit, List<PhotoDomain>>(), GetGalleryUseCase {

    override suspend fun createFlowObservable(params: Unit): Flow<List<PhotoDomain>> =
        withContext(dispatcher.io()) {
            repository.get()
        }

}