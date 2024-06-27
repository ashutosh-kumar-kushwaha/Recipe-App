package me.ashutoshkk.recipeapp.presentation.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class ColorScheme(
    val background: Color,
    val onBackground: Color,
)

private val colorScheme = ColorScheme(
    background = Color.White,
    onBackground = Color.Black
)

private val colors = lightColorScheme(
    primary = primary
)

private val LocalColorScheme = compositionLocalOf { colorScheme }

@Immutable
data class Paddings(
    val around: Dp = 8.dp,
    val horizontal: Dp = 16.dp,
    val vertical: Dp = 8.dp,
    val verticalInBetween: Dp = 16.dp,
)

private val LocalPaddings = staticCompositionLocalOf { Paddings() }


@Composable
fun RecipeAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {

    CompositionLocalProvider {
        LocalColorScheme provides colorScheme
        LocalPaddings provides Paddings()
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}

object RecipeTheme {
    val colorScheme: ColorScheme
    @Composable
    @ReadOnlyComposable
    get() = LocalColorScheme.current

    val paddings: Paddings
    @Composable
    @ReadOnlyComposable
    get() = LocalPaddings.current
}