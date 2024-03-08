package com.ultivic.savenotes.data.rerpository

import android.util.Log
import com.ultivic.savenotes.data.datasource.local.dao.NotesDao
import com.ultivic.savenotes.domain.model.NoteDetailsModel
import com.ultivic.savenotes.domain.repository.NotesRepository
import javax.inject.Inject

class NotesRepositoryImpl @Inject constructor(private val fruit: NotesDao) : NotesRepository {
    override suspend fun insert(notes: NoteDetailsModel): Long {
        return fruit.insert(notes)
        Log.e("bb",notes.toString());
    }

    override suspend fun getAllNotes(): List<NoteDetailsModel> {
        return fruit.getAllFruits()
    }

    override suspend fun getidNotes(id: String): NoteDetailsModel {
        return fruit.fetchFruitId(id).first()
    }

    override suspend fun deleteNote(fruitItem: NoteDetailsModel): Int {
        return fruit.delete(fruitItem)
    }

    override suspend fun updateNote(fruitItem: NoteDetailsModel) {
        return fruit.update(fruitItem)
    }

}