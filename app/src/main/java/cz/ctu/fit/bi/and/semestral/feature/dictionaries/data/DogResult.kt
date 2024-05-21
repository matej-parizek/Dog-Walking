package cz.ctu.fit.bi.and.semestral.feature.dictionaries.data

import androidx.compose.ui.res.stringResource
import cz.ctu.fit.bi.and.semestral.R

data class DogResult (
    val uri: String = "Error",
    val breed: String =  "Error",
    val name: String = "Error",
    val description: String = "Error",
    val hypoallergenic: Boolean = false,
    val image: String =  "Error",
    val type: String =  "Error",
    val id: Int = -1,
)
