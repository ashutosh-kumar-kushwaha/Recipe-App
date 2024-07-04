package me.ashutoshkk.recipeapp.presentation.ui.search.components

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import me.ashutoshkk.recipeapp.presentation.ui.theme.RecipeTheme
import kotlin.math.abs
import kotlin.math.roundToInt

@Composable
fun Heading(
    @StringRes name: Int,
    onBackClick: () -> Unit,
    offset: IntOffset,
    onOffsetChange: (IntOffset) -> Unit
) {
    var direction by remember { mutableStateOf(Direction.NONE) }
    Column(modifier = Modifier
        .fillMaxWidth()
        .offset { offset }
        .pointerInput(Unit) {
            detectDragGestures(onDrag = { change, dragAmount ->
                change.consume()
                val (x, y) = dragAmount
                onOffsetChange(
                    offset + IntOffset(0,
                        y
                            .roundToInt()
                            .coerceAtLeast(0)
                    )
                )
                if (abs(x) > abs(y)) {
                    when {
                        x > 0 -> {
                            direction = Direction.RIGHT
                        }

                        x < 0 -> {
                            direction = Direction.LEFT
                        }
                    }
                } else {
                    when {
                        y > 0 -> {
                            direction = Direction.DOWN
                        }

                        y < 0 -> {
                            direction = Direction.UP
                        }
                    }
                }
            }, onDragEnd = {
                if (direction == Direction.DOWN) onBackClick()
                onOffsetChange(IntOffset(0, 0))
            })
        }) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(RecipeTheme.colorScheme.textFieldBackground)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = RecipeTheme.paddings.horizontal,
                    vertical = RecipeTheme.paddings.verticalSmall
                ), verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(name),
                style = RecipeTheme.typography.titleLarge,
                modifier = Modifier.weight(1f),
                color = RecipeTheme.colorScheme.text
            )
            IconButton(
                onClick = onBackClick
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

enum class Direction {
    UP, DOWN, LEFT, RIGHT, NONE
}