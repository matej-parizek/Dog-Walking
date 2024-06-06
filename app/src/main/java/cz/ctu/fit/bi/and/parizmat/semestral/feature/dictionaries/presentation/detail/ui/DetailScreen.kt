package cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.presentation.detail.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.tooling.preview.Preview
import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.presentation.list.DogViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
@Preview
fun DetailScreen(
    viewModel: DogViewModel = koinViewModel()
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        Card() {

        }

    }
}