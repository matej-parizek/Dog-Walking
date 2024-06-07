package cz.ctu.fit.bi.and.parizmat.semestral.core.di

import cz.ctu.fit.bi.and.parizmat.semestral.core.data.api.RetroProvider
import cz.ctu.fit.bi.and.parizmat.semestral.core.data.db.WalkingDogDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import kotlin.math.sin

/**
 * Core module for dependency injection using Koin. This module sets up the fundamental components needed throughout the application.
 * It includes the database instance and network service providers necessary for data management and API interactions.
 *
 * This configuration ensures that these critical components are created once and reused, promoting efficient resource use and consistency.
 */
val coreModule = module {
    single { WalkingDogDatabase.newInstance(androidContext()) }
    single(named("dogProvider")) { RetroProvider.dogProvider() }
    single(named("imageProvider")) { RetroProvider.imageProvider() }
}