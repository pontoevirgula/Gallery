package com.chslcompany.gallery.framework.local

import com.chslcompany.core.data.dbrepository.GalleryLocalDataSource
import com.chslcompany.core.model.PhotoDomain
import com.chslcompany.gallery.framework.db.dao.WallPaperDao
import com.chslcompany.gallery.framework.db.entity.PhotoEntity
import com.chslcompany.gallery.framework.db.entity.toPhotoDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class GalleryLocalDataSourceImpl @Inject constructor(
    private val dao: WallPaperDao
) : GalleryLocalDataSource {

    override suspend fun get(): Flow<List<PhotoDomain>> = dao.getAllPhotos().map {
        it.toPhotoDomain()
    }

    override suspend fun insert(photoDomain: PhotoDomain) =
        dao.insert(photoDomain.toEntity())

    override suspend fun delete(photoDomain: PhotoDomain) =
        dao.deleteWallpaperById(photoDomain.toEntity())


    private fun PhotoDomain.toEntity() = PhotoEntity(
        id = this.id ?: 0,
        photo = this.srcDomain?.original ?: "",
        smallPhoto = this.srcDomain?.small ?: "",
        photographer = this.photographer ?: "",
        avgColor = this.avgColor ?: ""
    )
}