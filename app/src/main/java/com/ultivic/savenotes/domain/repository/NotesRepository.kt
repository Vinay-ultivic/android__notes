package com.ultivic.savenotes.domain.repository

import com.ultivic.savenotes.domain.model.NoteDetailsModel

interface NotesRepository {

    suspend fun insert(fruits: NoteDetailsModel): Long
    suspend fun getAllNotes():List<NoteDetailsModel>
    suspend fun getidNotes(id:String): NoteDetailsModel
    suspend fun deleteNote(fruitItem: NoteDetailsModel): Int
    suspend fun updateNote(fruitItem: NoteDetailsModel)
}