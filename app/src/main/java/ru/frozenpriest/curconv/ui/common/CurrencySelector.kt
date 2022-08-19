package ru.frozenpriest.curconv.ui.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.frozenpriest.curconv.domain.model.Symbol
import ru.frozenpriest.curconv.ui.theme.CurConvTheme

/**
 * Expanding list that allows to select one symbol
 * @param symbols list of symbol to be shown and selected from
 * @param isSelecting when true list is expanded and shows all symbols
 * @param onSymbolSelected is called when user clicks on symbol from expanded lists
 */
@Composable
fun CurrencySelector(
    symbols: List<Symbol>,
    isSelecting: Boolean,
    onSymbolSelected: (Symbol) -> Unit
) {
    AnimatedVisibility(
        visible = isSelecting,
        enter = expandVertically(expandFrom = Alignment.Top),
        exit = shrinkVertically(shrinkTowards = Alignment.Top)
    ) {
        LazyColumn(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f)
        ) {
            items(
                items = symbols,
                key = { item: Symbol -> item.code }
            ) { item ->
                Text(
                    text = item.code,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
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
            symbols = emptyList<Symbol>(),
            isSelecting = false,
            onSymbolSelected = {}
        )
    }
}
