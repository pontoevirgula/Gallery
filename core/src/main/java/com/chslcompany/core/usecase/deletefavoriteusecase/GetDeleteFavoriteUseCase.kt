package com.chslcompany.core.usecase.deletefavoriteusecase

import com.chslcompany.core.data.dbrepository.GalleryRepository
import com.chslcompany.core.model.PhotoDomain
import com.chslcompany.core.usecase.base.CoroutinesDispatchers
import com.chslcompany.core.usecase.base.ResultStatus
import com.chslcompany.core.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface GetDeleteFavoriteUseCase {
    operator fun invoke(params: Params): Flow<ResultStatus<Unit>>
    data class Params(val photoDomain: PhotoDomain)
}

class GetDeleteFavoriteUseCaseImpl @Inject constructor(
    private val repository: GalleryRepository,
    private val dispatcher: CoroutinesDispatchers
) : UseCase<GetDeleteFavoriteUseCase.Params, Unit>(), GetDeleteFavoriteUseCase {

    override suspend fun doWork(params: GetDeleteFavoriteUseCase.Params): ResultStatus<Unit> {
        return withContext(dispatcher.io()) {
            repository.delete(params.photoDomain)
            ResultStatus.Success(Unit)
        }
    }


}