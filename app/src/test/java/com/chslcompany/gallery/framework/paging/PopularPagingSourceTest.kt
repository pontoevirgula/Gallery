package com.chslcompany.gallery.framework.paging

import androidx.paging.PagingSource
import com.chslcompany.core.data.RemoteDataSource
import com.chslcompany.core.model.PhotoDomain
import com.chslcompany.gallery.factory.DataWrapperResponseFactory
import com.chslcompany.gallery.framework.network.response.DataWrapperResponse
import com.chslcompany.testing.MainCoroutineRule
import com.chslcompany.testing.model.WallpapersFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class PopularPagingSourceTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var remoteDataSource: RemoteDataSource<DataWrapperResponse>

    private lateinit var popularPagingSource: PopularPagingSource

    private val dataWrapperResponseFactory = DataWrapperResponseFactory()

    private val wallpapersFactory =
        WallpapersFactory().create(WallpapersFactory.Photo.PhotoDomainSuccess)

    @Before
    fun setup() {
        popularPagingSource = PopularPagingSource(remoteDataSource, 40)
    }

    @Test
    fun `should validate paging data success when invoke from repository is called`() = runTest {
        whenever(remoteDataSource.fetchPopular(any(), any()))
            .thenReturn(dataWrapperResponseFactory.create())

        val result = popularPagingSource.load(
            PagingSource.LoadParams.Refresh(
                null,
                loadSize = 40,
                false
            )
        )

        val expected = listOf(wallpapersFactory)

        assertEquals(
            PagingSource.LoadResult.Page(
                data = expected,
                prevKey = null,
                nextKey = 2
            ),
            result
        )
    }

    @Test
    fun `should validate paging data error when invoke from repository is called`() = runTest {
        val exception = RuntimeException()

        whenever(remoteDataSource.fetchPopular(any(), any()))
            .thenThrow(exception)

        val result = popularPagingSource.load(
            PagingSource.LoadParams.Refresh(
                null,
                loadSize = 40,
                false
            )
        )

        assertEquals(
            PagingSource.LoadResult.Error<Int, PhotoDomain>(exception),
            result
        )

    }


}