package cz.ctu.fit.bi.and.semestral.feature.dictionaries.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
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
import cz.ctu.fit.bi.and.semestral.R
import cz.ctu.fit.bi.and.semestral.feature.dictionaries.data.DogResult
import cz.ctu.fit.bi.and.semestral.ui.theme.IconSize
import cz.ctu.fit.bi.and.semestral.ui.theme.Typography

@Composable
fun DictionariesScreen() {
    Search()
}

@Composable
fun Search() {
    var text by rememberSaveable { mutableStateOf("") }
    var expanded by rememberSaveable { mutableStateOf(false) }

    Column(
        Modifier
            .fillMaxSize()
            .semantics { isTraversalGroup = true },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchBar(
            modifier = Modifier
                .semantics { traversalIndex = -1f },
            inputField = {
                SearchBarDefaults.InputField(
                    query = text,
                    onQueryChange = { text = it },
                    onSearch = { expanded = false },
                    expanded = expanded,
                    onExpandedChange = { expanded = it },
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
                        if (expanded)
                            IconButton(onClick = { expanded = false }) {
                                Icon(
                                    modifier = Modifier.size(IconSize.smallHead),
                                    imageVector = Icons.Default.Close,
                                    contentDescription = null
                                )
                            }
                    },
                )
            },
            expanded = expanded,
            onExpandedChange = { expanded = it },
        ) {
            DogList(
                list = listOf(
                    DogResult()
                )
            )
        }
        Spacer(modifier = Modifier.size(10.dp))
        if (!expanded) {
            DogList(
                list = listOf(
                    DogResult()
                )
            )
        }
    }
}