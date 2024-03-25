package com.alireza.searchimage.presentation.searchImage.screen

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import com.alireza.searchimage.R
import com.alireza.searchimage.domain.model.searchImage.Image
import com.alireza.searchimage.presentation.searchImage.screen.imagePreviewProvider.ImagePreviewProvider
import com.alireza.systemdesign.theme.SearchImageTheme

@Composable
fun SearchList(
    modifier: Modifier = Modifier,
    items: LazyPagingItems<Image>,
    onItemClick: (String) -> Unit
) {
    val configuration = LocalConfiguration.current
    val cellCount = if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) 2 else 4
    LazyVerticalGrid(
        modifier = modifier
            .fillMaxSize()
            .testTag("ImageLazyVerticalGrid")
            .padding(top = 8.dp),
        columns = GridCells.Fixed(cellCount)
    ) {
        items(
            count = items.itemCount,
            key = items.itemKey { it.id },
        ) { index ->
            val image = items[index]
            if (image != null) {
                ImageItem(
                    image = image,
                    modifier = Modifier.fillMaxWidth(),
                    onItemClick = onItemClick
                )
            }
        }
        item {
            if (items.loadState.append is LoadState.Loading) {
                LoadingItem()
            }
        }
    }
}

@Composable
fun LoadingItem() {
    Box(
        modifier = Modifier
            .height(200.dp)
            .aspectRatio(1f),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(40.dp),
            color = MaterialTheme.colorScheme.secondary,
        )
    }
}


@Composable
fun ImageItem(
    modifier: Modifier = Modifier,
    image: Image,
    onItemClick: (String) -> Unit
) {
    Box(
        modifier = modifier
            .padding(8.dp)
            .clip(MaterialTheme.shapes.medium)
            .clickable { onItemClick(image.id) },
        contentAlignment = Alignment.BottomCenter
    ) {
        AsyncImage(
            model = image.previewURL,
            contentDescription = image.tags,
            contentScale = ContentScale.FillBounds,
            placeholder = painterResource(id = R.drawable.ic_placeholder),
            modifier = Modifier
                .fillMaxSize()
                .height(200.dp)
        )

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .alpha(ContentAlpha.medium),
            color = Color.Black
        ) {
            BottomContent(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(4.dp), user = image.user, tags = image.tags
            )
        }


    }
}

@Composable
fun BottomContent(
    modifier: Modifier,
    user: String,
    tags: String
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(4.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = user,
            style = MaterialTheme.typography.titleSmall,
            color = Color.White,
            overflow = TextOverflow.Ellipsis,
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = tags,
            style = MaterialTheme.typography.labelSmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = Color.White.copy(alpha = 0.8f)
        )
    }
}

@Preview
@Composable
fun ImageItemPreview(@PreviewParameter(ImagePreviewProvider::class) image: Image) {
    SearchImageTheme {
        ImageItem(
            image = image
        ) {}
    }
}

