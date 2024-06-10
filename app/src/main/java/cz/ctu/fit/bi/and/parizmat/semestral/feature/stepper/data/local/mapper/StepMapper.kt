package cz.ctu.fit.bi.and.parizmat.semestral.feature.stepper.data.local.mapper

import cz.ctu.fit.bi.and.parizmat.semestral.feature.stepper.data.local.StepCountEntity
/**
 * Extension function for the StepCountEntity class to convert the steps count to a floating-point number.
 *
 * @return The steps count as a float.
 */
fun StepCountEntity.toFloat():Float{
    return steps.toFloat()
}