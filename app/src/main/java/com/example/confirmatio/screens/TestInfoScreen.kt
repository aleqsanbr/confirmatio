package com.example.confirmatio.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.example.confirmatio.NavigateUpButton
import com.example.confirmatio.Title

@Composable
fun TestInfoScreen(testId:Int, navigateToQuestions: (Int) ->  Unit, navigateUp: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(ScrollState(0)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Title("Шкала Спилберга")
        InfoCard(title = "Описание теста", "Шкала тревоги Спилбергера-Ханина (State-Trait Anxiety Inventory, STAI) является информативным способом самооценки как уровня тревожности в данный момент (реактивная тревожность, как состояние), так и личностной тревожности (как устойчивая характеристика человека).",icon = Icons.Outlined.Info)
        Spacer(modifier = Modifier.padding(15.dp))
        InfoCard(title = "Перед началом теста", "В первом блоке (20 вопросов):Прочитайте внимательно каждое из приведенных предложений.Выбирайте ответ в зависимости от того, как вы себя чувствуете в данный момент.\n" +
                "Во втором блоке (20 вопросов):Прочитайте внимательно каждое из приведенных предложений.Выбирайте ответ в зависимости от того, как вы себя чувствуете обычно.\n" +
                "Над вопросами долго не задумывайтесь, правильных и неправильных ответов здесь нет.",icon = Icons.Outlined.Star)
        FilledTonalButton(onClick = { navigateToQuestions(testId) },
           modifier = Modifier.padding(0.dp, 15.dp),
            colors = ButtonDefaults.buttonColors(containerColor =Color(0xFFBAEEC3),contentColor=Color.Black,
                   disabledContainerColor =Color(0xFFCFD5D0),  disabledContentColor=Color(0xFFA2AAA2))
        ) {
            Text(
                text = "Начать тест",
                textAlign = TextAlign.Center
            )
        }
    }
    NavigateUpButton(navigateUp)


}

@Composable
fun InfoCard(title:String,text:String, icon : ImageVector) {
    Column(
        modifier = Modifier
            .widthIn(200.dp, 400.dp)
            .padding(20.dp, 0.dp)
            .background(color = Color(0x96E8D1B6), shape = RoundedCornerShape(20.dp)),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color(0xFFF7D0A4),
                    shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp)
                ),
        ) {
            Image(
                imageVector = icon,
                contentDescription = "icon",
                modifier = Modifier
                    .padding(10.dp)
                    .size(25.dp)

            )
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically),

            ) {
                Text(
                    text = title,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 4.5.em
                )
            }


        }
        Text(
            text =text,
            modifier = Modifier.padding(10.dp),
            //textAlign = TextAlign.Justify
        )
    }

}