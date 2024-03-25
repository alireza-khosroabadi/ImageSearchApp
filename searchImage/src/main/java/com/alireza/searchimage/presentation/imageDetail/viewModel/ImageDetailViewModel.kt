package com.alireza.searchimage.presentation.imageDetail.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alireza.searchimage.domain.useCase.ImageDetailUseCase
import com.alireza.searchimage.presentation.imageDetail.navigation.imageIdArg
import com.alireza.utility.dispatcher.IoDispatcher
import com.alireza.utility.dispatcher.MainDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jetbrains.annotations.VisibleForTesting
import javax.inject.Inject

@HiltViewModel
class ImageDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    @IoDispatcher private val mainDispatcher: CoroutineDispatcher,
    private val imageDetailUseCase: ImageDetailUseCase
    , ):ViewModel() {

    private val imageId = savedStateHandle.get<String>(imageIdArg)?.toInt()

    private val _imageDetailState = MutableStateFlow(ImageDetailState())
    val imageDetailState = _imageDetailState.asStateFlow()

    init {
        imageId?.let { loadImage(it) }
    }


    private fun loadImage(imageId: Int) {
        viewModelScope.launch(mainDispatcher) {
            // Loading state
            _imageDetailState.update { imageState ->
                imageState.copy(
                    isLoading = true
                )
            }

            // Getting image
            val result = imageDetailUseCase.invoke(imageId)
                _imageDetailState.update { imageState ->
                    imageState.copy(
                        image = result,
                        isLoading = false
                    )
            }
        }
    }
}
