package com.example.confirmatio.testsSystem

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.compose.md_theme_dark_secondary
import com.example.compose.md_theme_dark_secondaryContainer
import com.example.compose.md_theme_light_onSecondary
import com.example.compose.md_theme_light_secondary
import com.example.compose.md_theme_light_secondaryContainer
import com.example.confirmatio.NavigateUpButton
import com.example.confirmatio.R
import kotlinx.coroutines.launch

@Composable
fun QuestionScreen(
    manager: TestManager,
    navigateUp: () -> Unit,
    navigateToResults : () -> Unit,
    putInts : (List<Int>) ->Unit
)
{
    //manager.startTest()
    //var mutableState by remember { mutableStateOf(manager.cur_q) }
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        //horizontalAlignment = Alignment.CenterHorizontally,
        )
    {
        QuestionCard(manager, navigateToResults, putInts)

    }
}


@Composable
fun QuestionCard(
    manager: TestManager,
    navigateToResults : () -> Unit,
    putInts : (List<Int>) ->Unit
) {
    var managerState   by remember { mutableStateOf(manager) }
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
           modifier = Modifier.fillMaxWidth().padding(horizontal = 25.dp),
       ) {
           Text(
               modifier = Modifier.padding(0.dp, 30.dp, 0.dp, 0.dp),
               text = "Вопрос ${mutableState}/${manager.amountOfQuestions()}",
               fontSize = 35.sp,
               fontWeight = FontWeight.Bold
           )
           LinearProgressIndicator(
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(0.dp, 20.dp)
                   .heightIn(10.dp, 20.dp),
               color = if(!isSystemInDarkTheme()) md_theme_light_secondary else md_theme_dark_secondary,
               progress = manager.getProgress()
           )
           Column (
               modifier = Modifier
                   .widthIn(300.dp, 550.dp)
                   //.fillMaxWidth()
                   .heightIn(350.dp, 650.dp)
                   .background(if(!isSystemInDarkTheme()) md_theme_light_secondaryContainer else md_theme_dark_secondaryContainer, RoundedCornerShape(20.dp))
                   .padding(15.dp),
               ) {
               Text(
                   modifier = Modifier.padding(10.dp, 7.dp,10.dp, 5.dp),
                   text = manager.getQuestion().q_text,
                   fontSize = 22.sp,
                   fontWeight = FontWeight.Medium,
               )


               Column(
                   modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp, horizontal = 0.dp),
                   verticalArrangement = Arrangement.spacedBy(5.dp),
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
                               modifier = Modifier.padding(start = 6.dp),
                               fontSize = 17.sp
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
                   enabled = manager.cur_q > 1,
                   modifier = Modifier
                       .padding(0.dp, 15.dp, 0.dp, 5.dp)
                       //.width(IntrinsicSize.Min)
                       .width(130.dp),
                       //.padding(0.dp, 15.dp, 0.dp, 10.dp),
                   colors = ButtonDefaults.buttonColors(containerColor = if(!isSystemInDarkTheme()) md_theme_light_secondary else md_theme_dark_secondaryContainer, contentColor = md_theme_light_onSecondary),
                   onClick = {
                   if(manager.cur_q - 1 >= 1) {
                       manager.saveAnswer(a_pos = radioOptions.indexOf(selectedOption))
                       Log.d("DEBUG QUESTION", "++++++++++prev manager.saveAnswer() ${manager.getChoosedOption()}")
                       manager.cur_q -=1
                       mutableState -= 1
                       selectedOption = radioOptions[manager.getChoosedOption()]
                   }


               }) {
                   Row (
                       modifier = Modifier.fillMaxWidth(),
                       horizontalArrangement = Arrangement.Start,
                       verticalAlignment = Alignment.CenterVertically
                   ) {
                       Image(
                           imageVector = Icons.Outlined.ArrowBack,
                           contentDescription = "Previous",
                           modifier = Modifier.size(20.dp),
                           colorFilter = ColorFilter.tint(Color.White)
                       )
                       Text(
                           text = "Назад",
                           color = Color.White,
                           fontSize = 17.sp
                       )
                   }

               }
               Button(
                   modifier = Modifier
                       //.width(IntrinsicSize.Min)
                       .padding(0.dp, 15.dp, 0.dp, 5.dp)
                       .width(130.dp),

                   colors = ButtonDefaults.buttonColors(containerColor =if(!isSystemInDarkTheme()) md_theme_light_secondary else md_theme_dark_secondaryContainer, contentColor = md_theme_light_onSecondary),
                   onClick = {
                   if(manager.cur_q + 1 <= manager.amountOfQuestions()) {
                       manager.saveAnswer(a_pos = radioOptions.indexOf(selectedOption))
                       manager.updateProgress()
                       Log.d("DEBUG QUESTION", "++++++++++next manager.saveAnswer() ${manager.getChoosedOption()}")
                       manager.cur_q +=1
                       mutableState += 1
                       selectedOption = radioOptions[if (manager.getChoosedOption() == -1) 0 else manager.getChoosedOption()]
                   }
                   else {
                      /* val lst = ArrayList<QAPair>()
                       var j = 1
                       for(i in manager.test.qa_map.keys) {
                           lst.add(QAPair(j, manager.test.qa_map[i]!!))
                           j+=1
                       }
                       val qalst : QAPairList? = QAPairList(lst)

                       Log.d("DEBUG RESULTS", "+++++++++ lst size${lst.size}")
                       if (qalst != null) {
                           Log.d("DEBUG RESULTS", "+++++++++ qapair lst size${qalst.list?.size}")
                       }

                       navController.currentBackStackEntry?.arguments?.putParcelable("qa",qalst)*/

                       val param1 = STAITestAnalyzer.countTotalPointsFirstHalf(manager.test)
                       val param2 = STAITestAnalyzer.countTotalPointsSecondHalf(manager.test)
//                       Log.d("DEBUG RESULTS", "+++++++++ qs param1 = ${param1}; param2 = ${param1}")
//
//                       navController.currentBackStackEntry?.arguments?.putInt("param1", param1)
//                       navController.currentBackStackEntry?.arguments?.putInt("param2", param2)
                       putInts(listOf(TestID.STAI.id, param1, param2))
                       navigateToResults()

                   }


               }) {

                   Row (
                       modifier = Modifier.fillMaxWidth(),
                       horizontalArrangement = Arrangement.End,
                       verticalAlignment = Alignment.CenterVertically
                   ) {
                       if(manager.cur_q < manager.amountOfQuestions()) {
                           Text(
                               text = "Вперед",
                               color = Color.White,
                               fontSize = 17.sp
                           )
                       }
                       else {
                           Text(
                               text = "Итоги",
                               color = Color.White,
                               fontSize = 17.sp
                           )
                       }

                       Image(
                           imageVector = Icons.Outlined.ArrowForward,
                           contentDescription = "Next",
                           modifier = Modifier.size(20.dp),
                           colorFilter = ColorFilter.tint(Color.White)
                       )
                   }
               }
           }
       }
    }
}

