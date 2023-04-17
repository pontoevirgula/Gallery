package com.chslcompany.gallery.framework.di

import com.chslcompany.core.usecase.deletefavoriteusecase.GetDeleteFavoriteUseCase
import com.chslcompany.core.usecase.deletefavoriteusecase.GetDeleteFavoriteUseCaseImpl
import com.chslcompany.core.usecase.galleryusecase.GetGalleryUseCase
import com.chslcompany.core.usecase.galleryusecase.GetGalleyUseCaseImpl
import com.chslcompany.core.usecase.insertusecase.GetInsertGalleryUseCase
import com.chslcompany.core.usecase.insertusecase.GetInsertGalleryUseCaseImpl
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

    @Binds
    fun bindGalleyUseCase(useCaseImpl: GetGalleyUseCaseImpl) : GetGalleryUseCase

    @Binds
    fun bindInsertUseCase(useCaseImpl: GetInsertGalleryUseCaseImpl) : GetInsertGalleryUseCase

    @Binds
    fun bindRemoveFavoriteUseCase(useCaseImpl: GetDeleteFavoriteUseCaseImpl) : GetDeleteFavoriteUseCase
}