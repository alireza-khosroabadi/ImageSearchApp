package com.alireza.database.di

import android.content.Context
import androidx.room.Room
import com.alireza.database.dao.image.ImageDao
import com.alireza.database.database.ImageDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {


    @Provides
    @Singleton
    internal fun provideImageDataBase(@ApplicationContext context: Context):ImageDatabase = Room.databaseBuilder(
    context,
    ImageDatabase::class.java, "database-name"
    )
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    internal fun provideImageDao(database: ImageDatabase):ImageDao =
        database.imageDao()

}
