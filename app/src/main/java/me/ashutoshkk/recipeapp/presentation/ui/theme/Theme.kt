package me.ashutoshkk.recipeapp.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class ColorScheme(
    val background: Color,
    val onBackground: Color,
    val text: Color,
    val text2: Color,
    val subText: Color,
    val textFieldBackground: Color,
    val heading: Color
)

private val colorScheme = ColorScheme(
    background = Color.White,
    onBackground = Color.Black,
    text = Color.Black,
    text2 = Color.White,
    subText = Color.Gray,
    textFieldBackground = Color(0xFFF2F7FD),
    heading = Color(0xFF171B21)
)

private val colors = lightColorScheme(
    primary = primary
)

private val LocalColorScheme = compositionLocalOf { colorScheme }

@Immutable
data class Paddings(
    val all: Dp = 20.dp,
    val around: Dp = 8.dp,
    val horizontal: Dp = 16.dp,
    val horizontalSmall: Dp = 12.dp,
    val vertical: Dp = 8.dp,
    val verticalInBetween: Dp = 16.dp,
    val verticalInBetweenSmall: Dp = 12.dp,
    val verticalInBetweenLarge: Dp = 24.dp,
)

private val LocalPaddings = staticCompositionLocalOf { Paddings() }


@Composable
fun RecipeAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
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

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.typography
}