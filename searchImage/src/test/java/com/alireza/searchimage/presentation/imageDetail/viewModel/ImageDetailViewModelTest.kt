package com.alireza.searchimage.presentation.imageDetail.viewModel

import androidx.lifecycle.SavedStateHandle
import com.alireza.searchimage.data.fakeData.fakeImageEntityList
import com.alireza.searchimage.data.local.mapper.toImageDetail
import com.alireza.searchimage.domain.useCase.ImageDetailUseCase
import com.alireza.searchimage.presentation.imageDetail.navigation.imageIdArg
import com.alireza.searchimage.tools.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.never
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ImageDetailViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: ImageDetailViewModel
    @Mock private lateinit var savedStateHandle: SavedStateHandle
    @Mock private lateinit var imageDetailUseCase: ImageDetailUseCase

    @Test
    fun `test image loading`() = runTest {
        val expectedImageId = 1
        val expectedImageDetail = fakeImageEntityList[0].toImageDetail()
        whenever(savedStateHandle.get<String>(imageIdArg)).thenReturn("1")
        viewModel = ImageDetailViewModel(savedStateHandle,
            StandardTestDispatcher(), imageDetailUseCase)

        whenever(imageDetailUseCase.invoke(expectedImageId)).thenReturn(expectedImageDetail)

        val stateFlow = viewModel.imageDetailState

            val initialState = stateFlow.first()
            assertEquals(false, initialState.isLoading)

//            viewModel.loadImage(expectedImageId)

        advanceUntilIdle()

            val updatedState = stateFlow.first()
            assertEquals(expectedImageDetail, updatedState.image)
            assertEquals(false, updatedState.isLoading)

        verify(imageDetailUseCase, times(1)).invoke(expectedImageId)
    }

    @Test
    fun `test initial state with no image ID`() = runTest{
        whenever(savedStateHandle.get<String>(imageIdArg)).thenReturn(null)
        viewModel = ImageDetailViewModel(savedStateHandle,
            StandardTestDispatcher(), imageDetailUseCase)

        val stateFlow = viewModel.imageDetailState

        assertEquals(ImageDetailState(), stateFlow.first())
        verify(imageDetailUseCase, never()).invoke(1)
    }
}

