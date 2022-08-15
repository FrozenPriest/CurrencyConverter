package ru.frozenpriest.curconv.ui.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.frozenpriest.curconv.domain.model.Symbol
import ru.frozenpriest.curconv.ui.theme.CurConvTheme

@Composable
fun CurrencySelector(
    modifier: Modifier = Modifier,
    symbols: () -> List<Symbol>,
    selectedSymbol: Symbol?,
    isSelecting: Boolean,
    onExpand: () -> Unit,
    onSymbolSelected: (Symbol) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onExpand)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = selectedSymbol?.code ?: "",
            color = MaterialTheme.colors.onSurface
        )
        Icon(
            imageVector = if (isSelecting) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
            contentDescription = null,
            tint = MaterialTheme.colors.onSurface
        )
    }
    AnimatedVisibility(visible = isSelecting) {
        LazyColumn(Modifier.fillMaxWidth()) {
            items(
                items = symbols(),
                key = { item: Symbol -> item.code }
            ) { item ->
                Text(
                    text = item.code,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onSymbolSelected(item) }
                )
            }
        }
    }
}

@CombinedPreviews
@Composable
fun SelectorPreview() {
    CurConvTheme {
        CurrencySelector(
            symbols = { emptyList<Symbol>() },
            selectedSymbol = Symbol("USD", "d"),
            isSelecting = false,
            onExpand = { },
            onSymbolSelected = {}
        )
    }
}
