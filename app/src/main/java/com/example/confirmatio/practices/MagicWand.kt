package com.example.confirmatio.practices

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
import com.example.confirmatio.Title

@Composable
fun MagicWand() {
    Column(
    modifier = Modifier
    .background(color = Color.Transparent)
    .verticalScroll(ScrollState(0))
    ) {

        Title("Упражнение \"Волшебная палочка\"")

        headings("Описание")

        contentText(
            "Техника \"Волшебная палочка\" - это простой и эффективный способ справляться с тревогой, основанный на понятии, что эмоции, мысли и поведение взаимосвязаны. Суть метода заключается в том, чтобы начать действовать, представив, что у вас есть волшебная палочка, способная устранить вашу тревогу."
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
                    "Представьте, что волшебство существует:\nВизуализируйте, что у вас есть волшебная палочка, способная убрать вашу тревогу одним взмахом.",
                    "Задайте себе вопрос:\nПодумайте о том, что бы вы сделали, если бы ваша тревога исчезла. Какие действия вы бы предприняли?",
                    "Создайте список:\nСоставьте список простых и приятных вещей, которые вы любите делать, но делаете редко из-за тревоги или стресса.",
                    "Выберите действие:\nИз этого списка выберите самое простое и доступное действие, которое вы можете сделать уже сегодня или в ближайшее время.",
                    "Действуйте:\nНачните выполнять выбранное действие, представляя, что ваша волшебная палочка помогает вам освободиться от тревоги.",
                    "Оцените результат:\nПосле выполнения действия обратите внимание на изменение вашего настроения.",
                )
            ) { _, content ->
                actionCard(content)
            }
        }

        contentText(
            "Использование метода \"Волшебная палочка\" может привнести в вашу жизнь новую легкость и радость, освободив вас от оков тревоги и беспокойства. Не бойтесь применять эту технику в повседневной жизни, чтобы ощутить ее положительные изменения."
        )
        Spacer(modifier = Modifier.padding(5.dp))
    }
}