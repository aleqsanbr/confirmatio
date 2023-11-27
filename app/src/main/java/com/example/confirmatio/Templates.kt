package com.example.confirmatio

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

@Composable
fun Title(title: String, isCentered: Boolean = false) {
    Text(
        text = title,
        fontSize = 30.sp,
        fontWeight = FontWeight(500),
        lineHeight = 1.2.em,
        modifier = Modifier
            .padding(15.dp, 30.dp, 15.dp, 15.dp)
            .fillMaxWidth(1f),
        textAlign = if (isCentered) TextAlign.Center else TextAlign.Left,
    )
}

@Composable
fun SubTitle(title: String) {
    Text(
        text = title,
        fontSize = 20.sp,
        fontWeight = FontWeight(200),
        lineHeight = 1.2.em,
        modifier = Modifier
            .padding(horizontal = 15.dp, vertical = 0.dp)
            .fillMaxWidth(1f),
        textAlign = TextAlign.Left,
    )
}

@Composable
fun NavigateUpButton(navigateUp: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(10.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End
    ) {
        Button(
            onClick = navigateUp,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Gray.copy(alpha = 0.2f),
                contentColor = Color.Black.copy(alpha = 0.3f)
            ),
            modifier = Modifier
                .size(70.dp)
            //  .clip(CircleShape)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "get back"
            )
        }
    }
}


val boldRegex = Regex("(?<!\\*)\\*\\*(?!\\*).*?(?<!\\*)\\*\\*(?!\\*)")
val ItalicRegex = Regex("(?<!\\|)\\|\\|(?!\\|).*?(?<!\\|)\\|\\|(?!\\|)")

@Composable
fun CustomText(text: String, modifier: Modifier = Modifier, fontSize: TextUnit) {

    var results: MatchResult? = boldRegex.find(text)

    val boldIndexes = mutableListOf<Pair<Int, Int>>()

    val keywords = mutableListOf<String>()

    var finalText = text

    var color: Color = Color.Black

    while (results != null) {
        keywords.add(results.value)
        results = results.next()
    }

    keywords.forEach { keyword ->
        val indexOf = finalText.indexOf(keyword)
        val newKeyWord = keyword.removeSurrounding("**")
        val value = newKeyWord.toIntOrNull()
        if (value != null) {
            color =
                if (value < 31) Color.Green else if (value < 45) (if (!isSystemInDarkTheme()) Color.DarkGray else Color.Yellow) else Color.Red
        }
        finalText = finalText.replace(keyword, newKeyWord)
        boldIndexes.add(Pair(indexOf, indexOf + newKeyWord.length))
    }

    val annotatedString = buildAnnotatedString {
        append(finalText)

        // Add bold style to keywords that has to be bold
        boldIndexes.forEach {
            addStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Bold,
                    color = color,
                    fontSize = fontSize

                ),
                start = it.first,
                end = it.second
            )

        }
    }

    Text(
        modifier = modifier,
        fontSize = fontSize,
        text = annotatedString
    )
}

@Composable
fun CustomTextForArticles(text: String, modifier: Modifier = Modifier, fontSize: TextUnit) {

    var resultsBold: MatchResult? = boldRegex.find(text)
    val boldIndexes = mutableListOf<Pair<Int, Int>>()

    var resultsItalic: MatchResult? = ItalicRegex.find(text)
    val italicIndexes = mutableListOf<Pair<Int, Int>>()

    val keywords = mutableListOf<Pair<String, Int>>()

    var finalText = text

    var color: Color = if (!isSystemInDarkTheme()) Color.Black else Color.Red

    while (resultsBold != null) {
        keywords.add(Pair(resultsBold.value, 0))
        resultsBold = resultsBold.next()
    }

    while (resultsItalic != null) {
        keywords.add(Pair(resultsItalic.value, 1))
        resultsItalic = resultsItalic.next()
    }
    var indexOf1 = -1
    var indexOf2 = -1
    keywords.forEach { keyword ->

         if (keyword.second == 0)
             if (indexOf1 == -1) indexOf1 =  finalText.indexOf(keyword.first)
        else indexOf2 = finalText.indexOf(keyword.first)


        val newKeyWord = if (keyword.second == 0) keyword.first.removeSurrounding("**")
                         else keyword.first.removeSurrounding("||")

        finalText = finalText.replace(keyword.first, newKeyWord)
        if (keyword.second == 0) boldIndexes.add(Pair(indexOf1, indexOf1 + newKeyWord.length))
        else italicIndexes.add(Pair(indexOf2, indexOf2 + newKeyWord.length))
    }

    val annotatedString = buildAnnotatedString {
        append(finalText)

        italicIndexes.forEach {
            addStyle(
                style = SpanStyle(
                    fontStyle = FontStyle.Italic,
                    color = color,
                    fontSize = fontSize

                ),
                start = it.first,
                end = it.second
            )
        }

        boldIndexes.forEach {
            addStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Bold,
                    color = color,
                    fontSize = fontSize

                ),
                start = it.first,
                end = it.second
            )
        }

    }
    Text(
        modifier = modifier,
        fontSize = fontSize,
        text = annotatedString
    )
}

