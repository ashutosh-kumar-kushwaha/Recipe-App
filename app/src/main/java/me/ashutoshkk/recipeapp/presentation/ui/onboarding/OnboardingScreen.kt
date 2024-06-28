package me.ashutoshkk.recipeapp.presentation.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.ashutoshkk.recipeapp.R
import me.ashutoshkk.recipeapp.presentation.Screen
import me.ashutoshkk.recipeapp.presentation.ui.theme.RecipeTheme

@Composable
fun OnboardingScreen(navigateTo: (String) -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            painter = painterResource(id = R.drawable.login_background_image),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillHeight
        )
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(RecipeTheme.paddings.allLarge)
        ) {
            Text(
                text = buildAnnotatedString {
                    append("Welcome to\n")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Black)) {
                        append("Rec")
                    }
                    append("ii")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Black)) {
                        append("p")
                    }
                    append("ii")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Black)) {
                        append("e")
                    }
                },
                fontSize = 48.sp,
                color = RecipeTheme.colorScheme.text2,
                fontStyle = FontStyle.Italic,
                lineHeight = 48.sp
            )
            Text(
                text = stringResource(id = R.string.please_signing_to_continue),
                fontSize = 14.sp,
                color = RecipeTheme.colorScheme.text2,
            )
            Spacer(modifier = Modifier.height(RecipeTheme.paddings.verticalInBetween))
            Button(
                onClick = {
                    navigateTo(Screen.Home.route)
                },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.coolicon),
                    modifier = Modifier
                        .height(18.dp)
                        .padding(end = 10.dp),
                    contentDescription = null)
                Text(
                    text = "Continue with google",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }
    }
}