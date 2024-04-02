package com.aleqsanbr.confirmatio.practices

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.aleqsanbr.confirmatio.Title

@Composable
fun HotThoughts() {
    Column(
        modifier = Modifier
            .background(color = Color.Transparent)
            .verticalScroll(ScrollState(0))
    ) {

        Title("Техника \"Горячие мысли\"")

        headings("Описание")

        contentText(
            "Автоматических суждений бывает много, но не все они одинаковы. Некоторые из них особенно беспокоят и расстраивают — отчасти потому, что больше других наполнены негативным смыслом. Такие оценочные суждения, усиливающие тревогу и депрессию, называют «горячими мыслями». Важно научиться их выявлять.\n" +
                    "\n" +
                    "Простой прием, который поможет определить «горячую мысль», называется «Двигаемся по нисходящей». Нужно взять конкретную мысль, которая вас тревожит, и проследить, что ее породило. Цель — добраться до лежащего в ее основе убеждения или страха."

        )

        headings("Действия для определения \"горячей мысли\"")

        headings("Сначала работаем с автоматической мыслью, которая вызывает тревогу и задаем вопросы:")

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
                .padding(horizontal = 15.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            itemsIndexed(
                listOf(
                    "К каким последствиям, по моему мнению, это может привести?",
                    "Что в соответствии с этой мыслью я должен делать?",
                    "Что, как я полагаю, меня ожидает?",
                    "Какие эмоции вызывает у меня эта мысль?",
                )
            ) { _, content ->
                actionCard(content)
            }
        }

        headings("Далее переходим к мысли, лежащей в основе предыдущей и задаем те же вопросы:")

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
                .padding(horizontal = 15.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            itemsIndexed(
                listOf(
                    "К каким последствиям, по моему мнению, это может привести?",
                    "Что в соответствии с этой мыслью я должен делать?",
                    "Что, как я полагаю, меня ожидает?",
                    "Какие эмоции вызывает у меня эта мысль?",
                )
            ) { _, content ->
                actionCard(content)
            }
        }

        contentText(
            "Этот метод позволяет проследить цепочку мыслей от конкретной горячей мысли до ее основы, которая часто связана с убеждениями или страхами. Разобравшись в корне горячих мыслей, можно начать работу над изменением отношения к ситуации и смягчением их воздействия на эмоциональное состояние. "
        )
        Spacer(modifier = Modifier.padding(5.dp))
    }
}