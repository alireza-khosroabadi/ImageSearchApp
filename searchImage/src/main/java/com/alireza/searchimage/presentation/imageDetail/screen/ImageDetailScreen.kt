package com.alireza.searchimage.presentation.imageDetail.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alireza.searchimage.domain.model.imageDetail.ImageDetail
import com.alireza.searchimage.presentation.imageDetail.viewModel.ImageDetailViewModel

@Composable
fun ImageDetailScreen(viewModel: ImageDetailViewModel = hiltViewModel(), onBackClick: () -> Unit) {
    val state by viewModel.imageDetailState.collectAsStateWithLifecycle()

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(top = 16.dp)) {
        state.image?.let {
            ImageDetailTopBar(it.user, onBackClick)
            Content(it) { onBackClick() }
        }
    }

}

@Composable
fun Content(image: ImageDetail, onBackClick: () -> Unit) {
    val configuration = LocalConfiguration.current
    if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
        PortraitView(image)
    else
        LandscapeView(image)

}
