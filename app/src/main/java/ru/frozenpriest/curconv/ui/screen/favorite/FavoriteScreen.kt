package ru.frozenpriest.curconv.ui.screen.favorite

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ru.frozenpriest.curconv.R
import ru.frozenpriest.curconv.ui.common.RefreshableCurrencyTable

@Composable
fun FavoriteScreen(navController: NavController, viewModel: FavoriteViewModel = hiltViewModel()) {
    val isLoading by viewModel.isLoading.collectAsState()
    val symbol by viewModel.selectedSymbol.collectAsState()
    val symbols by viewModel.symbols.collectAsState(initial = emptyList())
    val currencies by viewModel.currencyValues.collectAsState(initial = emptyList())
    val errorEvent by viewModel.errorFlow.collectAsState()

    val context = LocalContext.current
    LaunchedEffect(key1 = Unit) {
        viewModel.updateSymbols()
    }
    LaunchedEffect(key1 = errorEvent) {
        errorEvent?.let {
            Toast.makeText(context, it.error, Toast.LENGTH_SHORT).show()
        }
    }

    RefreshableCurrencyTable(
        titleId = R.string.favorite_currencies,
        isLoading = isLoading,
        refreshValues = viewModel::refreshValues,
        setSymbol = viewModel::setSymbol,
        favoriteClicked = viewModel::favoriteClicked,
        symbol = symbol,
        symbols = symbols,
        navController = navController,
        currencies = currencies
    )
}
