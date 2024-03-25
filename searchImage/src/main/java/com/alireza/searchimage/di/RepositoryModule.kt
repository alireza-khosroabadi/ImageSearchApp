package com.alireza.searchimage.di

import com.alireza.database.dataSource.image.ImageDataSource
import com.alireza.searchimage.data.remote.apiService.ApiService
import com.alireza.searchimage.data.repository.searchImage.SearchImageRepositoryImpl
import com.alireza.searchimage.domain.repository.SearchImageRepository
import com.alireza.utility.network.NetworkConnection
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideSearchImageRepository(
        apiService: ApiService,
        imageDataSource: ImageDataSource,
        networkConnection: NetworkConnection
    ): SearchImageRepository =
        SearchImageRepositoryImpl(
            apiService,
            imageDataSource,
            networkConnection
        )
}
