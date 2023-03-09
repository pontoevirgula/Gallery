package com.chslcompany.gallery.framework.di

import com.chslcompany.core.usecase.popularusecase.GetPopularUseCase
import com.chslcompany.core.usecase.popularusecase.GetPopularUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindPopularUseCase(useCaseImpl: GetPopularUseCaseImpl) : GetPopularUseCase
}