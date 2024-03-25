package com.alireza.searchimage.data.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.alireza.database.dataSource.image.ImageDataSource
import com.alireza.database.model.image.ImageEntity
import com.alireza.searchimage.data.remote.apiService.ApiService
import com.alireza.searchimage.data.remote.model.image.ImageListDto
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.whenever
import retrofit2.HttpException
import retrofit2.Response

@OptIn(ExperimentalPagingApi::class)
@RunWith(MockitoJUnitRunner::class)
class SearchImageMediatorTest {


    private val testScope = TestScope(StandardTestDispatcher())

    @Mock private lateinit var dataSource: ImageDataSource
    @Mock private lateinit var apiService: ApiService
    @Mock private lateinit var pagingState: PagingState<Int,ImageEntity>

    private lateinit var mediator: SearchImageMediator


    @Before
    fun setUp() {
        mediator = SearchImageMediator("Search Query",dataSource, apiService)

        whenever(pagingState.config) doReturn PagingConfig(20)
    }

    @Test
    fun `test load success`() = testScope.runTest {

        whenever(dataSource.insertImages(any(), any())).thenReturn(Unit)
        whenever(apiService.searchImage(any(), any(), any(), any())).thenReturn(
            ImageListDto(emptyList(),0,0))

        val result = mediator.load(LoadType.REFRESH, pagingState)

        assertTrue(result is RemoteMediator.MediatorResult.Success)

    }


    @Test
    fun `test load http exception`() = testScope.runTest {
            val errorBody = "{}".toResponseBody("application/json".toMediaTypeOrNull())
            val response = Response.error<PagingSource.LoadResult.Page<Int, ImageEntity>>(400, errorBody)
            whenever(apiService.searchImage(any(), any(), any(), any())).thenThrow(HttpException(response))

            val result = mediator.load(LoadType.REFRESH, pagingState)

            assertTrue(result is RemoteMediator.MediatorResult.Error)
        }


    @Test
    fun `test load prepend end of pagination`() = testScope.runTest {
            val result = mediator.load(LoadType.PREPEND, mock())

            assertTrue(result is RemoteMediator.MediatorResult.Success)
            assertTrue((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)
        }

    @Test
    fun `test load append success`() = testScope.runTest {
            val lastItem = ImageEntity(0,1,"","",1,"","",2,3,4)
            whenever(pagingState.lastItemOrNull()).thenReturn(lastItem)

            val response = ImageListDto(listOf(), 1 ,200)
            whenever(apiService.searchImage(any(), any(), any(), any())).thenReturn(response)

            val result = mediator.load(LoadType.APPEND, pagingState)

            assertTrue(result is RemoteMediator.MediatorResult.Success)
            assertEquals( true, (result as RemoteMediator.MediatorResult.Success).endOfPaginationReached )
        }


    @Test
    fun `test load append end of pagination`() = testScope.runTest {
            whenever(pagingState.lastItemOrNull()).thenReturn(null)

            val result = mediator.load(LoadType.APPEND, pagingState)

            assertTrue(result is RemoteMediator.MediatorResult.Success)
            assertEquals(true, (result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)
        }

}
