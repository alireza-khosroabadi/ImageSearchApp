package com.alireza.searchimage.presentation.searchImage.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import app.cash.turbine.test
import com.alireza.searchimage.domain.model.searchImage.Image
import com.alireza.searchimage.domain.repository.SearchImageRepository
import com.alireza.searchimage.domain.useCase.SearchImageUseCase
import com.alireza.searchimage.tools.MainDispatcherRule
import com.alireza.searchimage.tools.collectDataForTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class SearchImageViewModelTest{

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: SearchImageViewModel
    private lateinit var searchImageUseCase: SearchImageUseCase
    private val searchImageRepository: SearchImageRepository = mock()


    @Before
    fun setUp() {
        searchImageUseCase = SearchImageUseCase(searchImageRepository)
        viewModel = SearchImageViewModel(StandardTestDispatcher(),searchImageUseCase)
    }

    @Test
    fun `test updateSearchQuery`() {
        val query = "test query"
        viewModel.updateSearchQuery(query)
        assertEquals(query, viewModel.searchQuery.value)
    }

    @Test
    fun `test searchImage with non-empty result`() = runTest {
        val query = "test query"
        val pagingData: PagingData<Image> = PagingData.from(listOf(Image("1", 1,"","","",1)))
        whenever(searchImageRepository.searchImage(query)) doReturn flowOf(pagingData)

        viewModel.updateSearchQuery(query)
        viewModel.searchImage()
        advanceUntilIdle()

        viewModel.searchedImages.test {

            val result = awaitItem()

            assertEquals(pagingData.collectDataForTest(testScheduler).size ,
                result.collectDataForTest(testScheduler).size)

            cancelAndIgnoreRemainingEvents()
        }

        verify(searchImageRepository).searchImage(query)
    }


    @Test
    fun `test searchImage with empty result`() = runTest {
        val query = "test query"
        val emptyPagingData: PagingData<Image> = PagingData.empty()
        whenever(searchImageRepository.searchImage(query)).thenReturn(flowOf(emptyPagingData))

        viewModel.updateSearchQuery(query)
        viewModel.searchImage()
        advanceUntilIdle()

        viewModel.searchedImages.test {
            val result = awaitItem()

            assertTrue(result.collectDataForTest(testScheduler).isEmpty())

            cancelAndIgnoreRemainingEvents()
        }
        verify(searchImageRepository).searchImage(query)
    }

    @Test(expected = RuntimeException::class)
    fun `test searchImage with error`() = runTest {
        val query = "test query"
        val errorMessage = "Error fetching data"
        val exception = RuntimeException(errorMessage)
        whenever(searchImageRepository.searchImage(query)).thenThrow(exception)

        viewModel.updateSearchQuery(query)
        viewModel.searchImage()
        advanceUntilIdle()

        viewModel.searchedImages.test {
            val result = awaitItem()

            assertEquals(PagingData.empty<Image>().collectDataForTest(testScheduler).size, result.collectDataForTest(testScheduler).size)
            cancelAndIgnoreRemainingEvents()
        }

        verify(searchImageRepository).searchImage(query)
    }

    @Test
    fun `test setSelectedImageId`() {
        val imageId = "10"
        viewModel.setSelectedImageId(imageId)
        assertEquals(imageId, viewModel.selectedImageId)
    }
}
