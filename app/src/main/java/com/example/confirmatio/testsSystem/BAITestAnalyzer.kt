package com.example.confirmatio.testsSystem

import android.content.Context
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.confirmatio.CustomTextBAI
import com.example.confirmatio.Title

class BAITestAnalyzer {
    companion object {

        fun countPoints(test : Test) : Int {
            var sum = 0
            for(i in 1..21) {
                sum += test.qa_map[test.getQuestion(i)]!!
            }
            return sum
        }

        @Composable
        fun getResults(total: Int) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(ScrollState(0)),
                horizontalAlignment = Alignment.Start
            ) {
                Title(title = "Результаты теста",true)
                Text(
                    text = "Шкала оценивания от 0 до 63",
                    fontSize = 15.sp,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                CustomTextBAI(text = "Общие баллы : **${total}**" +
                        " (${if(total <= 9) "Данное значение свидетельствует об отсутствии тревоги" 
                        else if(total <= 21) "Данное значение свидетельствует о незначительном уровне тревоги"
                        else if(total <= 35) "Данное значение соответствует средней выраженности тревоги. " 
                        else "Данное значение свидетельствует об очень высокой тревоге."})",
                    modifier = Modifier.padding(20.dp),fontSize = 20.sp )





            }

        }
    }
}