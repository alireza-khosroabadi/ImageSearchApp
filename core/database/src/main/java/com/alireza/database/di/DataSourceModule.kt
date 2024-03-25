package com.alireza.database.di

import com.alireza.database.dataSource.image.ImageDataSource
import com.alireza.database.dataSource.image.ImageDataSourceImpl
import com.alireza.database.database.ImageDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    fun provideImageDataSource(database: ImageDatabase): ImageDataSource =
        ImageDataSourceImpl(database)

}
