package com.example.confirmatio.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose.md_theme_dark_onSecondary
import com.example.confirmatio.CustomText
import com.example.confirmatio.NotImplemented
import com.example.confirmatio.Title

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
                    SettingsButton(Icons.Filled.Palette,
                        "Вид приложения", "Тема и цветовая схема"
                    ) { navController.navigate("customize") }
                    SettingsButton(Icons.Filled.Notifications,
                        "Уведомления", "Для регулярного использования"
                    ) { navController.navigate("notifications") }
                    SettingsButton(Icons.Filled.Lock,
                        "Конфиденциальность", "Хранение ваших данных"
                    ) { navController.navigate("privacy") }
                    SettingsButton(Icons.Filled.Code,
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
fun AboutContent(navController: NavHostController) {
    Title(title = "О приложении")
    NotImplemented(name = "")
}

@Composable
fun PrivacyContent(navController: NavHostController) {
    Title(title = "Конфиденциальность")
    NotImplemented(name = "")
}

@Composable
fun NotificationsContent(navController: NavHostController) {
    Title(title = "Уведомления")
    NotImplemented(name = "")
}

@Composable
fun CustomizeContent(navController: NavHostController) {
    Title(title = "Вид приложения")
    NotImplemented(name = "")
}




