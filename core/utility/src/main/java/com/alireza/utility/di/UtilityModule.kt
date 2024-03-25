package com.alireza.utility.di

import android.content.Context
import com.alireza.utility.dispatcher.DefaultDispatcher
import com.alireza.utility.dispatcher.IoDispatcher
import com.alireza.utility.dispatcher.MainDispatcher
import com.alireza.utility.dispatcher.MainImmediateDispatcher
import com.alireza.utility.network.NetworkConnection
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UtilityModule{

    @DefaultDispatcher
    @Provides
    @Singleton
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @IoDispatcher
    @Provides
    @Singleton
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @MainDispatcher
    @Provides
    @Singleton
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @MainImmediateDispatcher
    @Provides
    @Singleton
    fun providesMainImmediateDispatcher(): CoroutineDispatcher = Dispatchers.Main.immediate


    @Provides
    @Singleton
    fun provideNetworkConnection(@ApplicationContext context: Context): NetworkConnection =
        NetworkConnection(context)

}
