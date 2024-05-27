package cz.ctu.fit.bi.and.parizmat.semestral

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cz.ctu.fit.bi.and.parizmat.semestral.core.ui.MainScreen
import cz.ctu.fit.bi.and.parizmat.semestral.core.ui.theme.BIANDSEMETRALTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BIANDSEMETRALTheme {
                MainScreen()
            }
        }
    }
}