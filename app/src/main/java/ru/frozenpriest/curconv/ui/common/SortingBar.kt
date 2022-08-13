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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ru.frozenpriest.curconv.R
import ru.frozenpriest.curconv.ui.NavDestination
import ru.frozenpriest.curconv.ui.theme.CurConvTheme

@Composable
fun SortingBar(navController: NavController) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CurrencySelector(modifier = Modifier.weight(1f))
        SortingButton(navController)
    }
}

@Composable
fun SortingButton(navController: NavController) {
    IconButton(
        onClick = {
            navController.navigate(NavDestination.SortingSettings.destination)
        }
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
        SortingBar(navController = rememberNavController())
    }
}
