package ru.frozenpriest.curconv.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.frozenpriest.curconv.R
import ru.frozenpriest.curconv.domain.model.Symbol
import ru.frozenpriest.curconv.ui.theme.CurConvTheme

@Composable
fun SortingBar(
    selectedSymbol: Symbol?,
    onSymbolChanged: (Symbol) -> Unit,
    onSortClick: () -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        var isSelecting by remember {
            mutableStateOf(false)
        }
        CurrencySelector(
            modifier = Modifier.weight(1f),
            symbols = { emptyList() },
            selectedSymbol = selectedSymbol,
            isSelecting = isSelecting,
            onExpand = { isSelecting = !isSelecting },
            onSymbolSelected = {
                isSelecting = false
                onSymbolChanged(it)
            }
        )
        SortingButton(onSortClick)
    }
}

@Composable
fun SortingButton(onSortClick: () -> Unit) {
    IconButton(
        onClick = onSortClick
    ) {
        Icon(
            imageVector = Icons.Filled.Sort,
            contentDescription = stringResource(id = R.string.button_sort),
            tint = MaterialTheme.colors.onBackground
        )
    }
}

@CombinedPreviews
@Composable
fun SortingBarPreview() {
    CurConvTheme {
        SortingBar(null, {}, {})
    }
}
