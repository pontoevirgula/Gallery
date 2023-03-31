package com.chslcompany.core.data

import androidx.paging.PagingSource
import com.chslcompany.core.model.PhotoDomain

interface PopularRepository {
    fun fetchPopular(pages : Int) : PagingSource<Int, PhotoDomain>
//    suspend fun listPhotos(params : PhotoDomain) : Flow<ResultStatus<List<PhotoDomain>>>
//    suspend fun removePhotoFromDatabase(photo: PhotoDomain): Flow<ResultStatus<PhotoDomain>>
//    suspend fun savePhoto(photoDomain: PhotoDomain): Flow<ResultStatus<Nothing>>
}