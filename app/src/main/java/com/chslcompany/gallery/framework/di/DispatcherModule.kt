package com.chslcompany.gallery.framework.di

import com.chslcompany.core.usecase.base.AppCoroutineDispatcher
import com.chslcompany.core.usecase.base.CoroutinesDispatchers
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface DispatcherModule {

    @Binds
    fun bindDispatcher(appCoroutineDispatcher: AppCoroutineDispatcher) : CoroutinesDispatchers
}