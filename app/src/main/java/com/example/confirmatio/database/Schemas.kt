package com.example.confirmatio.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

const val PERSONAL_NOTES_NAME = "personal_notes"
@Entity(tableName = PERSONAL_NOTES_NAME)
data class NotesEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "note_title") val noteTitle: String,
    @ColumnInfo(name = "note_text") val noteText: String,
    @ColumnInfo(name = "note_date") val noteDate: Date,
    @ColumnInfo(name = "type") val noteType: Int,

)

enum class NoteType(val type : Int){
    PERSONAL(1), PRACTICE(2);
}