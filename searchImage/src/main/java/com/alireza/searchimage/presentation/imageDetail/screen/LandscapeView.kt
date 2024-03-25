package com.alireza.searchimage.presentation.imageDetail.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.alireza.searchimage.R
import com.alireza.searchimage.domain.model.imageDetail.ImageDetail
import com.alireza.searchimage.presentation.imageDetail.screen.previewParameter.ImageDetailPreviewParameter
import com.alireza.systemdesign.theme.SearchImageTheme


@Composable
fun LandscapeView(image: ImageDetail) {
    Column {
        Row(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f),
                placeholder = painterResource(id = R.drawable.ic_placeholder),
                model = image.largeImageURL, contentDescription = image.tags,
                contentScale = ContentScale.FillBounds
            )

            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .border(
                        BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface),
                        shape = MaterialTheme.shapes.large
                    )
                    .padding(16.dp)
            ) {
                InformationContent(
                    comments = image.comments,
                    downloads = image.downloads,
                    likes = image.likes
                )
                Spacer(modifier = Modifier.height(16.dp))
                ImageTags(modifier = Modifier.fillMaxWidth(), tags = image.tagsAsList)
            }
        }
    }
}



@Preview(
    showSystemUi = true,
    device = "spec:width=411dp,height=891dp,dpi=420,isRound=false,chinSize=0dp,orientation=landscape"
)
@Composable
fun LandscapeViewPreview(@PreviewParameter(ImageDetailPreviewParameter::class) image: ImageDetail) {
    SearchImageTheme {
        LandscapeView(image)
    }
}
