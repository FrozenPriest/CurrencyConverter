package ru.frozenpriest.curconv.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.frozenpriest.curconv.R
import ru.frozenpriest.curconv.domain.model.CurrencyValue
import ru.frozenpriest.curconv.ui.theme.CurConvTheme

@Composable
fun CurrencyTable(
    items: List<CurrencyValue>,
    onFavoriteClicked: (CurrencyValue) -> Unit
) {
    LazyColumn(Modifier.fillMaxSize()) {
        this.items(items = items, key = { it.from + it.to }) { item ->
            CurrencyItem(item = item, onFavoriteClicked = { onFavoriteClicked(item) })
        }
    }
}

@Composable
fun CurrencyItem(
    modifier: Modifier = Modifier,
    item: CurrencyValue,
    onFavoriteClicked: () -> Unit
) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = item.to,
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = item.value.toString(),
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier.weight(1f)
        )
        IconButton(onClick = onFavoriteClicked) {
            Icon(
                imageVector = getFavoriteIcon(item.isFavorite ?: false),
                contentDescription = getContentDescFavorite(item.isFavorite ?: false),
                tint = MaterialTheme.colors.onBackground
            )
        }
    }
}

@Composable
private fun getContentDescFavorite(isFavorite: Boolean) =
    stringResource(id = if (isFavorite) R.string.in_favorite else R.string.add_to_fav)

private fun getFavoriteIcon(isFavorite: Boolean) =
    if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder

@CombinedPreviews
@Composable
fun CurrencyTablePreview() {
    CurConvTheme {
        CurrencyTable(
            items = listOf(
                CurrencyValue("F", "USD", 12.43, true),
                CurrencyValue("F", "TEST", 12555.43, false)
            ),
            {}
        )
    }
}
