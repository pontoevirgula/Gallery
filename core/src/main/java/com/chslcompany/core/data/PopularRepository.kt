package com.chslcompany.core.data

import androidx.paging.PagingSource
import com.chslcompany.core.model.PhotoDomain

interface PopularRepository {
    fun fetchPopular(pages : Int) : PagingSource<Int, PhotoDomain>
}