package com.chslcompany.gallery.framework.db.dao

import androidx.room.*
import com.chslcompany.core.data.DbConstants
import com.chslcompany.gallery.framework.db.entity.PhotoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WallPaperDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: PhotoEntity)

    @Query("SELECT * FROM ${DbConstants.APP_TABLE_NAME}")
    fun getAllPhotos() : Flow<List<PhotoEntity>>

    @Delete
    suspend fun deleteWallpaperById(entity: PhotoEntity)

    @Query("SELECT * FROM ${DbConstants.APP_TABLE_NAME} ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomWallpaper() : PhotoEntity

}