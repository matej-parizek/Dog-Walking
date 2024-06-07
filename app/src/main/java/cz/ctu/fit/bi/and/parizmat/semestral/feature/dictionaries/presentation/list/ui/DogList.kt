package cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.presentation.list.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.domain.Dog
import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.presentation.list.DogsListState

@Composable
fun DogList(
    navigate: (String) -> Unit,
    dogsListState: DogsListState,
) {
    val list = dogsListState.dogs
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(list, key = { it.id }) { dog ->
            DogCard(
                dog = dog,
                onClick = navigate,
            )
        }
    }

}

@Composable
private fun DogCard(
    dog: Dog,
    onClick: (String) -> Unit = {},
) {
    Card(
        modifier = Modifier.clickable(onClick = { onClick(dog.id) }),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
    ) {
        DogListItem(
            dog = dog,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        )
    }
}

