package com.aleqsanbr.confirmatio.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.em
import com.aleqsanbr.confirmatio.Article

fun JoinToString(list: List<String>): String {
    var result = ""
    for (i in list) {
        result += i
    }
    return result
}

@Composable
fun SingleArticle(
        articleId: Int,
        navigateUp: () -> Unit)
{
    val article1 = "\nВ психологической литературе сосуществуют два базовых термина, которые в ряде случаев используются как синонимы, но чаще разводятся в качестве самостоятельных понятий: тревога и тревожность. Различают тревожность как свойство личности, как относительно постоянную, относительно неизменную в течение жизни черту (личностная тревожность) и тревогу как отрицательное эмоциональное состояние, относительно длительное, связанное с изменением нервно-психической деятельности (ситуативная тревога). При этом определение тревоги как состояния является базовым, ключевым для определения тревожности как свойства личности: тревожность — это «склонность индивида к переживанию тревоги, характеризующаяся низким порогом возникновения реакции тревоги».\n" +
            "\nТревожность относится к уровню свойств личности и понимается как ожидание неблагополучного исхода в относительно нейтральных, не содержащих реальной угрозы ситуациях. Личностная тревожность — это относительно устойчивая индивидуальная характеристика человека, дающая представление о его склонности: а) воспринимать достаточно широкий круг ситуаций как угрожающих его самооценке, самоуважению и престижу; б) реагировать на эти ситуации проявлением состояния тревоги.\n" +
            "\nТермин «тревога» в большинстве случаев используется для описания неприятного по своей окраске эмоционального состояния, которое характеризуется субъективными ощущениями беспокойства, мрачных предчувствий, а с физиологической стороны — активацией автономной нервной системы. Это состояние возникает в ситуации неопределенной опасности, угрозы и содержит в себе ожидание негативной оценки, восприятия отрицательного к себе отношения или угрозы своему самоуважению\n" +
            "\nЦентральный элемент тревоги представляет собой ощущение угрозы. Состояние тревоги возникает, когда индивид воспринимает определенный раздражитель или ситуацию как несущие в себе актуально или потенциально элементы опасности, угрозы, вреда.\n" +
            "\nКак эмоция, направленная в будущее, функционально тревога не только предупреждает субъекта о возможной опасности, но и побуждает к поиску и конкретизации этой опасности, к активному исследованию окружающей действительности с установкой на выявление угрожающего объекта. В этом случае переживание тревоги реализуется в поведении беспокойством, суетливостью.\n" +
            "\nТревогу дифференцируют с переживанием страха. Понятия тревоги и страха рассматриваются как самостоятельные явления. В отличие от страха как реакции на конкретную угрозу тревога представляет собой генерализованный, или беспредметный страх. С другой стороны, страх определяется как опредмеченная тревога.\n"

    val article2 = "\nТревога - это состояние, которое может возникать по разным причинам и проявляться в различных формах. Вот некоторые из основных видов тревоги:\n" +
            "\n" +
            "1. Невротическая тревога: Это постоянный спутник человека, который возникает из-за внутренних конфликтов и неудовлетворенности собой. Примеры могут включать беспокойство по поводу внешности, социального статуса или личных отношений¹.\n" +
            "\n" +
            "2. Психотическая тревога: Этот тип тревоги связан с психическими расстройствами и может проявляться в виде паранойи или бредовых идей.\n" +
            "\n" +
            "3. Экзистенциальная тревога: Это глубокое беспокойство, связанное с осознанием собственной смертности и поиском смысла жизни.\n" +
            "\n" +
            "4. Адаптивная тревога: Она возникает, когда конкретная ситуация или явление несет в себе угрозу, и переживания человека не беспочвенны.\n" +
            "\n" +
            "5. Ситуационная тревога: Вызвана страхом перед неизвестностью или будущим.\n" +
            "\n" +
            "6. Социальная тревога: Возникает в ситуациях, где человеку приходится взаимодействовать с другими людьми.\n" +
            "\n" +
            "7. Личностная тревога: Черта характера, склонность к частым беспокойствам.\n" +
            "\n" +
            "Каждый из этих видов тревоги имеет свои особенности и может требовать различных подходов к лечению и справлению. Понимание причин и механизмов тревоги помогает в выборе наиболее эффективных методов терапии и самопомощи. Если вы чувствуете, что тревога мешает вашей повседневной жизни, рекомендуется обратиться за помощью к специалисту.\n" +
            "\n"
    val article3 = "\nСправиться с тревогой можно разными способами, и вот несколько методов, которые могут помочь:\n" +
            "\n" +
            "1. Изменения в образе жизни: Уменьшите потребление кофеина и сахара, увеличьте физическую активность, обеспечьте себе достаточный сон и здоровое питание.\n" +
            "\n" +
            "2. Психологические тактики: Практикуйте техники релаксации, такие как дыхательные упражнения, медитация или йога.\n" +
            "\n" +
            "3. Социальная поддержка: Общайтесь с друзьями и семьей, не изолируйте себя.\n" +
            "\n" +
            "4. Арт-терапия: Занимайтесь творчеством, например, рисованием, музыкой или рукоделием, чтобы выразить свои чувства и снизить уровень стресса.\n" +
            "\n" +
            "5. Профессиональная помощь: Если тревога мешает вашей повседневной жизни, важно обратиться за помощью к психологу или психотерапевту.\n" +
            "\n" +
            "Это лишь некоторые из способов, и каждый человек может найти то, что подходит именно ему. Главное — не бояться просить помощи и экспериментировать с разными методами, чтобы найти наиболее эффективные для себя.\n" +
            "\n"
    when(articleId) {
        0 -> Article(navigateUp, article1, "Что такое тревога?")
        1 -> Article(navigateUp, article2, "Виды тревоги")
        2 -> Article(navigateUp, article3, "Как бороться с тревогой?")
        else ->
        {
            Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
            )
            {
                Text(text = "Данная статья еще не реализована!",
                        fontSize = 4.em)
                Button(onClick = navigateUp) {
                    Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "get back"
                    )
                }
            }
        }
    }
}