package ru.frozenpriest.curconv.ui.screen.settings

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import ru.frozenpriest.curconv.R
import ru.frozenpriest.curconv.domain.model.SortingMethod
import ru.frozenpriest.curconv.domain.model.SortingType

@Composable
fun SettingsScreen(viewModel: SettingsViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState(SortingMethod(SortingType.Alphabet, true))

    Column(Modifier.fillMaxSize()) {
        Text(
            text = stringResource(id = R.string.sort_setting),
            style = MaterialTheme.typography.h1
        )

        SortingMethodButton(
            text = R.string.sort_aphabetic,
            isSelected = state.type == SortingType.Alphabet,
            isAscending = state.isAscending,
            onClick = { viewModel.toggle(SortingType.Alphabet, state.isAscending) }
        )
        SortingMethodButton(
            text = R.string.sort_by_value,
            isSelected = state.type == SortingType.ByValue,
            isAscending = state.isAscending,
            onClick = { viewModel.toggle(SortingType.ByValue, state.isAscending) }
        )
    }
}

@Composable
fun SortingMethodButton(
    @StringRes text: Int,
    isSelected: Boolean,
    isAscending: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val tintColor = if (isSelected)
            MaterialTheme.colors.primaryVariant
        else
            MaterialTheme.colors.onSurface
        Text(
            text = stringResource(id = R.string.sort_aphabetic),
            color = tintColor
        )
        if (isSelected) {
            Icon(
                imageVector = if (isAscending) Icons.Default.ArrowUpward else Icons.Default.ArrowDownward,
                contentDescription = stringResource(
                    id = if (isAscending) R.string.is_ascending else R.string.is_descending
                ),
                tint = tintColor
            )
        }
    }
}
