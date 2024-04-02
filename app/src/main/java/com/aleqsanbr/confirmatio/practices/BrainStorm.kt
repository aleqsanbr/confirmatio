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
fun BrainStorm() {
    Column(
        modifier = Modifier
            .background(color = Color.Transparent)
            .verticalScroll(ScrollState(0))
    ) {

        Title("Техника \"Мозговой штурм\"")

        headings("Описание")

        contentText(
            "Метод Д. Скотта, также известный как \"Мозговой Штурм\", представляет собой эффективную и креативную технику борьбы с тревогой, стимулируя процессы мышления и способствуя развитию творческого потенциала. Этот метод основан на принципах активного участия вашего ума в решении проблемы или преодолении тревожных мыслей."

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
                    "Запишите идеи:\nЗафиксируйте все мысли и решения, не задумываясь о их правильности.",
                    "Оцените идеи:\nПроанализируйте каждое решение по 5-балльной шкале.",
                    "Выберите лучшие решения:\nОтберите наивысшеоцененные идеи, наиболее вероятные к успеху.",
                    "Планируйте действия:\nРазработайте план действий на основе выбранных решений.",
                    "Действуйте:\nПрименяйте выбранные решения на практике и последовательно двигайтесь к цели."
                )
            ) { _, content ->
                actionCard(content)
            }
        }

        contentText(
            "Помните, что метод Д. Скотта – это не просто инструмент для решения проблем и борьбы с тревогой, это подход к жизни, способствующий вашему личностному росту и развитию. Применяйте его не только в сложных ситуациях, но и в повседневной жизни, чтобы постоянно развивать свой творческий потенциал и находить эффективные способы достижения поставленных целей. Помните, что каждая неудача – это возможность для учебы, а каждое преодоление – шаг к вашему личному и профессиональному росту. Верьте в себя, доверяйте своему уму и не бойтесь идти за своими мечтами. "
        )
        Spacer(modifier = Modifier.padding(5.dp))
    }
}