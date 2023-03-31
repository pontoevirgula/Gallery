package com.chslcompany.gallery.framework.repository

import androidx.paging.PagingSource
import com.chslcompany.core.data.PopularRepository
import com.chslcompany.core.data.RemoteDataSource
import com.chslcompany.core.model.PhotoDomain
import com.chslcompany.gallery.framework.network.response.DataWrapperResponse
import com.chslcompany.gallery.framework.paging.PopularPagingSource
import javax.inject.Inject

class PopularRepositoryImpl @Inject constructor(
    //private val dao: WallPaperDao,
    private val remoteDataSource: RemoteDataSource<DataWrapperResponse>
) : PopularRepository {

    override fun fetchPopular(pages: Int): PagingSource<Int, PhotoDomain> =
        PopularPagingSource(remoteDataSource, pages)

//    override suspend fun listPhotos(params : PhotoDomain): Flow<ResultStatus<List<PhotoDomain>>> =
//        readFromDatabase(dao)
//
//
//    private suspend fun readFromDatabase(dao: WallPaperDao): Flow<ResultStatus<List<PhotoDomain>>> =
//        flow {
//            dao.getAllPhotos().map {
//                it.sortedByDescending { photoEntity ->
//                    photoEntity.id
//                }.toPhotoDb()
//            }
//        }
//
//
//    override suspend fun removePhotoFromDatabase(photo: PhotoDomain): Flow<ResultStatus<PhotoDomain>> =
//        flow {
//            dao.deleteById(photo.id)
//        }
//
//
//    override suspend fun savePhoto(photoDomain: PhotoDomain): Flow<ResultStatus<Nothing>> =
//        flow {
//            val photoEntity = PhotoEntity(
//                photo = photoDomain.photo,
//                id = photoDomain.id
//            )
//            dao.insert(photoEntity)
//        }

}

