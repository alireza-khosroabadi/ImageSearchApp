package com.alireza.searchimage.presentation.imageDetail.screen.previewParameter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.alireza.searchimage.domain.model.imageDetail.ImageDetail

class ImageDetailPreviewParameter : PreviewParameterProvider<ImageDetail> {
    override val values: Sequence<ImageDetail>
        get() = sequenceOf(
            ImageDetail(
                id = 1,
                largeImageURL = "",
                likes = "10",
                tags = "tag one,tag2",
                user = "user",
                comments = "15",
                downloads = "8"
            ),
            ImageDetail(
                id = 1,
                largeImageURL = "",
                likes = "10",
                tags = "tag one, tag2 , Tag three",
                user = "user",
                comments = "15",
                downloads = "8"
            )
        )
}
