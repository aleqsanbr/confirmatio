package com.example.confirmatio.articles

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.tooling.data.EmptyGroup.box
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.md_theme_dark_secondaryContainer
import com.example.compose.md_theme_light_secondaryContainer
import com.example.confirmatio.NavigateUpButton
import com.example.confirmatio.R
import com.example.confirmatio.practices.nameOfPractice


@Composable
fun WhatIsAnAlarm(navigateUp: () -> Unit) {
    val shape = RoundedCornerShape(30.dp)

    Column(
        modifier = Modifier
            .background(color = Color.Transparent)
            .verticalScroll(ScrollState(0))
            .padding(horizontal = 15.dp, vertical = 15.dp),
        verticalArrangement = Arrangement.spacedBy((-65).dp)
    )
    {
      //  Spacer(modifier = Modifier.padding(30.dp))

          Image(
                painter = painterResource(id = R.drawable.article_1_heading),
                contentDescription = "image1",
                modifier = Modifier
                  .fillMaxSize()
                    .clip(shape)
            )

        Box(
            modifier = Modifier
                .background(
                    color = if (!isSystemInDarkTheme()) md_theme_light_secondaryContainer else md_theme_dark_secondaryContainer,
                    shape = shape
                )
        )
        {
            contentText(
                "\nВ психологической литературе сосуществуют два базовых термина, которые в ряде случаев используются как синонимы, но чаще разводятся в качестве самостоятельных понятий: тревога и тревожность. Различают тревожность как свойство личности, как относительно постоянную, относительно неизменную в течение жизни черту (личностная тревожность) и тревогу как отрицательное эмоциональное состояние, относительно длительное, связанное с изменением нервно-психической деятельности (ситуативная тревога). При этом определение тревоги как состояния является базовым, ключевым для определения тревожности как свойства личности: тревожность — это «склонность индивида к переживанию тревоги, характеризующаяся низким порогом возникновения реакции тревоги» \n" +
                        "\n" +
                        "Тревожность относится к уровню свойств личности и понимается как ожидание неблагополучного исхода в относительно нейтральных, не содержащих реальной угрозы ситуациях. Личностная тревожность — это относительно устойчивая индивидуальная характеристика человека, дающая представление о его склонности: а) воспринимать достаточно широкий круг ситуаций как угрожающих его самооценке, самоуважению и престижу; б) реагировать на эти ситуации проявлением состояния тревоги\n" +
                        "\n" +
                        "Термин «тревога» в большинстве случаев используется для описания неприятного по своей окраске эмоционального состояния, которое характеризуется субъективными ощущениями беспокойства, мрачных предчувствий, а с физиологической стороны — активацией автономной нервной системы. Это состояние, по мнению автора, возникает в ситуации неопределенной опасности, угрозы и содержит в себе ожидание негативной оценки, восприятия отрицательного к себе отношения или угрозы своему самоуважению\n" +
                        "\n" +
                        "Центральный элемент тревоги представляет собой ощущение угрозы. Состояние тревоги возникает, когда индивид воспринимает определенный раздражитель или ситуацию как несущие в себе актуально или потенциально элементы опасности, угрозы, вреда» \n" +
                        "\n" +
                        "Как эмоция, направленная в будущее, функционально тревога не только предупреждает субъекта о возможной опасности, но и побуждает к поиску и конкретизации этой опасности, к активному исследованию окружающей действительности с установкой на выявление угрожающего объекта. В этом случае переживание тревоги реализуется в поведении беспокойством, суетливостью\n" +
                        "\n" +
                        "Тревогу дифференцируют с переживанием страха. Понятия тревоги и страха рассматриваются как самостоятельные явления. В отличие от страха как реакции на конкретную угрозу тревога представляет собой генерализованный, диффузный или беспредметный страх. С другой стороны, страх определяется как опредмеченная тревога.\n"
            )
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

