package com.chslcompany.gallery.framework.di

import com.chslcompany.core.data.PopularRepository
import com.chslcompany.core.data.RemoteDataSource
import com.chslcompany.gallery.framework.db.dao.WallPaperDao
import com.chslcompany.gallery.framework.db.database.WallPaperDatabase
import com.chslcompany.gallery.framework.network.response.DataWrapperResponse
import com.chslcompany.gallery.framework.remote.PopularRemoteDataSourceImpl
import com.chslcompany.gallery.framework.repository.PopularRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindPopularRepository(repositoryImpl: PopularRepositoryImpl): PopularRepository

    @Binds
    fun bindRemoteDataSource(remoteDataSourceImpl: PopularRemoteDataSourceImpl): RemoteDataSource<DataWrapperResponse>

}