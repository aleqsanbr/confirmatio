package com.example.confirmatio.database

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.confirmatio.App
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.singleOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.Instant
import java.util.Date

class NotesViewModel (
    val database: AppDatabase
): ViewModel() {

    val notesList = database.personalNotesDao().getAll()

    fun InsertItem(title:String, text:String, date: Date, type:Int) = viewModelScope.launch {
        val item = NotesEntity(0,title,text, date, type)
        database.personalNotesDao().insertAll(item)
    }

    /*fun GetItemById(id : Long) : NotesEntity? {
        var item : NotesEntity?
        runBlocking {
            item = database.personalNotesDao().findById(id).singleOrNull()
        }
        return item
    }*/

    fun getItemById(id: Long): Flow<NotesEntity?> {
        return database.personalNotesDao().findById(id)
    }


    fun DeleteItem(id:Long) = viewModelScope.launch {
        database.personalNotesDao().deleteById(id)
    }

    fun UpdateItem(note:NotesEntity) = viewModelScope.launch {
        database.personalNotesDao().updateNote(note)
    }

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