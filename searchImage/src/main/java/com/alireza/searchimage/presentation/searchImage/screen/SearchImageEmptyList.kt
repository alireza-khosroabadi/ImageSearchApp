package com.alireza.searchimage.presentation.searchImage.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alireza.searchimage.R
import com.alireza.systemdesign.theme.SearchImageTheme

@Composable
fun SearchImageEmptyList(onRefreshClick: () -> Unit) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Icon(modifier = Modifier.size(100.dp),
            painter = painterResource(id = R.drawable.ic_empty_result),
            contentDescription = null)

        Text(modifier = Modifier.padding(top = 32.dp),
            text = stringResource(id = R.string.nothing_found))

        Button(modifier = Modifier.padding(top = 32.dp),
            onClick = onRefreshClick) {
            Text(text = stringResource(id = R.string.retry))
        }
    }

}


@Preview
@Composable
private fun PreviewSearchImageEmptyList() {
    SearchImageTheme {
        SearchImageEmptyList {

        }
    }
}
