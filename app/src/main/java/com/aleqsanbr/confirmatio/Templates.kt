package com.aleqsanbr.confirmatio

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.aleqsanbr.compose.dark_CustomColor1Container
import com.aleqsanbr.compose.md_theme_dark_onBackground
import com.aleqsanbr.compose.md_theme_dark_secondaryContainer

@Composable
fun FriendlyNameOf(name: String) {
    val fr_name: String
    fr_name = when (name) {
        "help_now" -> "В моменте"
        "practices" -> "Практики"
        "info" -> "Информация"
        "diary" -> "Дневник"
        "settings" -> "Настройки"
        else -> "Confirmatio"
    }
    Text(text = fr_name)
}

@Composable
fun Article(navigateUp: () -> Unit, article: String, title: String) {
    val shape = RoundedCornerShape(30.dp)

    Column(
        modifier = Modifier
            .background(color = Color.Transparent)
            .verticalScroll(ScrollState(0))
            .padding(horizontal = 12.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy((-65).dp)
    )
    {
        Box(
            modifier = Modifier
                .height(130.dp)
                .width(LocalConfiguration.current.screenWidthDp.dp - 20.dp)
                .background(
                    dark_CustomColor1Container,
                    shape = shape
                )
                .border(3.dp, MaterialTheme.colorScheme.background, RoundedCornerShape(30.dp))
                .padding((10.dp)),
        )
        {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    lineHeight = 50.sp,
                    fontSize = 22.sp,
                    fontWeight = FontWeight(700),
                    color = md_theme_dark_onBackground,
                    modifier = Modifier
                        .padding((10.dp))
                )
            }
        }

        /*  Image(
                painter = painterResource(id = R.drawable.article_1_heading),
                contentDescription = "image1",
                modifier = Modifier
                  .fillMaxSize()
                    .clip(shape)
            )
*/
        Box(
            modifier = Modifier
                .background(
                    color = md_theme_dark_secondaryContainer,
                    shape = shape
                )
                .border(3.dp, MaterialTheme.colorScheme.background, RoundedCornerShape(30.dp))
        )
        {
            contentText(article)
        }
        //nameOfPractice("Что такое тревога?")

    }
}

@Composable
fun nameOfPractice(str: String) {
    Text(
        text = str,
        fontSize = 30.sp,
        modifier = Modifier
            .padding(horizontal = 15.dp, vertical = 5.dp)
            .fillMaxWidth(1f),
        textAlign = TextAlign.Center,
        fontWeight = FontWeight(700)
    )
}

@Composable
fun contentText(str: String) {
    Text(
        text = str,
        fontSize = 18.sp,
        modifier = Modifier
            .padding(horizontal = 15.dp, vertical = 5.dp),
    )
}



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
fun CustomTextBold(text: String, modifier: Modifier = Modifier, fontSize : TextUnit) {

    var results: MatchResult? = boldRegex.find(text)

    val boldIndexes = mutableListOf<Pair<Int, Int>>()

    val keywords = mutableListOf<String>()

    var finalText = text


    while (results != null) {
        keywords.add(results.value)
        results = results.next()
    }

    keywords.forEach { keyword ->
        val indexOf = finalText.indexOf(keyword)
        val newKeyWord = keyword.removeSurrounding("**")
        val value = newKeyWord.toIntOrNull()
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
                    //color = color,
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
fun ImportantTestNotes() {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(top = 30.dp)
    )
    {
        Title(title = "Важные замечания",true)
        CustomTextBold(text = "**Не является окончательной диагностикой:**\nХотя шкала помогает оценить уровень тревожности, окончательный диагноз должен ставить квалифицированный специалист, учитывая все аспекты своего здоровья.\n" +
                "\n**Контекст и другие факторы:**\nРезультаты теста должны интерпретироваться с учетом общей клинической картины и других возможных факторов, влияющих на ваше психическое состояние.\n" +
                "\n**Регулярность и изменения:**\nПоказатели тревожности могут изменяться со временем, поэтому повторное тестирование и мониторинг динамики могут быть полезными для более точного понимания своего состояния.",
            modifier = Modifier.padding(20.dp, 0.dp, 20.dp, 20.dp), fontSize = 20.sp)

    }
}
@Composable
fun CustomTextColor(text: String, modifier: Modifier = Modifier, fontSize : TextUnit) {

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
            color = if(value < 31) color_green else if(value < 46) color_yellow else color_red
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


fun CustomTextDiaryNote(text: String) : AnnotatedString {

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
        color = color_green
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
                    //fontSize = fontSize

                ),
                start = it.first,
                end = it.second
            )

        }
    }

    /*Text(
        modifier = modifier,
        fontSize = fontSize,
        text = annotatedString
    )*/
    return annotatedString
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
            color = if(value < 50) color_green else if(value < 80) color_yellow else color_red
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
val color_green = Color(red = 0x8F, green = 0xBC, blue = 0x8F, alpha = 0xFF)
val color_yellow = Color(red = 0xFF, green = 0xD7, blue = 0x00, alpha = 0xFF)
val color_red = Color(red = 0xB2, green = 0x22, blue = 0x22, alpha = 0xFF)

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
            color = if(value <= 21) color_green
            else if(value <= 35) color_yellow
            else color_red
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
        CustomTextColor(text = "Not implemented. $name", modifier = Modifier
            .padding(20.dp)
            .align(
                Alignment.CenterHorizontally
            ), fontSize = 20.sp)
        Spacer(modifier = Modifier.padding(30.dp))
        Icon(
            imageVector = Icons.Outlined.Warning,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .size(150.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}
