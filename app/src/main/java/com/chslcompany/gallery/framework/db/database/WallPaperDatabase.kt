package com.chslcompany.gallery.framework.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chslcompany.gallery.framework.db.dao.WallPaperDao
import com.chslcompany.gallery.framework.db.entity.PhotoEntity

@Database(entities = [PhotoEntity::class], version = 1, exportSchema = false)
abstract class WallPaperDatabase : RoomDatabase() {

    abstract fun wallPaperDao(): WallPaperDao
}