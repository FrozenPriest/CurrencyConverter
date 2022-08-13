package ru.frozenpriest.curconv.ui.screen.popular

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
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import ru.frozenpriest.curconv.R
import ru.frozenpriest.curconv.ui.NavDestination
import ru.frozenpriest.curconv.ui.common.CombinedPreviews
import ru.frozenpriest.curconv.ui.common.CurrencyTable
import ru.frozenpriest.curconv.ui.common.SortingBar
import ru.frozenpriest.curconv.ui.common.Spacer16
import ru.frozenpriest.curconv.ui.theme.CurConvTheme

@Composable
fun PopularScreen(navController: NavController, viewModel: PopularViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.refresh()
    }

    SwipeRefresh(
        state = rememberSwipeRefreshState(state.isLoading),
        onRefresh = { viewModel.refresh() },
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp)
        ) {
            Spacer16()
            SortingBar {
                navController.navigate(NavDestination.SortingSettings.destination)
            }
            Divider()
            Spacer16()
            Text(
                text = stringResource(id = R.string.popular_currencies),
                style = MaterialTheme.typography.h1
            )
            CurrencyTable(
                getItems = state::currencies,
                viewModel::favoriteClicked
            )
        }
    }
}

@CombinedPreviews
@Composable
fun PopularScreenPreview() {
    CurConvTheme {
        PopularScreen(navController = rememberNavController())
    }
}
