package com.chslcompany.core.usecase.popularusecase

import androidx.paging.PagingConfig
import com.chslcompany.core.data.PopularRepository
import com.chslcompany.testing.MainCoroutineRule
import com.chslcompany.testing.model.WallpapersFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetPopularUseCaseImplTest{

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private val repository = mock<PopularRepository>()

    lateinit var useCase: GetPopularUseCaseImpl

    private val wallpapersFactory = WallpapersFactory().create(WallpapersFactory.Photo.PhotoDomainSuccess)
    private val pagingSource = PagingSourceFactory().create(listOf(wallpapersFactory))

    @Before
    fun setup() {
        useCase = GetPopularUseCaseImpl(repository)
    }

    @Test
    fun `should validate flow paging data creation when invoke from use case is called`() = runTest {
        whenever(repository.fetchPopular(40)).thenReturn((pagingSource))

        val results = useCase(GetPopularUseCase.GetPopularParams(PagingConfig(40)))

        verify(repository).fetchPopular(40)

        assertNotNull(results.first())
    }




}