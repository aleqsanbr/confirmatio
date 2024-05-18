package com.aleqsanbr.confirmatio.screens

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.icu.util.Calendar
import android.os.Build
import androidx.activity.ComponentActivity
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
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Palette
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
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aleqsanbr.compose.md_theme_dark_onSecondary
import com.aleqsanbr.compose.md_theme_dark_onSurface
import com.aleqsanbr.confirmatio.CustomTextColor
import com.aleqsanbr.confirmatio.R
import com.aleqsanbr.confirmatio.SubTitle
import com.aleqsanbr.confirmatio.Title
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.ui.window.Dialog
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
                        Icons.Filled.Code,
                        "О приложении", "Информация о версии"
                    ) { navController.navigate("about") }

                }

            }

            CustomTextColor(
                text = "Confirmatio не является лекарственным средством. При необходимости обращайтесь к специалисту.",
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                fontSize = 12.sp
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun Settings() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "settings") {
        composable("settings") { SettingsContent(navController) }
        composable("customize") { CustomizeContent(navController) }
        composable("notifications") { NotificationsContent(navController) }
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
fun TimePicker(
    hour: Int,
    onHourSelected: (Int) -> Unit,
    minute: Int,
    onMinuteSelected: (Int) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Spacer(modifier = Modifier.width(16.dp))
        NumberPicker(
            value = hour,
            onValueChange = onHourSelected,
            range = 0..23
        )
        Text(text = "ч", modifier = Modifier.padding(start = 8.dp, end = 8.dp))
        NumberPicker(
            value = minute,
            onValueChange = onMinuteSelected,
            range = 0..59
        )
        Text(text = "мин", modifier = Modifier.padding(start = 8.dp))
    }
}

@Composable
fun NumberPicker(
    value: Int,
    onValueChange: (Int) -> Unit,
    range: IntRange
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(80.dp)
    ) {
        IconButton(onClick = { onValueChange(value + 1) }) {
            Icon(Icons.Default.ArrowDropUp, contentDescription = "Increase")
        }
        Text(text = value.toString())
        IconButton(onClick = { onValueChange(value - 1) }) {
            Icon(Icons.Default.ArrowDropDown, contentDescription = "Decrease")
        }
    }
}

@Composable
fun SaveButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
    ) {
        Text("Сохранить время")
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun NotificationsContent(navController: NavHostController) {
    val notificationId = 1
    val context = LocalContext.current
    val channelId = "channel_id"
    val notificationBuilder = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(R.drawable.logo_icon)
        .setContentTitle("Напоминание")
        .setContentText("Вы нажали на кнопку!")
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
    var showPermissionWarningDialog by remember { mutableStateOf(false) }

    var selectedHour by remember { mutableStateOf(0) }
    var selectedMinute by remember { mutableStateOf(0) }
    var isTimePickerVisible by remember { mutableStateOf(false) }
    var isNotImplementedWarningVisible by remember { mutableStateOf(false) }

    Column {
        Title(title = "Уведомления")
        val requestPermissionLauncher = rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                showPermissionWarningDialog = false
                scheduleNotification(context, selectedHour, selectedMinute)
            } else {
                showPermissionWarningDialog = true
            }
        }

        Column(modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()) {
            Button(onClick = {
                if (ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    val notificationManager = NotificationManagerCompat.from(context)
                    val channel = NotificationChannel(
                        channelId,
                        "Напоминания",
                        NotificationManager.IMPORTANCE_DEFAULT
                    )
                    notificationManager.createNotificationChannel(channel)
                    notificationManager.notify(notificationId, notificationBuilder.build())

                    scheduleNotification(context, selectedHour, selectedMinute)

                } else {
                    showPermissionWarningDialog = true
                    ActivityCompat.requestPermissions(
                        context as ComponentActivity,
                        arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                        0
                    )
                }
            }) {
                Text("Показать тестовое уведомление")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                isTimePickerVisible = false
                isNotImplementedWarningVisible = true
            }) {
                Text("Настроить время напоминания")
            }
        }

        if (showPermissionWarningDialog) {
            AlertDialog(
                onDismissRequest = {
                    showPermissionWarningDialog = false
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
                        showPermissionWarningDialog = false
                    }) {
                        Text("Хорошо")
                    }
                }
            )
        }

        if (isNotImplementedWarningVisible) {
            AlertDialog(
                onDismissRequest = {
                    isNotImplementedWarningVisible = false
                },
                title = { Text("Уведомления ещё не реализованы") },
                text = {
                    Text(
                        "Напоминания по расписанию пока не работают"
                    )
                },
                confirmButton = {
                    Button(onClick = {
                        isNotImplementedWarningVisible = false
                    }) {
                        Text("Вернусь позже")
                    }
                }
            )
        }

        if (isTimePickerVisible) {
            TimePickerDialog(
                selectedHour = selectedHour,
                onHourSelected = { hour -> selectedHour = hour },
                selectedMinute = selectedMinute,
                onMinuteSelected = { minute -> selectedMinute = minute },
                context = context,
                onDismiss = {
                    isTimePickerVisible = false
                    scheduleNotification(context, selectedHour, selectedMinute)
                },
                navController = navController
            )
        }
    }
}


@Composable
fun TimePickerDialog(
    selectedHour: Int,
    onHourSelected: (Int) -> Unit,
    selectedMinute: Int,
    onMinuteSelected: (Int) -> Unit,
    onDismiss: () -> Unit,
    context: Context,
    navController: NavHostController,
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "Выберите время напоминания")
                Spacer(modifier = Modifier.height(16.dp))
                TimePicker(
                    hour = selectedHour,
                    onHourSelected = onHourSelected,
                    minute = selectedMinute,
                    onMinuteSelected = onMinuteSelected
                )
                Spacer(modifier = Modifier.height(16.dp))
                SaveButton(
                    onClick = {
                        scheduleNotification(context, selectedHour, selectedMinute)
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}


fun scheduleNotification(context: Context, selectedHour: Int, selectedMinute: Int) {
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, NotificationReceiver::class.java)
    val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

    val calendar = Calendar.getInstance()
    calendar.set(Calendar.HOUR_OF_DAY, selectedHour)
    calendar.set(Calendar.MINUTE, selectedMinute)
    calendar.set(Calendar.SECOND, 0)
    var notificationTime = calendar.timeInMillis

    // Если выбранное время уже прошло, то устанавливаем уведомление на следующий день
    if (System.currentTimeMillis() > notificationTime) {
        calendar.add(Calendar.DAY_OF_YEAR, 1)
        notificationTime = calendar.timeInMillis  // Обновляем notificationTime
    }

    // Устанавливаем повторяющееся срабатывание на выбранное время каждый день
    alarmManager.setRepeating(
        AlarmManager.RTC_WAKEUP,
        calendar.timeInMillis,
        AlarmManager.INTERVAL_DAY,
        pendingIntent
    )

    // Сохраняем выбранное время в SharedPreferences
    val sharedPreferences = context.getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
    with(sharedPreferences.edit()) {
        putLong("notification_time", notificationTime)
        apply()
    }
}




class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val channelId = "channel_id"
        val notificationId = 1

        val notificationBuilder = NotificationCompat.Builder(context!!, channelId)
            .setSmallIcon(R.drawable.logo_icon)
            .setContentTitle("Напоминание")
            .setContentText("Вы нажали на кнопку!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val notificationManager = NotificationManagerCompat.from(context)
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            notificationManager.notify(notificationId, notificationBuilder.build())
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
            Text(text = "🐱 (только так, простите)", fontSize = 8.sp)
        }
    }
}




