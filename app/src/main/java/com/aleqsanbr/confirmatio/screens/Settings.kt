package com.aleqsanbr.confirmatio.screens

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
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
import android.preference.PreferenceManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
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
import com.aleqsanbr.confirmatio.CustomText
import com.aleqsanbr.confirmatio.NotImplemented
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.window.Dialog
import androidx.core.app.ActivityCompat
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import androidx.lifecycle.viewmodel.compose.viewModel
import android.content.SharedPreferences
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.RectangleShape
import com.aleqsanbr.compose.md_theme_dark_outlineVariant
import com.aleqsanbr.compose.md_theme_dark_secondaryContainer

object NotificationPreferences {

    private const val PREFERENCES_NAME = "notification_preferences"
    private const val KEY_HOUR = "hour"
    private const val KEY_MINUTE = "minute"

    fun saveNotificationTime(context: Context, hour: Int, minute: Int) {
        val sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        sharedPreferences.edit().putInt(KEY_HOUR, hour).putInt(KEY_MINUTE, minute).apply()
    }

    fun getNotificationTime(context: Context): Pair<Int, Int>? {
        val sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        val hour = sharedPreferences.getInt(KEY_HOUR, -1)
        val minute = sharedPreferences.getInt(KEY_MINUTE, -1)
        return if (hour != -1 && minute != -1) {
            hour to minute
        } else {
            null
        }
    }

    fun resetNotificationTime(context: Context) {
        val sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        sharedPreferences.edit().remove(KEY_HOUR).remove(KEY_MINUTE).apply()
    }
}


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

    var notificationTime by remember { mutableStateOf(NotificationPreferences.getNotificationTime(context)) }
    var selectedHour by remember { mutableStateOf(notificationTime?.first ?: 0) }
    var selectedMinute by remember { mutableStateOf(notificationTime?.second ?: 0) }

    var isTimePickerVisible by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Title(title = "Уведомления")
            Column(Modifier.padding(15.dp)) {
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

            Button(
                onClick = {
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
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = md_theme_dark_secondaryContainer,
                    contentColor = MaterialTheme.colorScheme.onBackground
                )
            ) {
                Text("🐳 Показать тестовое уведомление")
            }

            Button(
                onClick = {
                    isTimePickerVisible = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = md_theme_dark_secondaryContainer,
                    contentColor = MaterialTheme.colorScheme.onBackground
                )
            ) {
                Text("⌚ Настроить время напоминания")
            }

            notificationTime?.let {
                Text(
                    text = "✅ Установлено напоминание на ${it.first}:${it.second} c повторением каждые 6 часов",
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Button(
                    onClick = {
                        NotificationPreferences.resetNotificationTime(context)
                        notificationTime = null
                        // Optionally cancel the existing notification
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = md_theme_dark_outlineVariant,
                        contentColor = MaterialTheme.colorScheme.onBackground
                    )
                ) {
                    Text("🗑️ Сбросить настройки уведомлений", color = Color.White)
                }
            } ?: Text(
                text = "❌ Напоминание не установлено",
                modifier = Modifier.padding(vertical = 8.dp)
            )

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

            if (isTimePickerVisible) {
                TimePickerDialog(
                    selectedHour = selectedHour,
                    onHourSelected = { hour ->
                        selectedHour = hour
                        NotificationPreferences.saveNotificationTime(context, hour, selectedMinute)
                    },
                    selectedMinute = selectedMinute,
                    onMinuteSelected = { minute ->
                        selectedMinute = minute
                        NotificationPreferences.saveNotificationTime(context, selectedHour, minute)
                    },
                    context = context,
                    onDismiss = {
                        isTimePickerVisible = false
                        notificationTime = NotificationPreferences.getNotificationTime(context)
                    },
                    navController = navController
                )
            }
        }
    }
}


fun cancelScheduledNotification(context: Context) {
    val intent = Intent(context, NotificationReceiver::class.java)
    val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    alarmManager.cancel(pendingIntent)
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

fun scheduleNotification(context: Context, hour: Int, minute: Int) {
    val calendar: Calendar = Calendar.getInstance().apply {
        timeInMillis = System.currentTimeMillis()
        set(Calendar.HOUR_OF_DAY, hour)
        set(Calendar.MINUTE, minute)
    }

    val intent = Intent(context, NotificationReceiver::class.java)
    val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    alarmManager.setRepeating(
        AlarmManager.RTC_WAKEUP,
        calendar.timeInMillis,
        AlarmManager.INTERVAL_HOUR * 6,
        pendingIntent
    )
}

@Composable
fun TimePickerDialog(
    selectedHour: Int,
    onHourSelected: (Int) -> Unit,
    selectedMinute: Int,
    onMinuteSelected: (Int) -> Unit,
    context: Context,
    onDismiss: () -> Unit,
    navController: NavHostController
) {
    val timePickerDialog = android.app.TimePickerDialog(
        context,
        { _, hourOfDay, minute ->
            onHourSelected(hourOfDay)
            onMinuteSelected(minute)
            NotificationPreferences.saveNotificationTime(context, hourOfDay, minute)
            onDismiss()
        },
        selectedHour,
        selectedMinute,
        true
    )

    DisposableEffect(key1 = true) {
        timePickerDialog.show()

        onDispose {
            timePickerDialog.dismiss()
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