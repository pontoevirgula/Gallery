package com.chslcompany.gallery.framework.di

import android.content.Context
import androidx.room.Room
import com.chslcompany.core.data.DbConstants
import com.chslcompany.gallery.framework.db.database.WallPaperDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    fun provideWallpaperDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            WallPaperDatabase::class.java,
            DbConstants.APP_DATABASE_NAME
        ).build()

    @Provides
    fun provideWallpaperDao(db : WallPaperDatabase) = db.wallPaperDao()

}