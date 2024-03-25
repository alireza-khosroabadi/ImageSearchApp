package com.alireza.searchimage.presentation.searchImage.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.alireza.searchimage.R
import com.alireza.searchimage.presentation.searchImage.viewModel.SearchImageViewModel
import com.alireza.systemdesign.ui.dialog.MyAlertDialog
import com.alireza.systemdesign.ui.searchField.SearchBar
import com.alireza.systemdesign.ui.topBarHolder.TopBarHolder

@Composable
fun SearchImageScreen(
    viewModel: SearchImageViewModel = hiltViewModel(),
    navigateToImageDetail: (String) -> Unit
) {
    val searchQuery by viewModel.searchQuery
    val searchedImages = viewModel.searchedImages.collectAsLazyPagingItems()
    val context = LocalContext.current
    var openAlertDialog by remember { mutableStateOf(false) }
    var initialApiCalled by rememberSaveable { mutableStateOf(false) }

    if (!initialApiCalled)
        LaunchedEffect(key1 = Unit) {
            viewModel.searchImage()
            initialApiCalled = true
        }

    LaunchedEffect(key1 = searchedImages.loadState) {
        if (searchedImages.loadState.refresh is LoadState.Error)
            Toast.makeText(
                context,
                "Error: ${(searchedImages.loadState.refresh as LoadState.Error).error.message}",
                Toast.LENGTH_LONG
            )
                .show()
    }

    TopBarHolder.currentTopBar = {
        SearchBar(
            modifier = Modifier.padding(8.dp),
            text = searchQuery,
            onTextChange = {
                viewModel.updateSearchQuery(it)
            },
            onSearchClicked = {viewModel.searchImage()},
            onCloseClicked = {viewModel.updateSearchQuery("")}
        )
    }


    if (openAlertDialog) {
        MyAlertDialog(
            onDismissRequest = { openAlertDialog = false },
            onConfirmation = {
                openAlertDialog = false
                navigateToImageDetail(viewModel.selectedImageId)
            },
            dialogTitle = stringResource(id = R.string.image_detail),
            dialogText = stringResource(id = R.string.view_image_detail),
            onConfirmationTitle = stringResource(id = R.string.confirm),
            onDismissTitle = stringResource(id = R.string.dismiss)
        )
    }


    if (searchedImages.loadState.refresh is LoadState.Loading) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(
                modifier = Modifier
                    .testTag("Loading")
                    .width(64.dp)
                    .align(Alignment.Center),
                color = MaterialTheme.colorScheme.secondary,
            )
        }
    } else if(searchedImages.itemCount == 0){
        SearchImageEmptyList {
            viewModel.searchImage()
        }
    } else {
        SearchList(modifier = Modifier.fillMaxSize(), items = searchedImages) {
            openAlertDialog = true
            viewModel.setSelectedImageId(it)
        }
    }

}
