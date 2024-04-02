package com.aleqsanbr.confirmatio

import android.app.Application
import com.aleqsanbr.confirmatio.database.AppDatabase

class App : Application() {
    val database by lazy { AppDatabase.createDataBase(this) }
}
