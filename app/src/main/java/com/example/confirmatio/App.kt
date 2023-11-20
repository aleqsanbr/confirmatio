package com.example.confirmatio

import android.app.Application
import com.example.confirmatio.database.AppDatabase

class App : Application() {
    val database by lazy { AppDatabase.createDataBase(this) }
}
