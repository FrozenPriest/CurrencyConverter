package ru.frozenpriest.curconv.ui.screen.popular

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ru.frozenpriest.curconv.domain.model.CurrencyValue
import ru.frozenpriest.curconv.ui.common.CombinedPreviews
import ru.frozenpriest.curconv.ui.common.CurrencyTable
import ru.frozenpriest.curconv.ui.common.SortingBar
import ru.frozenpriest.curconv.ui.common.Spacer16
import ru.frozenpriest.curconv.ui.theme.CurConvTheme

@Composable
fun PopularScreen(navController: NavController) {
    Column(Modifier.fillMaxSize()) {
        Spacer16()
        SortingBar(navController)
        Divider()
        CurrencyTable(
            getItems = {
                listOf(
                    CurrencyValue(1, "USD", 12.43, true),
                    CurrencyValue(2, "TEST", 12555.43, false)
                )
            },
            {}
        )
    }
}

@CombinedPreviews
@Composable
fun PopularScreenPreview() {
    CurConvTheme {
        PopularScreen(navController = rememberNavController())
    }
}
