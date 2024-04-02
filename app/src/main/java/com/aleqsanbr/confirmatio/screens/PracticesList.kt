package com.aleqsanbr.confirmatio.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aleqsanbr.compose.dark_CustomColor1Container
import com.aleqsanbr.compose.dark_CustomColor2Container
import com.aleqsanbr.compose.dark_CustomColor3Container
import com.aleqsanbr.confirmatio.R
import com.aleqsanbr.confirmatio.navigation.PracticeID


data class CardModel(val title:String, val description:String,
                     val image: Painter, val color:Color, val id:Int)

@Composable
fun ListColumn(cards : List<CardModel>,navigateToPractice: (Int) -> Unit) {
    val cardsList = remember { mutableStateListOf<CardModel>() }
    for(card in cards) {
        cardsList.add(card)
    }


    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),

        contentPadding = PaddingValues(vertical = 35.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        items(cardsList) { model ->
            CardItem(model = model, navigateToPractice)
        }

    }
}


@Composable
fun CardItem(model: CardModel, navigateToPractice: (Int) -> Unit) {
    //val context = LocalContext.current
    Card (
        modifier = Modifier
            .fillMaxSize()
            .clickable { navigateToPractice(model.id) }
            .padding(20.dp, 0.dp)
            .background(color = Color.Transparent, shape = RoundedCornerShape(20.dp))
            .padding(5.dp)
            .size(150.dp),
    ){
        Row(
            Modifier
                .fillMaxSize()
                .background(color = model.color),

            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Column(
                modifier = Modifier
                    .padding(20.dp, 20.dp)
                    .width(190.dp)
            ) {
                Text(text = model.title,
                    modifier = Modifier.padding(15.dp,0.dp, 5.dp, 0.dp),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onBackground,


                    )
                /* Text(text = model.description,
                     modifier = Modifier.padding(15.dp, 0.dp),
                     color = if(!isSystemInDarkTheme()) md_theme_light_onSurfaceVariant else md_theme_dark_onSurfaceVariant,
                     fontSize = 13.sp
                 )*/
            }
            Image(
                painter = model.image,
                contentDescription = "Question Icon",
                modifier = Modifier
                    .padding(5.dp, 0.dp, 35.dp, 0.dp)
                    .size(80.dp),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),

                )
        }
    }

}

@Composable
fun generateList(id : Int) : List<CardModel> {
    val color1 : Color = dark_CustomColor1Container
    val color2 : Color = dark_CustomColor2Container
    val color3 : Color = dark_CustomColor3Container


    if (id == 1) {
        return listOf(
            CardModel("Техника \"Пирог\"", "Description",
                painterResource(id = R.drawable.cake_icon), color2, PracticeID.Pie.id
            ),
            CardModel("Упражнение \"Волшебная палочка\"", "Description",
                painterResource(id = R.drawable.magic_wand_icon), color1, PracticeID.MagicWand.id
            ),
            CardModel("Упражнение \"Прогнозы\"", "Description",
                painterResource(id = R.drawable.time_icon), color3, PracticeID.Prognosis.id
            )
        )
    }
    else if (id == 2) {
        return listOf(
            CardModel("Мозговой штурм", "Description",
                painterResource(id = R.drawable.idea_icon), color2, PracticeID.BrainStorm.id
            ),
            CardModel("Техника \"Горячие мысли\"", "Description",
                painterResource(id = R.drawable.thoughts_icon), color3, PracticeID.HotThought.id
            ),
            CardModel("Ловушки сознания", "Description",
                painterResource(id = R.drawable.question_icon), color1, PracticeID.MindTrap.id
            )
        )
    }
    else {
        return listOf(
            CardModel("Ловушки сознания", "Description",
                painterResource(id = R.drawable.question_icon), color3, PracticeID.MindTrap.id
            ),
            CardModel("Упражнение \"Прогнозы\"", "Description",
                painterResource(id = R.drawable.time_icon), color1, PracticeID.Prognosis.id
            ),
            CardModel("Ведение записей", "Description",
                painterResource(id = R.drawable.note_icon),color2,PracticeID.WithNotes.id
            )
        )
    }
}

