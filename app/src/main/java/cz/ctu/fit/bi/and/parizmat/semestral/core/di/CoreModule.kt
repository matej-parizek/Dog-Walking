package cz.ctu.fit.bi.and.parizmat.semestral.core.di

import cz.ctu.fit.bi.and.parizmat.semestral.core.data.api.RetroProvider
import cz.ctu.fit.bi.and.parizmat.semestral.core.data.db.WalkingDogDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import kotlin.math.sin

val coreModule = module {
    single { WalkingDogDatabase.newInstance(androidContext()) }
    single(named("dogProvider")) { RetroProvider.dogProvider() }
    single(named("imageProvider")) { RetroProvider.imageProvider() }
}