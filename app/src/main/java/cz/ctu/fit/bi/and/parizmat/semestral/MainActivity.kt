package cz.ctu.fit.bi.and.parizmat.semestral

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cz.ctu.fit.bi.and.parizmat.semestral.core.presentation.ui.MainScreen
import cz.ctu.fit.bi.and.parizmat.semestral.core.presentation.ui.theme.BIANDSEMETRALTheme
import cz.ctu.fit.bi.and.parizmat.semestral.core.presentation.ui.theme.sideBarSize
import cz.ctu.fit.bi.and.parizmat.semestral.core.presentation.ui.theme.sideBarSizeHeight
import cz.ctu.fit.bi.and.parizmat.semestral.core.presentation.ui.theme.sideBarSizeWidth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        sideBarSize = if(resources.configuration.orientation == 1){
            sideBarSizeHeight
        }else{
            sideBarSizeWidth
        }
        super.onCreate(savedInstanceState)
        setContent {
            BIANDSEMETRALTheme {
                MainScreen()
            }
        }
        Log.d("Orientation", resources.configuration.orientation.toString())
    }

}