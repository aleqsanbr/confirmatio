package com.example.confirmatio.practices

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.md_theme_dark_onSecondary
import com.example.compose.md_theme_dark_secondaryContainer
import com.example.compose.md_theme_light_secondaryContainer
import com.example.confirmatio.Title

@Composable
fun MindTraps() {
    Column(
        modifier = Modifier
            .background(color = Color.Transparent)
            .verticalScroll(ScrollState(0))
    ) {

        Title("Техника \n\"Ловушки сознания\"")

        headings("Описание")

        contentText(
            "Ловушки сознания — это автоматическая тенденция мозга постоянно интерпретировать события определенным образом, " +
                    "часто искажая реальность и создавая негативные или деструктивные мысли и убеждения. " +
                    "Эти ловушки могут быть связаны с привычными способами мышления, стереотипами, переживаниями, страхами " +
                    "и ожиданиями, которые формируются в процессе жизни."
        )

        headings("Однако более распространенными являются следующие:")

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
                .padding(horizontal = 15.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            itemsIndexed(
                listOf(
                    "Делая поспешные выводы,\nвы переоцениваете\nвероятность плохих событий.\nНапример, вы думаете,\nчто ничего хорошего \nуже никогда не произойдет.",
                    "Вдобавок к поспешным\nвыводам вы можете чрезмерно\nсконцентрироваться\nна неверных выводах\nи не принимать во внимание\nфакты, которые им противоречат.",
                    "Вы представляете самый\nплохой исход событий.\nЕсли произойдет что-то\nплохое — а вы уверены,\nчто оно так и будет, —\nто все закончится хуже некуда."
                )
            ) { ind, content ->
                if (ind == 0) actionCard("Поспешные выводы",content);
                if (ind == 1) actionCard("Тоска и мрак",content);
                if (ind == 2) actionCard("Худший вариант",content);
            }
        }
        contentText(
            "Первый шаг к гибкому мышлению — научиться определять, что вы угодили в ловушку сознания, и в какую именно."
        )
        Spacer(modifier = Modifier.padding(5.dp))
        val selectedButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onBackground
        )
        val unselectedButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colorScheme.outlineVariant,
            contentColor = MaterialTheme.colorScheme.onSurface,
        )
        val textStyle = androidx.compose.material.MaterialTheme.typography.button.copy(color = MaterialTheme.colorScheme.onBackground)
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {

                },
                shape = RoundedCornerShape(20.dp),

                colors = if (!isSystemInDarkTheme()) selectedButtonColors else unselectedButtonColors
            ) {
                Text(
                    text = "Зафиксировать в дневнике\n\nловушку сознания",
                    modifier = Modifier
                        .padding(horizontal = 15.dp, vertical = 5.dp),
                    style = textStyle,
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                )
            }
        }
        Spacer(modifier = Modifier.padding(10.dp))
    }
}

@Composable
fun actionCard(title: String, content: String) {
    val shape = RoundedCornerShape(20.dp)
    Box(
        modifier = Modifier
            .height(220.dp)
            .fillMaxWidth()
            .background(
                color = if (!isSystemInDarkTheme()) md_theme_light_secondaryContainer else md_theme_dark_secondaryContainer,
                shape = shape
            )
            .padding(horizontal = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(
                text = title,
                fontSize = 25.sp,
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                color = if (!isSystemInDarkTheme()) md_theme_dark_onSecondary; else md_theme_light_secondaryContainer,
                fontWeight = FontWeight(900),
            )
            Text(
                text = content,
                color = if (!isSystemInDarkTheme()) Color.Black else Color.White,
                fontSize = 18.sp, modifier = Modifier
                    .padding(horizontal = 10.dp)
            )
            Spacer(modifier = Modifier.padding(10.dp))
        }
    }
}

