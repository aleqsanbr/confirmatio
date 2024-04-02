package com.example.confirmatio.screens

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Matrix
import android.graphics.RectF
import android.net.Uri
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.PathParser
import androidx.media3.extractor.text.Subtitle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose.md_theme_dark_onSecondary
import com.example.compose.md_theme_dark_onSurface
import com.example.confirmatio.CustomText
import com.example.confirmatio.NotImplemented
import com.example.confirmatio.R
import com.example.confirmatio.SubTitle
import com.example.confirmatio.Title
import java.time.Instant
import java.util.Date
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat

@Composable
fun SettingsButton(icon: ImageVector, title: String, subtitle: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clickable(onClick = onClick)
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(17.dp, Alignment.Start),
            modifier = Modifier
                .clip(
                    RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 0.dp,
                        bottomStart = 0.dp,
                        bottomEnd = 0.dp
                    )
                )
                .background(Color.Transparent)
                .alpha(1f)
                .padding(16.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(50.dp)
                    .background(md_theme_dark_onSecondary, CircleShape)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    modifier = Modifier.size(24.dp),
                    tint = Color.White
                )
            }

            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(
                            topStart = 0.dp,
                            topEnd = 0.dp,
                            bottomStart = 0.dp,
                            bottomEnd = 0.dp
                        )
                    )
                    .background(Color.Transparent)
                    .padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 0.dp)
                    .alpha(1f)
            ) {

                Text(
                    text = title,
                    textAlign = TextAlign.Start,
                    fontSize = 20.sp,
                    textDecoration = TextDecoration.None,
                    letterSpacing = 0.sp,
                    lineHeight = 24.sp,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Medium,
                    fontStyle = FontStyle.Normal,
                )

                Text(
                    text = subtitle,
                    textAlign = TextAlign.Start,
                    fontSize = 14.sp,
                    textDecoration = TextDecoration.None,
                    letterSpacing = 0.sp,
                    lineHeight = 17.sp,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .alpha(0.6000000238418579f),
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal,
                )
            }
        }
    }
}

@Composable
fun SettingsContent(navController: NavController) {
    val navController = navController
    Column {
        Title(title = "Настройки")
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column {
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(
                                topStart = 0.dp,
                                topEnd = 0.dp,
                                bottomStart = 0.dp,
                                bottomEnd = 0.dp
                            )
                        )
                        .background(Color.Transparent)

                        .padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 0.dp)

                        .alpha(1f)


                ) {
                    SettingsButton(
                        Icons.Filled.Palette,
                        "Вид приложения", "Кастомизация"
                    ) { navController.navigate("customize") }
                    SettingsButton(
                        Icons.Filled.Notifications,
                        "Уведомления", "Для регулярного использования"
                    ) { navController.navigate("notifications") }
                    SettingsButton(
                        Icons.Filled.Lock,
                        "Конфиденциальность", "Хранение ваших данных"
                    ) { navController.navigate("privacy") }
                    SettingsButton(
                        Icons.Filled.Code,
                        "О приложении", "Информация о версии"
                    ) { navController.navigate("about") }

                }

            }

            CustomText(
                text = "Confirmatio не является лекарственным средством. При необходимости обращайтесь к специалисту.",
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun Settings() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "settings") {
        composable("settings") { SettingsContent(navController) }
        composable("customize") { CustomizeContent(navController) }
        composable("notifications") { NotificationsContent(navController) }
        composable("privacy") { PrivacyContent(navController) }
        composable("about") { AboutContent(navController) }
    }
}

@Composable
fun LogoInFooter() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
        modifier = Modifier
            .clip(
                RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 0.dp
                )
            )
            .background(Color.Transparent)
            .padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 0.dp)
            .alpha(2f)
    ) {
        Image(
            painter = painterResource(id = R.drawable.darklogopng),
            contentDescription = "",
            alpha = 0.5F
        )
        Text(
            text = "Confirmatio v1.0",
            textAlign = TextAlign.Center,
            fontSize = 22.sp,
            textDecoration = TextDecoration.None,
            letterSpacing = 0.sp,
            lineHeight = 28.sp,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .alpha(0.5f),
            color = md_theme_dark_onSurface,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
        )
    }
}

@SuppressLint("RestrictedApi")
@Composable
fun AboutContent(navController: NavHostController) {
    Column {
        Title(title = "О приложении")
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                "Confirmatio - это приложение для психологической помощи, которое поможет " +
                        "вам справиться с тревожностью и стрессом. Приложение предлагает различные " +
                        "методики и упражнения для расслабления и улучшения вашего психологического " +
                        "состояния.\n\nОбращаем ваше внимание, что Confirmatio не является " +
                        "лекарственным средством. При необходимости обращайтесь к специалисту.\n\n" +
                        "Разработано в рамках проектной деятельности студентами 1-2 курса Института математики, " +
                        "механики и компьютерных наук ЮФУ в 2024 году."
            )
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                verticalAlignment = Alignment.Bottom
            ) {
                LogoInFooter()
            }
        }
    }
}

@Composable
fun PrivacyContent(navController: NavHostController) {
    Title(title = "Конфиденциальность")
    NotImplemented(name = "")
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun NotificationsContent(navController: NavHostController) {
    val notificationId = 1
    val context = LocalContext.current
    val channelId = "channel_id"
    val notificationBuilder = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(R.drawable.darklogopng)
        .setContentTitle("Уведомление")
        .setContentText("Вы нажали на кнопку!")
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
    var showEmptyWarningDialog by remember { mutableStateOf(false) }

    Column {
        Title(title = "Уведомления")
        val requestPermissionLauncher = rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                val notificationManager = NotificationManagerCompat.from(context)
                notificationManager.notify(notificationId, notificationBuilder.build())
            } else {
                showEmptyWarningDialog = true
            }
        }

        Column(modifier = Modifier.padding(16.dp)) {
            Button(onClick = {
                if (ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    val notificationManager = NotificationManagerCompat.from(context)
                    val channel = NotificationChannel(
                        channelId,
                        "Имя канала",
                        NotificationManager.IMPORTANCE_DEFAULT
                    )
                    notificationManager.createNotificationChannel(channel)
                    notificationManager.notify(notificationId, notificationBuilder.build())

                } else {
                    //showEmptyWarningDialog = true
                    ActivityCompat.requestPermissions(
                        context as ComponentActivity,
                        arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                        12
                    )
                }
            }) {
                Text("Показать уведомление")
            }
        }


        if (showEmptyWarningDialog) {
            AlertDialog(
                onDismissRequest = {
                    showEmptyWarningDialog = false
                },
                title = { Text("Вы не разрешили уведомления") },
                text = {
                    Text(
                        "Пожалуйста, перейдите в настройки и разрешите показ уведомлений" +
                                " для Confirmatio"
                    )
                },
                confirmButton = {
                    Button(onClick = {
                        showEmptyWarningDialog = false
                    }) {
                        Text("Хорошо")
                    }
                }
            )
        }

        if (showEmptyWarningDialog) {
            AlertDialog(
                onDismissRequest = {
                    showEmptyWarningDialog = false
                },
                title = { Text("Вы не разрешили уведомления") },
                text = {
                    Text(
                        "Пожалуйста, перейдите в настройки и разрешите показ уведомлений" +
                                " для Confirmatio"
                    )
                },
                confirmButton = {
                    Button(onClick = {
                        showEmptyWarningDialog = false
                    }) {
                        Text("Хорошо")
                    }
                }
            )
        }
    }
}

fun onRequestPermissionsResult(
    requestCode: Int,
    permissions: Array<out String>,
    grantResults: IntArray
) {
    onRequestPermissionsResult(requestCode, permissions, grantResults)
    if (requestCode == 12) {
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Разрешение получено, продолжайте с показом уведомления
        } else {
            // Разрешение не было получено, показать сообщение об ошибке
        }
    }
}

@Composable
fun CustomizeContent(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize()) {
        Title(title = "Вид приложения")
        SubTitle(
            title = "Когда-нибудь здесь можно будет настроить цветовую схему и тему приложения. " +
                    "Пока что это просто заглушка. Но скоро всё будет! :) Посмотрите на этого кота: "
        )
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Image(
                painterResource(id = R.drawable.cat),
                contentDescription = "Кот",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            )
        }
    }
}




