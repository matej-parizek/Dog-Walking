package cz.ctu.fit.bi.and.parizmat.semestral.feature.settings.di

import cz.ctu.fit.bi.and.parizmat.semestral.core.data.db.WalkingDogDatabase
import cz.ctu.fit.bi.and.parizmat.semestral.feature.settings.data.SettingRepository
import cz.ctu.fit.bi.and.parizmat.semestral.feature.settings.data.SettingRepositoryImp
import cz.ctu.fit.bi.and.parizmat.semestral.feature.settings.data.local.SettingLocalDataSource
import cz.ctu.fit.bi.and.parizmat.semestral.feature.settings.data.local.SettingLocalDataSourceImp
import cz.ctu.fit.bi.and.parizmat.semestral.feature.settings.presentation.ui.dog.SettingDogViewModel
import cz.ctu.fit.bi.and.parizmat.semestral.feature.settings.presentation.ui.person.SettingPersonViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * This module sets up the dependency injection for database access and data handling,
 * specifically tailored to manage settings for both persons and dogs within the application.
 */
val settingsModule = module {

    single { get<WalkingDogDatabase>().settingPersonDao() }

    factory<SettingLocalDataSource> { SettingLocalDataSourceImp(dao = get()) }

    single<SettingRepository> { SettingRepositoryImp(localSettingsSource = get()) }

    viewModel { SettingPersonViewModel(repository = get()) }

    viewModel { SettingDogViewModel(repository = get()) }
}