package com.example.confirmatio

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import java.io.Console

@Composable
fun Title(title : String, isCentered : Boolean = false) {
    Text(
        text = title,
        fontSize = 30.sp,
        fontWeight = FontWeight(500),
        lineHeight = 1.2.em,
        modifier = Modifier
            .padding(15.dp, 30.dp, 15.dp, 15.dp)
            .fillMaxWidth(1f),
        textAlign = if(isCentered) TextAlign.Center else TextAlign.Left,
    )
}

@Composable
fun SubTitle(title : String) {
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


@Composable
fun CustomText(text: String, modifier: Modifier = Modifier, fontSize : TextUnit) {

    var results: MatchResult? = boldRegex.find(text)

    val boldIndexes = mutableListOf<Pair<Int, Int>>()

    val keywords = mutableListOf<String>()

    var finalText = text

    var color : Color = Color.Black

    while (results != null) {
        keywords.add(results.value)
        results = results.next()
    }

    keywords.forEach { keyword ->
        val indexOf = finalText.indexOf(keyword)
        val newKeyWord = keyword.removeSurrounding("**")
        val value = newKeyWord.toIntOrNull()
        if(value != null) {
            color = if(value < 31) Color.Green else if(value < 45) (if(!isSystemInDarkTheme())Color.DarkGray else Color.Yellow) else Color.Red
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
fun CustomTextLSAS(text: String, modifier: Modifier = Modifier, fontSize : TextUnit) {

    var results: MatchResult? = boldRegex.find(text)

    val boldIndexes = mutableListOf<Pair<Int, Int>>()

    val keywords = mutableListOf<String>()

    var finalText = text

    var color : Color = Color.Black

    while (results != null) {
        keywords.add(results.value)
        results = results.next()
    }

    keywords.forEach { keyword ->
        val indexOf = finalText.indexOf(keyword)
        val newKeyWord = keyword.removeSurrounding("**")
        val value = newKeyWord.toIntOrNull()
        if(value != null) {
            color = if(value < 65) Color.Green else if(value < 96) (if(!isSystemInDarkTheme())Color.DarkGray else Color.Yellow) else Color.Red
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
fun CustomTextBAI(text: String, modifier: Modifier = Modifier, fontSize : TextUnit) {

    var results: MatchResult? = boldRegex.find(text)
    val boldIndexes = mutableListOf<Pair<Int, Int>>()
    val keywords = mutableListOf<String>()
    var finalText = text
    var color : Color = Color.Black
    while (results != null) {
        keywords.add(results.value)
        results = results.next()
    }

    keywords.forEach { keyword ->
        val indexOf = finalText.indexOf(keyword)
        val newKeyWord = keyword.removeSurrounding("**")
        val value = newKeyWord.toIntOrNull()
        if(value != null) {
            color = if(value <= 21) Color.Green else if(value <= 35) (if(!isSystemInDarkTheme())Color.DarkGray else Color.Yellow) else Color.Red
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
fun StartButton(text: String, onButtonClick: () -> Unit) {
    FilledTonalButton(
        onClick = {
                onButtonClick()
        },
        modifier = Modifier.padding(0.dp, 10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFBAEEC3),
            contentColor = Color.Black,
            disabledContainerColor = Color(0xFFCFD5D0),
            disabledContentColor = Color(0xFFA2AAA2)
        )
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
        )
    }
}

@Composable
fun NotImplemented(name: String) {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.padding(30.dp))
        CustomText(text = "Not implemented. $name", modifier = Modifier.padding(20.dp).align(
            Alignment.CenterHorizontally
        ), fontSize = 20.sp)
        Spacer(modifier = Modifier.padding(30.dp))
        Icon(
            imageVector = Icons.Outlined.Warning,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.size(150.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}