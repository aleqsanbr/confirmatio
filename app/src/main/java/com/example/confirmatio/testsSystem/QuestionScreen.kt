package com.example.confirmatio.testsSystem

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import com.example.confirmatio.NavigateUpButton

@Composable
fun QuestionScreen(
    manager: TestManager,
    navigateUp: () -> Unit)
{
    manager.startTest()
    //var mutableState by remember { mutableStateOf(manager.cur_q) }
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        //horizontalAlignment = Alignment.CenterHorizontally,
        )
    {
        Box {
            QuestionCard(manager)
            NavigateUpButton(navigateUp)
        }

    }
}


@Composable
fun QuestionCard(
    manager: TestManager
) {
    var mutableState by remember { mutableStateOf(manager.cur_q) }
    val radioOptions = manager.getOptions()
    var selectedOption by remember { mutableStateOf(radioOptions[0])}
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
       Column(
           modifier = Modifier.width(IntrinsicSize.Min),
       ) {
           Text(
               modifier = Modifier.padding(vertical = 40.dp),
               text = "Вопрос ${mutableState}/${manager.amountOfQuestions()}",
               fontSize = 8.em,
               fontWeight = FontWeight.Bold
           )
           Column (
               modifier = Modifier
                   .widthIn(300.dp, 400.dp)
                   .padding(0.dp)
                   .background(Color(0xFFEEEBF4), RoundedCornerShape(20.dp))
                   .padding(15.dp),

               ) {
               Text(
                   modifier = Modifier.padding(vertical = 8.dp),
                   text = manager.getQuestion().q_text,
                   fontSize = 6.em,
                   fontWeight = FontWeight.Medium
               )


               Column(
                   modifier = Modifier.padding(vertical = 10.dp, horizontal = 0.dp),
                   verticalArrangement = Arrangement.spacedBy(10.dp),
                   horizontalAlignment = Alignment.Start

               ) {
                   radioOptions.forEach { option ->
                       Row(verticalAlignment = Alignment.CenterVertically) {
                           RadioButton(
                               selected = (option == selectedOption),
                               onClick = {
                                  // manager.saveAnswer(a_pos = radioOptions.indexOf(option))
                                   selectedOption = option
                               }
                           )
                           Text(
                               text = option,
                               style = MaterialTheme.typography.bodyMedium,
                               modifier = Modifier.padding(start = 8.dp)
                           )
                       }
                   }
               }
           }
           Row (
               modifier= Modifier.fillMaxWidth(),
               horizontalArrangement = Arrangement.SpaceBetween,
           ) {
               Button(
                   modifier = Modifier.padding(0.dp,15.dp,0.dp,0.dp),
                   onClick = {
                   if(manager.cur_q - 1 >= 1) {
                       manager.saveAnswer(a_pos = radioOptions.indexOf(selectedOption))
                       Log.d("DEBUG QUESTION", "++++++++++prev manager.saveAnswer() ${manager.getChoosedOption()}")
                       manager.cur_q -=1
                       mutableState -= 1
                       selectedOption = radioOptions[manager.getChoosedOption()]
                   }


               }) {
                   Text(
                       text = "Previous"
                   )
               }
               Button(
                   modifier = Modifier.padding(0.dp,15.dp,0.dp,0.dp),
                   onClick = {
                   if(manager.cur_q + 1 <= manager.amountOfQuestions()) {
                       manager.saveAnswer(a_pos = radioOptions.indexOf(selectedOption))
                       manager.updateProgress()
                       Log.d("DEBUG QUESTION", "++++++++++next manager.saveAnswer() ${manager.getChoosedOption()}")
                       manager.cur_q +=1
                       mutableState += 1
                       selectedOption = radioOptions[if (manager.getChoosedOption() == -1) 0 else manager.getChoosedOption()]
                   }


               }) {
                   Text(
                       text = "Next"
                   )
               }
           }
       }
    }
}

