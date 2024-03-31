package com.example.confirmatio.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.compose.md_theme_dark_onSecondaryContainer
import com.example.compose.md_theme_dark_secondaryContainer
import com.example.confirmatio.Title
import com.example.confirmatio.testsSystem.TestLoader

@Composable
fun TestInfoScreen(testId:Int, navigateToQuestions: (Int) ->  Unit, navigateUp: () -> Unit) {
    when(testId) {
        1,2,3 -> ScreenContent(testId, navigateToQuestions, navigateUp)

        else ->
            Column(
                modifier = Modifier.fillMaxSize().padding(horizontal = 25.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            )
            {
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    textAlign = TextAlign.Center,
                    text = "Данный тест еще не реализован!",
                    fontSize = 23.sp
                )
                Button(onClick = navigateUp,)
                {Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "get back"
                )
                }
            }


    }


}

@Composable
fun ScreenContent(testId:Int, navigateToQuestions: (Int) ->  Unit, navigateUp: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(ScrollState(0)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Title(TestLoader.getTestName(testId, LocalContext.current), true)
        InfoCard(title = "Описание теста", TestLoader.getInfo(testId, LocalContext.current),icon = Icons.Outlined.Info)
        Spacer(modifier = Modifier.padding(15.dp))
        InfoCard(title = "Перед началом теста", TestLoader.getTestDescription(testId, LocalContext.current),icon = Icons.Outlined.Star)
        FilledTonalButton(onClick = { navigateToQuestions(testId) },
            modifier = Modifier.width((LocalConfiguration.current.screenWidthDp/2).dp).padding(5.dp,20.dp),
        ) {
            Text(
                text = "Начать тест",
                textAlign = TextAlign.Center,
                fontSize = 15.sp
            )
        }
    }
}
@Composable
fun InfoCard(title:String,text:String, icon : ImageVector) {
    Column(
        modifier = Modifier
            .widthIn(200.dp, 400.dp)
            .padding(20.dp, 0.dp)
            .background(
                color = md_theme_dark_secondaryContainer,
                shape = RoundedCornerShape(20.dp)
            ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color(0x44B7CCB8),
                    shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp)
                ),
        ) {
            Image(
                imageVector = icon,
                contentDescription = "icon",
                modifier = Modifier
                    .padding(10.dp)
                    .size(25.dp),
                colorFilter = ColorFilter.tint(md_theme_dark_onSecondaryContainer)

            )
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically),

            ) {
                Text(
                    text = title,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,

                )
            }


        }
        Text(
            text =text,
            modifier = Modifier.padding(10.dp),
            fontSize = 15.sp,
            style = LocalTextStyle.current.merge(
                TextStyle(
                    lineHeight = 1.5.em,
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    ),
                    lineHeightStyle = LineHeightStyle(
                        alignment = LineHeightStyle.Alignment.Center,
                        trim = LineHeightStyle.Trim.None
                    )
                )
            )

        )
    }

}