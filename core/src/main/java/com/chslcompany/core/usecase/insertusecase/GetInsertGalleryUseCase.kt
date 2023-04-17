package com.chslcompany.core.usecase.insertusecase

import com.chslcompany.core.data.dbrepository.GalleryRepository
import com.chslcompany.core.model.PhotoDomain
import com.chslcompany.core.usecase.base.UseCase
import com.chslcompany.core.usecase.base.CoroutinesDispatchers
import com.chslcompany.core.usecase.base.ResultStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface GetInsertGalleryUseCase {
    operator fun invoke(params: Params): Flow<ResultStatus<Unit>>
    data class Params(val photoDomain: PhotoDomain)
}

class GetInsertGalleryUseCaseImpl @Inject constructor(
    private val repository: GalleryRepository,
    private val dispatcher: CoroutinesDispatchers
) : UseCase<GetInsertGalleryUseCase.Params, Unit>(), GetInsertGalleryUseCase {

    override suspend fun doWork(params: GetInsertGalleryUseCase.Params): ResultStatus<Unit> {
        return withContext(dispatcher.io()) {
            repository.insert(params.photoDomain)
            ResultStatus.Success(Unit)
        }
    }


}