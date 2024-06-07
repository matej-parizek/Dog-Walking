package cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.di

import cz.ctu.fit.bi.and.parizmat.semestral.core.data.db.WalkingDogDatabase
import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.DogRepository
import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.DogRepositoryImp
import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.api.DogApi
import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.api.DogRemoteDataSource
import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.api.DogRemoteDataSourceImp
import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.api.ImageApi
import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.local.DogLocalDataSource
import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.local.DogLocalDataSourceImp
import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.presentation.detail.DogDetailViewModel
import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.presentation.list.DogViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

/**
 * Dependency injection module for the Dog Dictionary feature. This module configures and provides
 * all necessary dependencies.
 *
 * - `DogApi`: API interface for dog-related network operations. This API is used for data of dog breed
 * - `ImageApi`: API interface for image-related network operations. This API is used for image of dog
 */

val dictionariesModule = module {
    // Network layer dependencies
    single { get<Retrofit>(named("dogProvider")).create(DogApi::class.java) }
    single { get<Retrofit>(named("imageProvider")).create(ImageApi::class.java) }

    // Repository and data source dependencies
    factory<DogRemoteDataSource> { DogRemoteDataSourceImp(dogApi = get(), imageApi = get()) }
    single { get<WalkingDogDatabase>().dogDao() }
    factory<DogLocalDataSource> { DogLocalDataSourceImp(dao = get()) }
    single<DogRepository> {
        DogRepositoryImp(dogRemoteDataSource = get(), dogLocalDataSource = get())
    }

    // ViewModel dependencies
    viewModel { DogViewModel(repository = get(), savedStateHandle = get()) }
    viewModel { DogDetailViewModel(repository = get(), savedStateHandle = get()) }
}