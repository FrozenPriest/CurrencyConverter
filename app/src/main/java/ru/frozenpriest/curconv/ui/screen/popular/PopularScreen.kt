package ru.frozenpriest.curconv.ui.screen.popular

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ru.frozenpriest.curconv.ui.common.CombinedPreviews
import ru.frozenpriest.curconv.ui.common.SortingBar
import ru.frozenpriest.curconv.ui.common.Spacer16
import ru.frozenpriest.curconv.ui.theme.CurConvTheme

@Composable
fun PopularScreen(navController: NavController) {
    Column(Modifier.fillMaxSize()) {
        Spacer16()
        SortingBar(navController)
    }
}

@CombinedPreviews
@Composable
fun PopularScreenPreview() {
    CurConvTheme {
        PopularScreen(navController = rememberNavController())
    }
}
