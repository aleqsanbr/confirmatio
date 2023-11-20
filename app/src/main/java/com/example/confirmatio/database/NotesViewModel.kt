package com.example.confirmatio.database

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.confirmatio.App
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date

class NotesViewModel (
    val database: AppDatabase
): ViewModel() {

    val notesList = database.personalNotesDao().getAll()

    fun InsertItem(title:String, text:String, date: Date) = viewModelScope.launch {
        val item = NotesEntity(0,title,text, date)
        database.personalNotesDao().insertAll(item)
    }
   /* fun DeleteItem(id:Int) = viewModelScope.launch {
        val item = database.personalNotesDao().findById(id).single()
        database.personalNotesDao().delete(item)
    }
*/
    companion object{
        val factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory{
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val database = (checkNotNull(extras[APPLICATION_KEY]) as App).database
                return NotesViewModel(database) as T
            }
        }
    }

 }