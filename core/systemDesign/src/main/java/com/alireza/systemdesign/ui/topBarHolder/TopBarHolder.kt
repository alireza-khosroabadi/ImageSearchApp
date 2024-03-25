package com.alireza.systemdesign.ui.topBarHolder

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object TopBarHolder {
    var currentTopBar by mutableStateOf<@Composable () -> Unit>({})
}
