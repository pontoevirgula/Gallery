package com.chslcompany.gallery.framework.repository

import com.chslcompany.core.data.RemoteDataSource
import com.chslcompany.gallery.framework.network.response.DataWrapperResponse
import com.chslcompany.testing.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class PopularRepositoryImplTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var remoteDataSource: RemoteDataSource<DataWrapperResponse>

    lateinit var repositoryImpl: PopularRepositoryImpl

    @Before
    fun setup() {
        repositoryImpl = PopularRepositoryImpl(remoteDataSource)
    }

    @Test
    fun `should validate return from repository`() = runTest {
        val result = repositoryImpl.fetchPopular(40)
        assertNotNull(result)
    }


}