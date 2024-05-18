package com.aleqsanbr.confirmatio.testsSystem

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aleqsanbr.confirmatio.CustomText
import com.aleqsanbr.confirmatio.Title

val LOW_ANX = "Для них характерно ярко выраженное спокойствие. Они не всегда склонны воспринимать угрозу своему престижу, самооценке в самом широком диапазоне ситуаций, даже когда она реально существует. Возникновение состояния тревоги у них может наблюдаться лишь в особо важных и личностно значимых ситуациях (экзамен, стрессовые ситуации, реальная угроза семейному положению и др.). В личностном плане такие люди спокойны, считают, что лично у них нет поводов и причин волноваться за свою жизнь, репутацию, поведение и деятельность. Вероятность возникновения конфликтов, срывов, аффективных вспышек у таких людей крайне мала."
val HIGH_ANX = "Для них характерна склонность в диапазоне ситуаций воспринимать любое проявление качеств их личности, любую заинтересованность в них как возможную угрозу их престижу, самооценке. Усложненные ситуации они склонны воспринимать как угрожающие, катастрофические. Соответственно восприятию проявляется и сила эмоциональной реакции. Такие люди вспыльчивы, раздражительны и находятся в постоянной готовности к конфликту и готовности к защите, даже если в этом объективно нет надобности. Для них, как правило, характерна неадекватная реакция на замечания, советы и просьбы. Особенно велика вероятность нервных срывов, аффективных реакций в ситуациях, где речь идет об их компетенции в тех или иных вопросах, их престиже, самооценке, их отношении. Излишнее подчеркивание результатов их деятельности или способов поведения, как в лучшую, так и в худшую сторону, категоричный по отношению к ним тон или тон, выражающий сомнение, – всё это неизбежно ведет к срывам, конфликтам, созданию различного рода психологических барьеров, препятствующих эффективному взаимодействию с такими людьми. К высоко тревожным людям опасно предъявлять категорично высокие требования, даже в ситуациях, когда объективно они выполнимы для них. Неадекватная реакция на такие требования может задержать, а то и вообще отодвинуть на долгое время выполнение требуемого результата."

class STAITestAnalyzer(val test : Test) {


    @Composable
    fun getResults() {
        var total_points1 = Companion.countTotalPointsFirstHalf(test)
        var total_points2 = Companion.countTotalPointsSecondHalf(test)

        Column() {
            Text(
                fontSize = 20.sp,
                text = "Баллы ситуативной тревожности : ${total_points1}" +
                        "(${if(total_points1 <= 30) "низкая" else if(total_points1 <= 45) "умеренная" else "высокая"})"
            )
            Text(
                fontSize = 20.sp,
                text = "Баллы личностной тревожности : ${total_points2}" +
                        "(${if(total_points2 <= 30) "низкая" else if(total_points2 <= 45) "умеренная" else "высокая"})"
            )
            Text(
                fontSize = 20.sp,
                text = "Психологический портрет высокотревожных лиц\n" + HIGH_ANX
            )
        }

    }

    companion object {
        fun countTotalPointsFirstHalf(test : Test) : Int
        {
            var sum = 35
            for(i in listOf<Int>(1,2,5,8,10,11,15,16,19,20)) {
                sum -= 1+test.qa_map[test.getQuestion(i)]!!
            }
            for(i in listOf<Int>(3,4,6,7,9,12,13,14,17,18)) {
                sum += test.qa_map[test.getQuestion(i)]!!
            }
            return sum
        }

        fun countTotalPointsSecondHalf(test : Test) : Int
        {
            var sum = 35
            for(i in listOf<Int>(1,6,7,10,16,19)) {
                sum -= 1+ test.qa_map[test.getQuestion(i)]!!
            }
            for(i in listOf<Int>(2,3,4,5,8,9,11,12,13,14,15,17,18,20)) {
                sum += test.qa_map[test.getQuestion(i)]!!
            }
            return sum
        }

        @Composable
        fun getResults(total_points1: Int, total_points2 : Int) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(ScrollState(0)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Title(title = "Результаты теста",true)
                Text(
                    text = "Шкала оценивания от 20 до 80",
                    fontSize = 15.sp,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                CustomText(
                    text = "Баллы ситуативной тревожности : **${total_points1}**" +
                        " (${if(total_points1 <= 30) "низкая" else if(total_points1 <= 45) "умеренная" else "высокая"})",
                    modifier = Modifier.padding(20.dp), fontSize = 20.sp )
                HorizontalScoreScaleWithTicks(
                    score = total_points1,
                    maxScore = 80,
                    numberOfTicks = 6,
                    points = listOf(31,46),
                    padding = 20.dp,
                    actText = false,
                    barWidth = 250.dp
                )

                CustomText(
                    text = "Баллы личностной тревожности : **${total_points2}**" +
                        " (${if(total_points2 <= 30) "низкая" else if(total_points2 <= 45) "умеренная" else "высокая"})",
                    modifier = Modifier.padding(20.dp), fontSize = 20.sp )
                HorizontalScoreScaleWithTicks(
                    score = total_points2,
                    maxScore = 80,
                    numberOfTicks = 6,
                    points = listOf(31,46),
                    padding = 20.dp,
                    actText = false,
                    barWidth = 250.dp
                )

                if((total_points1 + total_points2) / 2 < 31) {
                    Text(
                        modifier = Modifier.padding(20.dp, 20.dp, 20.dp, 0.dp),
                        fontSize = 20.sp,
                        text = "Психологический портрет низкотревожных лиц\n",
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        modifier = Modifier.padding(20.dp, 0.dp, 20.dp, 20.dp),
                        fontSize = 20.sp,
                        text = LOW_ANX
                    )
                }
                else if ((total_points1 + total_points2) / 2 > 44) {
                    Text(
                        modifier = Modifier.padding(20.dp, 20.dp, 20.dp, 0.dp),
                        fontSize = 20.sp,
                        text = "Психологический портрет высокотревожных лиц\n",
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        modifier = Modifier.padding(20.dp, 0.dp, 20.dp, 20.dp),
                        fontSize = 20.sp,
                        text = HIGH_ANX
                    )
                }


            }

        }
    }

}