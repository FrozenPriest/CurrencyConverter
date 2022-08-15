package ru.frozenpriest.curconv.ui.screen.popular

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ru.frozenpriest.curconv.R
import ru.frozenpriest.curconv.ui.common.CombinedPreviews
import ru.frozenpriest.curconv.ui.common.RefreshableCurrencyTable
import ru.frozenpriest.curconv.ui.theme.CurConvTheme

@Composable
fun PopularScreen(navController: NavController, viewModel: PopularViewModel = hiltViewModel()) {
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
        titleId = R.string.popular_currencies,
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

@CombinedPreviews
@Composable
fun PopularScreenPreview() {
    CurConvTheme {
        PopularScreen(navController = rememberNavController())
    }
}
