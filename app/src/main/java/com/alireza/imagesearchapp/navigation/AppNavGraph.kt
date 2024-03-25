package com.alireza.imageSearchApp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.alireza.searchimage.presentation.imageDetail.navigation.imageDetailScreen
import com.alireza.searchimage.presentation.imageDetail.navigation.navigateToImageDetail
import com.alireza.searchimage.presentation.searchImage.navigation.searchImageRout
import com.alireza.searchimage.presentation.searchImage.navigation.searchImageScreen


@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = searchImageRout) {
        searchImageScreen { navController.navigateToImageDetail(it) }
        imageDetailScreen {navController.navigateUp()}
    }
}
