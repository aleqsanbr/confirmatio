package com.aleqsanbr.confirmatio.testsSystem

import android.content.Context
import android.graphics.Color
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.aleqsanbr.compose.md_theme_light_secondary
import com.aleqsanbr.confirmatio.CustomText
import com.aleqsanbr.confirmatio.CustomTextLSAS
import com.aleqsanbr.confirmatio.Title
import com.github.mikephil.charting.charts.HorizontalBarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter

class LSASTestAnalyzer (val test : Test, context : Context) {
    companion object {
        fun countFearPoints(test : Test) : Int {
            var sum = 0
            for(i in 1..48 step 2) {
                sum += test.qa_map[test.getQuestion(i)]!!
            }
            return sum
        }
        fun countAvoidancePoints(test : Test) : Int {
            var sum = 0
            for(i in 2..48 step 2) {
                sum += test.qa_map[test.getQuestion(i)]!!
            }
            return sum
        }

        @Composable
        fun getResults(total_points1: Int, total_points2 : Int, context: Context) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(ScrollState(0)),
                horizontalAlignment = Alignment.Start

            ) {
                Title(title = "Результаты теста",true)
                Text(
                    text = "Шкала оценивания от 0 до 144",
                    fontSize = 15.sp,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                val total = total_points1 + total_points2
                CustomTextLSAS(text = "Общие баллы социальной тревожности : **${total}**" +
                        " (${if(total <= 29) "Вы не страдаете социальной тревожностью" else if(total <= 49) "Легкая социальная тревожность" 
                        else if(total <= 64) "Умеренная социальная тревожность" else if(total <= 79) "Выраженная социальная тревожность"
                        else if(total <= 94) "Тяжелая социальная тревожность" else "Очень сильная социальная тревожность"})",
                    modifier = Modifier.padding(20.dp),fontSize = 20.sp )
                HorizontalScoreScaleWithTicks(
                    score = total,
                    maxScore = 144,
                    numberOfTicks = 6,
                    points = listOf(50,80),
                    padding = 20.dp,
                    actText = false,
                    barWidth = 250.dp
                )

                Text(text = "Баллы страха : ${total_points1} / 72",
                    modifier = Modifier.padding(20.dp, 40.dp, 20.dp, 0.dp ),fontSize = 20.sp )
                HorizontalScoreScaleWithTicks(
                    score = total_points1,
                    maxScore = 72,
                    numberOfTicks = 6,
                    points = listOf(25,41),
                    padding = 20.dp,
                    actText = false,
                    barWidth = 250.dp
                )

                Text(text = "Баллы избегания : ${total_points2} / 72",
                    modifier = Modifier.padding(20.dp, 40.dp, 20.dp, 0.dp),fontSize = 20.sp )
                HorizontalScoreScaleWithTicks(
                    score = total_points2,
                    maxScore = 72,
                    numberOfTicks = 6,
                    points = listOf(25,41),
                    padding = 20.dp,
                    actText = false,
                    barWidth = 250.dp
                )



            }

        }
        }

/*@Composable
fun getResults(total_points1: Int, total_points2 : Int, context: Context) {
    AndroidView(factory = { context ->
       HorizontalBarChart(context)
    },
        update = {barChart ->
            val entries = ArrayList<BarEntry>()
            val labels = ArrayList<String>()
            val colors = ArrayList<Int>()

            entries.add(BarEntry(0f, total_points1.toFloat()))
            entries.add(BarEntry(1f, total_points2.toFloat()))
            labels.add("Шкала страха")
            labels.add("Шкала избегания")
            colors.add(md_theme_light_secondary.toArgb())
            colors.add(md_theme_light_secondary.toArgb())


            val dataSet = BarDataSet(entries, "Bar Chart")
            dataSet.colors = colors


            val data = BarData(dataSet)
            data.setValueTextSize(5f)

            data.setBarWidth(9f);
            val xAxis = barChart.xAxis
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.setDrawGridLines(false) // hides vertical grid line
            xAxis.labelCount = labels.size // ensures all labels are visible
            xAxis.valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    val index = value.toInt()

                    if (index >= 0 && index < labels.size) {
                        return labels[index]
                    }
                    return ""

                }
            }

            barChart.data = data
            barChart.axisLeft.axisMinimum = 0f
            barChart.setDrawValueAboveBar(true)

            barChart.invalidate()
        })

}*/

}