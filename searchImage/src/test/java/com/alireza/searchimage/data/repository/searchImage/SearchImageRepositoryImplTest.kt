package com.alireza.searchimage.data.repository.searchImage

import com.alireza.database.dataSource.image.ImageDataSource
import com.alireza.searchimage.data.fakeData.fakeImageEntityList
import com.alireza.searchimage.data.local.mapper.toImageDetail
import com.alireza.searchimage.data.remote.apiService.ApiService
import com.alireza.utility.network.NetworkConnection
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class SearchImageRepositoryImplTest {

    @Mock private lateinit var apiService: ApiService
    @Mock private lateinit var imageDataSource: ImageDataSource
    @Mock private lateinit var networkConnection: NetworkConnection

    private lateinit var searchImageRepository: SearchImageRepositoryImpl

    @Before
    fun setup() {
        searchImageRepository = SearchImageRepositoryImpl(apiService, imageDataSource, networkConnection)
    }


    @Test
    fun `imageDetails returns ImageDetail`() = runTest {
//        whenever(networkConnection.isInternetOn()).thenReturn(true)
        val imageId = 123
        val expectedImageDetail = fakeImageEntityList[0].toImageDetail()
        `when`(imageDataSource.findImageById(imageId)).thenReturn(fakeImageEntityList[0])

        val resultImageDetail = searchImageRepository.imageDetails(imageId)

        assertEquals(expectedImageDetail, resultImageDetail)
    }

    @Test(expected = Exception::class)
    fun `imageDetails throws exception if imageId is invalid`() = runTest {
//        whenever(networkConnection.isInternetOn()).thenReturn(true)
        val invalidImageId = -1
        `when`(imageDataSource.findImageById(invalidImageId)).thenThrow(Exception("Invalid image ID"))

        searchImageRepository.imageDetails(invalidImageId)
    }

}
