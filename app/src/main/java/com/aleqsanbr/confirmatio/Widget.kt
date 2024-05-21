package com.aleqsanbr.confirmatio


import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.ImageProvider
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.layout.width
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import com.aleqsanbr.compose.md_theme_dark_primary
import kotlinx.coroutines.delay
import kotlin.random.Random


class MyAppWidget : GlanceAppWidget() {

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            Content(context)
        }

    }

    @Composable
    fun Content(context : Context) {
        val text = remember { mutableStateOf(getLine(context))}
        Column(
            modifier = GlanceModifier.fillMaxSize().background(ImageProvider(R.drawable.rounded_corner_background)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                GlanceModifier.height(170.dp)
            ) {
                Text(
                    text = text.value,
                    style = TextStyle(fontSize = 17.sp),
                    modifier = GlanceModifier.padding(20.dp),
                )
            }

            val intent = Intent(context, MainActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(context, 0, intent,
                PendingIntent.FLAG_IMMUTABLE)

            Row {
                Column (
                    GlanceModifier.width(140.dp).padding(horizontal = 10.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalAlignment = Alignment.Bottom
                ){
                    Box(

                        modifier = GlanceModifier
                            .padding(10.dp)
                            .background(ImageProvider(BitmapFactory.decodeResource(context.resources, R.drawable.logo_icon)))
                            .size(40.dp)
                            .padding(10.dp)

                            .clickable(
                                //actionStartActivity<MainActivity>()
                                {pendingIntent.send()}
                            )
                    ) {}
                }
                Column (
                    GlanceModifier.width(140.dp).padding(horizontal = 10.dp),
                    horizontalAlignment = Alignment.End,
                    verticalAlignment = Alignment.Bottom
                ){
                    Box(
                        modifier = GlanceModifier
                            .padding(10.dp)
                            .background(ImageProvider(BitmapFactory.decodeResource(context.resources, R.drawable.refresh_icon)))
                            .size(40.dp)
                            .padding(10.dp)

                            .clickable( {text.value = getLine(context)})
                    ) {}
                }
            }


        }
        LaunchedEffect(Unit) {
            while (true) {
                text.value = getLine(context)
                delay(43200000)
            }
        }
    }



    fun getLine(context : Context) : String {
        val reader = context.assets.open("widget_phrases.txt").bufferedReader()
        var content = emptyList<String>()
        try {
            var line = reader.readLine()
            while (line != null) {
                content = content.plus(line)
                line = reader.readLine()
            }
        } finally {
            reader.close()
        }
        return  content.get(Math.abs(Random.nextInt()) % content.size)
    }
}

class MyAppWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = MyAppWidget()
}




