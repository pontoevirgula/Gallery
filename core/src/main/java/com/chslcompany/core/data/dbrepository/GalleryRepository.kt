package com.chslcompany.core.data.dbrepository

import com.chslcompany.core.model.PhotoDomain
import kotlinx.coroutines.flow.Flow

interface GalleryRepository {

    suspend fun get() : Flow<List<PhotoDomain>>
    suspend fun insert(photoDomain: PhotoDomain)
    suspend fun delete(photoDomain: PhotoDomain)
}