package com.alireza.searchimage.domain.useCase

import androidx.paging.PagingData
import com.alireza.searchimage.domain.model.searchImage.Image
import com.alireza.searchimage.domain.repository.SearchImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class SearchImageUseCaseTest {

    private val testScope = TestScope(StandardTestDispatcher())


    @Mock private lateinit var searchImageUseCase: SearchImageUseCase
    @Mock private lateinit var searchImageRepository: SearchImageRepository

    @Before
    fun setUp() {
        searchImageUseCase = SearchImageUseCase(searchImageRepository)
    }

    @Test
    fun `invoke use case with search query`()= testScope.runTest{
            val searchQuery = "test query"
            val pagingData: PagingData<Image> = PagingData.empty()
            val flow: Flow<PagingData<Image>> = flowOf(pagingData)

            whenever(searchImageRepository.searchImage(searchQuery)).thenReturn(flow)

            val resultFlow = searchImageUseCase(searchQuery)

            verify(searchImageRepository).searchImage(searchQuery)

            val resultList = resultFlow.toList()

            assertEquals(1, resultList.size)
            assertEquals(pagingData, resultList[0])
        }


    @Test
    fun `test multiple queries`() = testScope.runTest{
            val searchQueries = listOf("query1", "query2", "query3")
            val pagingData: PagingData<Image> = PagingData.empty()

            for (query in searchQueries) {
                whenever(searchImageRepository.searchImage(query)).thenReturn(flowOf(pagingData))
            }

            for (query in searchQueries) {
                searchImageUseCase(query)
                verify(searchImageRepository).searchImage(query)
            }
    }
}
