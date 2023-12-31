package com.example.confirmatio.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import com.example.compose.CustomColor1
import com.example.compose.CustomColor2
import com.example.compose.CustomColor3
import com.example.compose.dark_CustomColor1
import com.example.compose.dark_CustomColor1Container
import com.example.compose.dark_CustomColor2
import com.example.compose.dark_CustomColor2Container
import com.example.compose.dark_CustomColor3
import com.example.compose.dark_CustomColor3Container
import com.example.compose.md_theme_dark_onBackground
import com.example.compose.md_theme_dark_onSurfaceVariant
import com.example.compose.md_theme_light_onBackground
import com.example.compose.md_theme_light_onSurfaceVariant
import com.example.compose.md_theme_light_secondaryContainer
import com.example.confirmatio.R


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
                    color = if(model.id ==2) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.outline,


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
                    .padding(5.dp, 0.dp, 25.dp, 0.dp)
                    .size(80.dp),
                colorFilter = ColorFilter.tint( if(model.id ==2) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.outline),

                )
        }
    }

}

@Composable
fun generateList(id : Int) : List<CardModel> {
    val color1 : Color
    val color2 : Color
    val color3 : Color
    if(!isSystemInDarkTheme()){
        color1 = CustomColor1
        color2 = CustomColor2
        color3 = CustomColor3
    }
    else {
        color1 = dark_CustomColor1Container
        color2 = dark_CustomColor2Container
        color3 = dark_CustomColor3Container
    }


    if (id == 1) {
        return listOf(
            CardModel("Техника \"Пирог\"", "Description",
                painterResource(id = R.drawable.cake_icon), color2, 2
            ),
            CardModel("\"У меня есь мысль, что...\"", "Description",
                painterResource(id = R.drawable.question_icon), color1, 1
            ),
            CardModel("Упражнение \"Прогнозы\"", "Description",
                painterResource(id = R.drawable.note_icon), color3, 3
            )
        )
    }
    else if (id == 2) {
        return listOf(
            CardModel("Мозговой штурм", "Description",
                painterResource(id = R.drawable.note_icon), color2, 4
            ),
            CardModel("Техника \"Горячие мысли\"", "Description",
                painterResource(id = R.drawable.cake_icon), color3, 5
            ),
            CardModel("Ловушки сознания", "Description",
                painterResource(id = R.drawable.question_icon), color1, 6
            )
        )
    }
    else {
        return listOf(
            CardModel("Ловушки сознания", "Description",
                painterResource(id = R.drawable.cake_icon), color3, 6
            ),
            CardModel("Упражнение \"Прогнозы\"", "Description",
                painterResource(id = R.drawable.question_icon), color1, 3
            ),
            CardModel("Ведение записей", "Description",
                painterResource(id = R.drawable.note_icon),color2,7
            )
        )
    }
}

