package cz.ctu.fit.bi.and.parizmat.semestral.feature.settings.di

import cz.ctu.fit.bi.and.parizmat.semestral.core.data.db.WalkingDogDatabase
import cz.ctu.fit.bi.and.parizmat.semestral.feature.settings.data.SettingRepository
import cz.ctu.fit.bi.and.parizmat.semestral.feature.settings.data.SettingRepositoryImp
import cz.ctu.fit.bi.and.parizmat.semestral.feature.settings.data.local.SettingLocalDataSource
import cz.ctu.fit.bi.and.parizmat.semestral.feature.settings.data.local.SettingLocalDataSourceImp
import cz.ctu.fit.bi.and.parizmat.semestral.feature.settings.presentation.SettingDogViewModel
import cz.ctu.fit.bi.and.parizmat.semestral.feature.settings.presentation.SettingPersonViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val settingsModule = module {
    single { get<WalkingDogDatabase>().settingPersonDao() }

    factory<SettingLocalDataSource> { SettingLocalDataSourceImp(dao = get()) }

    single<SettingRepository> { SettingRepositoryImp(localSettingsSource = get()) }

    viewModel { SettingPersonViewModel(repository = get()) }

    viewModel { SettingDogViewModel(repository = get()) }
}