package com.chslcompany.gallery.framework.di

import com.chslcompany.core.data.dbrepository.GalleryLocalDataSource
import com.chslcompany.core.data.dbrepository.GalleryRepository
import com.chslcompany.gallery.framework.db.repository.GalleryRepositoryImpl
import com.chslcompany.gallery.framework.local.GalleryLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface GalleryRepositoryModule {

    @Binds
    fun bindRepositoryGallery(repositoryImpl: GalleryRepositoryImpl) : GalleryRepository

    @Binds
    fun bindLocalDataSource(localDataSourceImpl: GalleryLocalDataSourceImpl) : GalleryLocalDataSource

}