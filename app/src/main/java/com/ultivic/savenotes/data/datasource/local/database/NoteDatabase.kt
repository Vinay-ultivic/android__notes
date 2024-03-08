package com.ultivic.savenotes.data.datasource.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ultivic.savenotes.data.datasource.local.dao.NotesDao
import com.ultivic.savenotes.domain.model.NoteDetailsModel

@Database(entities = [NoteDetailsModel::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun fruitDao(): NotesDao
}
