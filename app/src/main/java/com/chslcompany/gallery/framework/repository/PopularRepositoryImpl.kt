package com.chslcompany.gallery.framework.repository

import androidx.paging.PagingSource
import com.chslcompany.core.data.PopularRepository
import com.chslcompany.core.data.RemoteDataSource
import com.chslcompany.core.model.PhotoDomain
import com.chslcompany.gallery.framework.network.response.DataWrapperResponse
import com.chslcompany.gallery.framework.paging.PopularPagingSource
import javax.inject.Inject

class PopularRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource<DataWrapperResponse>
) : PopularRepository {

    override fun fetchPopular(pages: Int): PagingSource<Int, PhotoDomain> =
        PopularPagingSource(remoteDataSource, pages)

}

