package ru.frozenpriest.curconv.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.frozenpriest.curconv.ui.screen.favorite.FavoriteScreen
import ru.frozenpriest.curconv.ui.screen.popular.PopularScreen
import ru.frozenpriest.curconv.ui.screen.settings.SettingsScreen
import ru.frozenpriest.curconv.ui.theme.CurConvTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CurConvTheme {
                val navController = rememberAnimatedNavController()
                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(
                            navController = navController,
                            items = BottomBarScreens.items
                        )
                    }
                ) { padding ->
                    AnimatedNavHost(
                        navController = navController,
                        startDestination = NavDestination.Popular.destination,
                        modifier = Modifier.padding(padding)
                    ) {
                        favoriteScreenNavComposable(navController)
                        popularScreenNavComposable(navController)
                        settingsScreenNavComposable()
                    }
                }
            }
        }
    }

    @Composable
    private fun BottomNavigationBar(
        navController: NavHostController,
        items: List<BottomBarScreens>
    ) {
        BottomNavigation {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            items.forEach { screen ->
                val label = stringResource(id = screen.label)
                BottomNavigationItem(
                    icon = {
                        Icon(imageVector = screen.icon, contentDescription = label)
                    },
                    label = {
                        Text(label)
                    },
                    selected = currentRoute == screen.destination,
                    alwaysShowLabel = false,
                    onClick = {
                        if (currentRoute != screen.destination) {
                            navController.navigate(
                                screen.destination,
                                navOptions = NavOptions.Builder().setLaunchSingleTop(true).build()
                            )
                        }
                    }
                )
            }
        }
    }

    @OptIn(ExperimentalAnimationApi::class)
    private fun NavGraphBuilder.popularScreenNavComposable(navController: NavHostController) {
        composable(
            NavDestination.Popular.destination,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            }
        ) { PopularScreen(navController) }
    }

    @OptIn(ExperimentalAnimationApi::class)
    private fun NavGraphBuilder.favoriteScreenNavComposable(navController: NavHostController) {
        composable(
            NavDestination.Favourite.destination,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            }
        ) { FavoriteScreen(navController) }
    }

    @OptIn(ExperimentalAnimationApi::class)
    private fun NavGraphBuilder.settingsScreenNavComposable() {
        composable(
            NavDestination.SortingSettings.destination,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Down,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Up,
                    animationSpec = tween(700)
                )
            }
        ) { SettingsScreen() }
    }
}
