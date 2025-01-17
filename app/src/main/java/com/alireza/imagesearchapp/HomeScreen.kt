package com.alireza.imageSearchApp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.alireza.imageSearchApp.navigation.AppNavGraph
import com.alireza.systemdesign.ui.topBarHolder.TopBarHolder

@Composable
fun HomeAppScreen(navController: NavHostController = rememberNavController()) {
        Scaffold(
            topBar = TopBarHolder.currentTopBar
        ) {
            Surface(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                AppNavGraph(navController = navController)
            }
        }

}
