package ru.frozenpriest.curconv.ui.common

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Device
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "small font",
    group = "font scales",
    fontScale = 0.5f,
    showBackground = true
)
@Preview(
    name = "large font",
    group = "font scales",
    fontScale = 1.5f,
    showBackground = true

)
annotation class FontScalePreviews

@Preview(
    name = "dark theme",
    group = "themes",
    uiMode = UI_MODE_NIGHT_YES,
    showBackground = true
)
@Preview(
    name = "light theme",
    group = "themes",
    uiMode = UI_MODE_NIGHT_NO,
    showBackground = true
)
@FontScalePreviews
@Device
annotation class CombinedPreviews
