package com.alireza.searchimage.presentation.imageDetail.screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alireza.systemdesign.theme.SearchImageTheme
import com.alireza.systemdesign.ui.topBarHolder.TopBarHolder

@Composable
internal fun ImageDetailTopBar(
    user: String,
    onBackClick: () -> Unit
) {
    TopBarHolder.currentTopBar = {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            IconText(
                text = user,
                icon = Icons.Outlined.Person,
                iconTint = MaterialTheme.colorScheme.onSurface,
                textStyle = MaterialTheme.typography.titleMedium
            )

        }
    }
}


@Preview
@Composable
fun PreviewImageDetailTopBar(){
   SearchImageTheme {
       ImageDetailTopBar(user = "Alireza") {

       }
   }
}

