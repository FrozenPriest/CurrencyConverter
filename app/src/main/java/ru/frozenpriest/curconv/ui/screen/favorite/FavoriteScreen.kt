package ru.frozenpriest.curconv.ui.screen.favorite

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun FavoriteScreen(navController: NavController) {
    Text(text = "Fav", Modifier.fillMaxSize())
}
