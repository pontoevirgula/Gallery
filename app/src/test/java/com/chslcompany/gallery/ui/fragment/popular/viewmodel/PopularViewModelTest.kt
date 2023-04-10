package com.chslcompany.gallery.ui.fragment.popular.viewmodel

import androidx.paging.PagingData
import com.chslcompany.core.usecase.insertusecase.GetInsertGalleryUseCase
import com.chslcompany.core.usecase.popularusecase.GetPopularUseCase
import com.chslcompany.testing.MainCoroutineRule
import com.chslcompany.testing.model.WallpapersFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class PopularViewModelTest {

    @get:Rule
    var mainCoroutineDispatcher = MainCoroutineRule()

    @Mock
    lateinit var popularUseCase: GetPopularUseCase

    @Mock
    lateinit var insertGalleryUseCase: GetInsertGalleryUseCase

    private lateinit var viewModel: PopularViewModel

    @Before
    fun setup() {
        viewModel = PopularViewModel(popularUseCase, insertGalleryUseCase)
    }

    @Test
    fun `Should validate pagination data`() = runTest {
        whenever(popularUseCase(any())).thenReturn(flowOf(getPagingDataMock))
        val result = viewModel.popularWallPapers()
        assertNotNull(result.first())
    }

    @Test(expected = RuntimeException::class)
    fun `Should return an empty PagingData When an error occurred`() = runTest {
        //Arrange
        whenever(popularUseCase(any())).thenThrow(RuntimeException())
        //Act
        viewModel.popularWallPapers()
    }

    private val wallpapersFactory = WallpapersFactory()

    private val getPagingDataMock =
        PagingData.from(
            listOf(
                wallpapersFactory.create(
                    WallpapersFactory.Photo.PhotoDomainSuccess
                )
            )
        )


}