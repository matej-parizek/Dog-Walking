package cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.presentation.detail.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import cz.ctu.fit.bi.and.parizmat.semestral.R
import cz.ctu.fit.bi.and.parizmat.semestral.core.presentation.ui.LoadingScreen
import cz.ctu.fit.bi.and.parizmat.semestral.core.presentation.ui.theme.IconSize
import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.domain.Dog
import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.presentation.detail.DogDetailViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailScreen(
    viewModel: DogDetailViewModel = koinViewModel(),
) {
    val state by viewModel.screenState.collectAsState()
    LoadingScreen(screenState = state) { data ->
        DogCard(data)
    }
}


@Composable
@Preview
private fun DogCard(dog: Dog = Dog()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            elevation = CardDefaults.cardElevation(2.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background
            )
        ) {
            DogHeaderCard(dog)
            Spacer(modifier = Modifier.height(4.dp))
            HorizontalDivider(
                color = MaterialTheme.colorScheme.outlineVariant,
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp
            )
            Spacer(modifier = Modifier.height(16.dp))
            DogCardDetails(dog = dog)
            Spacer(modifier = Modifier.height(24.dp))

        }
    }
}

@Composable
private fun DogHeaderCard(
    dog: Dog = Dog()
) {
    Row(
        modifier = Modifier.padding(all = 16.dp)
    ) {
        AsyncImage(
            model = dog.image,
            contentDescription = stringResource(R.string.image),
            error = painterResource(id = R.drawable.dog),
            modifier = Modifier.size(size = IconSize.largeImage),
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        ) {
            Text(
                text = dog.group,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondary
            )
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = dog.name,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }
    }
}

@Composable
private fun DogCardDetails(dog: Dog) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        Information(
            title = stringResource(R.string.is_hypoallergenic), value =
            if (dog.hypoallergenic) stringResource(R.string.hypoallergenic) else stringResource(R.string.not_hypoallergenic)
        )
        Information(
            title = stringResource(R.string.female_weight), value = stringResource(
                R.string.weight_range,
                dog.femaleWeight.min,
                dog.femaleWeight.max
            )
        )
        Information(
            title = stringResource(R.string.male_weight), value = stringResource(
                R.string.weight_range,
                dog.maleWeight.min,
                dog.maleWeight.max
            )
        )
        Information(
            title = stringResource(R.string.lifespan), value = stringResource(
                R.string.yearsRange,
                dog.life.min,
                dog.life.max
            )
        )
        Information(title = stringResource(R.string.info), value = dog.description)
    }
}

@Composable
private fun Information(title: String, value: String) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSecondary,
        )
        Text(
            text = value.takeUnless { it.isBlank() } ?: "-",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onPrimary,
        )
    }
}
