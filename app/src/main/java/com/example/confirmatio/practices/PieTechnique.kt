package com.example.confirmatio.practices

import androidx.annotation.StringRes
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.compose.md_theme_dark_onSecondary
import com.example.compose.md_theme_dark_secondaryContainer
import com.example.compose.md_theme_light_secondaryContainer
import com.example.confirmatio.NavigateUpButton
import com.example.confirmatio.NavigationHost
import com.example.confirmatio.R
import com.example.confirmatio.Title
//import com.example.confirmatio.displaybackbutton


@Composable
fun PieTechnique() {
    Column(
        modifier = Modifier
            .background(color = Color.Transparent)
            .verticalScroll(ScrollState(0))
    ) {

        Title("Техника \"Пирог\"")

        headings("Описание")

        contentText(
            "Когда мы берем всю ответственность на себя, мы создаем излишнюю тревожность. " +
                    "Например, когда вы думаете, что на происходящее влияете на 100% и что-то идет не так, " +
                    "вам становится очень сильно тревожно. Вы требуете от себя больше, чем вы способны."
        )

        headings("Действия")

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
                .padding(horizontal = 15.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            itemsIndexed(
                listOf(
                    "Выберите\nситуацию,\nкоторая вас\nбеспокоит и\nначертите круг",
                    "Сколько факторов\nвлияет на происходящее\n(явно больше 2)?\nСколько процентов\nвлияния каждый из\nфакторов берет на себя?",
                    "Подумайте, сколько\nиз этих факторов вы\nможете реально\nконтролировать? На\nчто вы влияете, а на\nчто нет?"
                )
            ) { _, content ->
                actionCard(content)
            }
        }

        contentText(
            "Форс-мажоры, внешние обстоятельства, оценки, мнения, настроение, поведение " +
                    "других людей, случайности и т.д. находятся вне зоны вашего контроля. " +
                    "Контролировать вы можете исключительно свои действия, а вот результат ваших " +
                    "действий зависит от множества других факторов."
        )
        Spacer(modifier = Modifier.padding(5.dp))
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
fun headings(str: String) {
    Text(
        text = str,
        fontSize = 18.sp,
        modifier = Modifier
            .padding(horizontal = 15.dp, vertical = 5.dp),
        fontStyle = FontStyle.Italic,
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
fun actionCard(content: String) {
    val shape = RoundedCornerShape(20.dp)
    Box(
        modifier = Modifier
            .height(220.dp)
            //.fillMaxWidth()
            .widthIn(150.dp, 300.dp)
            .background(
                color = md_theme_dark_secondaryContainer,
                shape = shape
            )
            .padding(horizontal = 10.dp),
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = content,
            color = Color.White,
            fontSize = 20.sp, modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 15.dp)

        )
    }
}

