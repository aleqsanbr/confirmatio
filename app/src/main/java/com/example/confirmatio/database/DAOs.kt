package com.example.confirmatio.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface PersonalNotesDBDao {
    @Query("SELECT * FROM ${PERSONAL_NOTES_NAME}")
    fun getAll():Flow<List<NotesEntity>>

    @Query("SELECT * FROM ${PERSONAL_NOTES_NAME} WHERE id IN (:noteIds)")
    fun loadAllByIds(noteIds: IntArray): Flow<List<NotesEntity>>

    @Query("SELECT * FROM ${PERSONAL_NOTES_NAME} WHERE id IN (:id)")
    fun findById(id: Int): Flow<NotesEntity>

    @Query("SELECT * FROM ${PERSONAL_NOTES_NAME} WHERE note_title LIKE :title")
    fun findByTitle(title: String,): Flow<List<NotesEntity>>

    @Query("SELECT * FROM ${PERSONAL_NOTES_NAME} WHERE note_date LIKE :date")
    fun findByDate(date: Date): Flow<List<NotesEntity>>

    @Insert
    suspend fun insertAll(vararg notes: NotesEntity)

    @Update
    suspend fun updateNotes(vararg notes: NotesEntity)

    /*@Delete
    suspend fun delete(note: NotesEntity)*/
    @Query("DELETE FROM ${PERSONAL_NOTES_NAME} WHERE id = :id")
    suspend fun deleteById(id: Long)
}