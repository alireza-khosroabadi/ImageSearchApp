package com.alireza.searchimage.presentation.searchImage.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.alireza.searchimage.domain.model.searchImage.Image
import com.alireza.searchimage.domain.useCase.SearchImageUseCase
import com.alireza.utility.dispatcher.IoDispatcher
import com.alireza.utility.dispatcher.MainDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchImageViewModel @Inject constructor(
    @IoDispatcher private val mainDispatcher: CoroutineDispatcher,
    private val searchImageUseCase: SearchImageUseCase) :
    ViewModel() {

    var selectedImageId = "-1"
        private set

    private val _searchQuery = mutableStateOf("fruit")
    val searchQuery = _searchQuery

    private val _searchedImages = MutableStateFlow<PagingData<Image>>(PagingData.from(emptyList()))
    val searchedImages = _searchedImages.asStateFlow()

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun searchImage() {
        viewModelScope.launch (mainDispatcher){
            searchImageUseCase.invoke(searchQuery = searchQuery.value)
                .cachedIn(viewModelScope)
                .collect {
                    _searchedImages.value = it
                }
        }
    }

    fun setSelectedImageId(imageId: String) {
        selectedImageId = imageId
    }

}
