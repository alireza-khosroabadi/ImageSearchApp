package com.alireza.imageSearchApp.searchimage.di

import com.alireza.searchimage.di.RepositoryModule
import com.alireza.imageSearchApp.searchimage.domain.repository.FakeSearchImageRepositoryImpl
import com.alireza.searchimage.domain.repository.SearchImageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class]
)
object TestRepositoryModule {

    @Provides
    fun provideFakeSearchImageRepository(): SearchImageRepository =
        FakeSearchImageRepositoryImpl()
}
