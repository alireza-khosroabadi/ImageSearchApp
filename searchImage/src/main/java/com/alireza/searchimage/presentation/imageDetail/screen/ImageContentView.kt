package com.alireza.searchimage.presentation.imageDetail.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Download
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.ModeComment
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.alireza.searchimage.domain.model.imageDetail.ImageDetail
import com.alireza.searchimage.presentation.imageDetail.screen.previewParameter.ImageDetailPreviewParameter
import com.alireza.systemdesign.theme.SearchImageTheme


@Composable
fun ImageTags(modifier: Modifier = Modifier, tags: List<String>) {
    LazyRow(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(tags, key = { it }) {
            Text(
                modifier = Modifier
                    .clip(shape = MaterialTheme.shapes.large)
                    .border(
                        border = BorderStroke(1.dp, color = Color.LightGray),
                        shape = MaterialTheme.shapes.large
                    )
                    .padding(6.dp),
                text = it,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
fun InformationContent(modifier: Modifier = Modifier, comments: String, downloads: String, likes: String) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconText(
            text = likes,
            icon = Icons.Outlined.Favorite,
            iconTint = Color.Red.copy(alpha = 0.8f),
            textStyle = MaterialTheme.typography.labelMedium
        )
        Spacer(Modifier.weight(1f))
        IconText(
            text = comments,
            icon = Icons.Outlined.ModeComment,
            iconTint = MaterialTheme.colorScheme.tertiary,
            textStyle = MaterialTheme.typography.labelMedium
        )
        Spacer(Modifier.weight(1f))
        IconText(
            text = downloads,
            icon = Icons.Outlined.Download,
            iconTint = MaterialTheme.colorScheme.tertiary,
            textStyle = MaterialTheme.typography.labelMedium
        )
    }
}

@Composable
fun IconText(text: String, icon: ImageVector, iconTint: Color, textStyle: TextStyle) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = iconTint
        )
        Spacer(Modifier.width(4.dp))
        Text(text = text, style = textStyle)
    }
}


@Preview
@Composable
fun ImageTagsPreview(@PreviewParameter(ImageDetailPreviewParameter::class) image: ImageDetail) {
    SearchImageTheme {
        ImageTags(tags = image.tagsAsList)
    }
}
