package ru.frozenpriest.curconv.ui.screen.favorite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import ru.frozenpriest.curconv.R
import ru.frozenpriest.curconv.ui.NavDestination
import ru.frozenpriest.curconv.ui.common.CurrencyTable
import ru.frozenpriest.curconv.ui.common.SortingBar
import ru.frozenpriest.curconv.ui.common.Spacer16

@Composable
fun FavoriteScreen(navController: NavController, viewModel: FavoriteViewModel = hiltViewModel()) {
    val isLoading by viewModel.isLoading.collectAsState()
    val symbol by viewModel.selectedSymbol.collectAsState()
    val symbols by viewModel.symbols.collectAsState(initial = emptyList())
    val currencies by viewModel.currencyValues.collectAsState(initial = emptyList())

    LaunchedEffect(key1 = Unit) {
        viewModel.updateSymbols()
    }

    SwipeRefresh(
        state = rememberSwipeRefreshState(isLoading),
        onRefresh = { viewModel.refreshValues() },
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
                onSymbolChanged = viewModel::setSymbol,
                onSortClick = {
                    navController.navigate(NavDestination.SortingSettings.destination)
                }
            )
            Divider()
            Spacer16()
            Text(
                text = stringResource(id = R.string.favorite_currencies),
                style = MaterialTheme.typography.h1
            )
            CurrencyTable(
                items = currencies,
                viewModel::favoriteClicked
            )
        }
    }
}
