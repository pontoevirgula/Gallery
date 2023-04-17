package com.chslcompany.gallery.framework.db.repository

import com.chslcompany.core.data.dbrepository.GalleryLocalDataSource
import com.chslcompany.core.data.dbrepository.GalleryRepository
import com.chslcompany.core.model.PhotoDomain
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GalleryRepositoryImpl @Inject constructor(
    private val dataSource: GalleryLocalDataSource
) : GalleryRepository {

    override suspend fun get(): Flow<List<PhotoDomain>> = dataSource.get()

    override suspend fun insert(photoDomain: PhotoDomain) = dataSource.insert(photoDomain)

    override suspend fun delete(photoDomain: PhotoDomain) = dataSource.delete(photoDomain)
}