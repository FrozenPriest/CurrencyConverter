package ru.frozenpriest.curconv.ui

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.ui.graphics.vector.ImageVector
import ru.frozenpriest.curconv.R

sealed class NavDestination(val destination: String) {
    object Popular : NavDestination("popular")
    object Favourite : NavDestination("favourite")
    object SortingSettings : NavDestination("sorting_settings")
}

sealed class BottomBarScreens(
    val destination: String,
    @StringRes val label: Int,
    val icon: ImageVector
) {
    object Popular :
        BottomBarScreens(
            NavDestination.Popular.destination,
            R.string.label_popular,
            Icons.Outlined.Explore
        )

    object Favourite : BottomBarScreens(
        NavDestination.Favourite.destination,
        R.string.label_favourite,
        Icons.Outlined.Favorite
    )

    companion object {
        val items = listOf(Popular, Favourite)
    }
}
