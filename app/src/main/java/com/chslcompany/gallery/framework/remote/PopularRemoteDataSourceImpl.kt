package com.chslcompany.gallery.framework.remote

import com.chslcompany.core.data.RemoteDataSource
import com.chslcompany.gallery.framework.network.api.WallpapersApi
import com.chslcompany.gallery.framework.network.response.DataWrapperResponse
import javax.inject.Inject

class PopularRemoteDataSourceImpl @Inject constructor(
    private val api: WallpapersApi
) : RemoteDataSource<DataWrapperResponse> {

    override suspend fun fetchPopular(page: Int, perPage: Int): DataWrapperResponse =
        api.getPopularWallpapers(page,perPage)
}