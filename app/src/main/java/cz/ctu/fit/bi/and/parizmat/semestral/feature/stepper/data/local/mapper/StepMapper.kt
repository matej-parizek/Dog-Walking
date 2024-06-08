package cz.ctu.fit.bi.and.parizmat.semestral.feature.stepper.data.local.mapper

import cz.ctu.fit.bi.and.parizmat.semestral.feature.stepper.data.local.StepCountEntity

fun StepCountEntity.toFloat():Float{
    return steps.toFloat()
}