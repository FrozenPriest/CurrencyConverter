package ru.frozenpriest.curconv.ui.common

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import ru.frozenpriest.curconv.domain.model.CurrencyValue
import ru.frozenpriest.curconv.domain.model.Symbol
import ru.frozenpriest.curconv.ui.NavDestination

@Composable
fun RefreshableCurrencyTable(
    @StringRes titleId: Int,
    isLoading: Boolean,
    symbol: Symbol?,
    refreshValues: () -> Unit,
    setSymbol: (Symbol) -> Unit,
    favoriteClicked: (CurrencyValue) -> Unit,
    symbols: List<Symbol>,
    navController: NavController,
    currencies: List<CurrencyValue>
) {
    SwipeRefresh(
        state = rememberSwipeRefreshState(isLoading),
        onRefresh = refreshValues,
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp)
        ) {
            Spacer16()
            SortingBar(
                selectedSymbol = symbol,
                symbols = symbols,
                onSymbolChanged = setSymbol,
                onSortClick = {
                    navController.navigate(NavDestination.SortingSettings.destination)
                }
            )
            Divider()
            Spacer16()
            Text(
                text = stringResource(id = titleId),
                style = MaterialTheme.typography.h1
            )
            CurrencyTable(
                items = currencies,
                favoriteClicked
            )
        }
    }
}
