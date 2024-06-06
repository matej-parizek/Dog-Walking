package cz.ctu.fit.bi.and.parizmat.semestral

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cz.ctu.fit.bi.and.parizmat.semestral.core.presentation.ui.MainScreen
import cz.ctu.fit.bi.and.parizmat.semestral.core.presentation.ui.theme.BIANDSEMETRALTheme
import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.local.dao.DogDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
//    private val dao: DogDao by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BIANDSEMETRALTheme {
                MainScreen()
            }
        }
    }

//    override fun onDestroy() {
//        super.onDestroy()
//        deleteDogTable()
//    }
//
//    override fun onStop() {
//        super.onStop()
//        deleteDogTable()
//    }
//    private fun deleteDogTable(){
//        CoroutineScope(Dispatchers.IO).launch{
//            dao.deleteAll()
//        }
//    }
}