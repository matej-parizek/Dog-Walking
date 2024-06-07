package cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.presentation.list.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cz.ctu.fit.bi.and.parizmat.semestral.R
import cz.ctu.fit.bi.and.parizmat.semestral.core.presentation.ui.LoadingScreen
import cz.ctu.fit.bi.and.parizmat.semestral.core.presentation.ui.theme.IconSize
import cz.ctu.fit.bi.and.parizmat.semestral.core.presentation.ui.theme.Typography
import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.presentation.list.DogsListState
import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.presentation.list.DogViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DictionariesScreen(
    onClick: (String) -> Unit,
    viewModel: DogViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val query by viewModel.name.collectAsStateWithLifecycle()
    LoadingScreen(screenState = state) { data ->
        Search(
            state = data,
            query = query,
            onChange = { viewModel.onChange(it) },
            onRetry = { viewModel.retry() },
            navigate = onClick
        )
    }
}

@Composable
@ExperimentalMaterial3Api
fun Search(
    state: DogsListState,
    query: String,
    onChange: (String) -> Unit,
    navigate: (String) -> Unit,
    onRetry: () -> Unit,
) {

    var expanded by rememberSaveable { mutableStateOf(false) }

    Column(
        Modifier
            .fillMaxSize()
            .semantics { isTraversalGroup = true },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchBar(
            active = expanded,
            onActiveChange = { expanded = it },
            modifier = Modifier
                .semantics { traversalIndex = -1f },
            query = query,
            onQueryChange = { onChange(it) },
            onSearch = { onChange(it) },
            placeholder = {
                Text(
                    text = stringResource(R.string.search_text),
                    style = Typography.displayMedium,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            },
            leadingIcon = {
                Icon(
                    modifier = Modifier.size(IconSize.smallHead),
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            },
            trailingIcon = {
                if (expanded) {
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(
                            modifier = Modifier.size(IconSize.smallHead),
                            imageVector = Icons.Default.Close,
                            contentDescription = null
                        )
                    }
                }
            }
        ) {
            DogList(dogsListState = state, navigate = navigate )
        }
        Spacer(modifier = Modifier.size(10.dp))
        if (!expanded) {
            onRetry()
            DogList(dogsListState = state, navigate = navigate )
        }
    }
}