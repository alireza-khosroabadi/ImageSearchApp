package com.alireza.searchimage.presentation.searchImage.screen.imagePreviewProvider

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.alireza.searchimage.domain.model.searchImage.Image

class ImagePreviewProvider:PreviewParameterProvider<Image> {
    override val values: Sequence<Image>
        get() = sequenceOf(Image(
            id = "123",
            previewURL = "www.google.com",
            tags = "Tag one, Tag two",
            user = "alireza",
            userId = 234,
            uniqueId= 234
        ))
}
